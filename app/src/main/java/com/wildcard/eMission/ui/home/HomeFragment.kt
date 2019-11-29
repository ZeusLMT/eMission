package com.wildcard.eMission.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.ActivityViewModel
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.CompleteStatus
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment(), ChallengesListAdapter.ChallengesListListener {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var challengesListAdapter: ChallengesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
        activityViewModel = ViewModelProviders.of(activity!!).get(ActivityViewModel::class.java)

        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_1)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_1)

        activityViewModel.userDataUpdated.observe(this, Observer {
            homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupChallengesList()

        if (homeViewModel.todayChallenges.value.isNullOrEmpty()) {
            generateTodayChallenges()
        }
    }

    //Implement Challenges List Adapter Listener's functions
    override fun onCheckpointSelected(challenge: Challenge, position: Int) {
        val todayChallenges = homeViewModel.todayChallenges.value
        if (todayChallenges!!.contains(challenge)) {
            challenge.status = when {
                (challenge.status == CompleteStatus.UNSTARTED && !challenge.singleTask) -> CompleteStatus.ONGOING
                else -> CompleteStatus.COMPLETE
            }
        }
        challengesListAdapter.notifyItemChanged(position)

        activityViewModel.updateUserData { user ->
            user.carbonSaved += challenge.points
            user.rewardPoints += challenge.points
        }

        homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()

        if (challenge.status == CompleteStatus.COMPLETE) {
            activityViewModel.updateUserData { user ->
                user.completed_challenges.add(challenge)
            }
        }


        if (todayChallenges.all { it.status == CompleteStatus.COMPLETE }) {
            Timber.d("Completed all challenges")
            showCompleteDialog()
        }
    }

    override fun onInfoSelected(challenge: Challenge, position: Int) {
        Toast.makeText(context, "Showing info for ${challenge.name}", Toast.LENGTH_SHORT).show()
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle= actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle= actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_home)
        homeViewModel.carbonSaved.observe(this, Observer { carbonSaved ->
            actionBarSubtitle?.text = getString(R.string.subtitle_home, carbonSaved)

        })
        homeViewModel.carbonSaved.value = activityViewModel.user.carbonSaved.toInt()

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_blue),
            context!!.getColor(R.color.colorPrimary_green)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }

    private fun setupChallengesList() {
        challengesListAdapter = ChallengesListAdapter(context!!,this)
        challenges_recyclerView.layoutManager = LinearLayoutManager(context)
        challenges_recyclerView.adapter = challengesListAdapter

        homeViewModel.todayChallenges.observe(this, Observer { challenges ->
            Timber.d("TodayChallenges changed")
            challengesListAdapter.onDataChanged(challenges)
        })
    }

    private fun generateTodayChallenges() {
        val personalizedChallenges = homeViewModel.allChallenges.filter { challenge ->
            when {
                (challenge.diet.isNotEmpty() && !challenge.diet.contains(activityViewModel.user.diet)) -> false
                (challenge.housingType.isNotEmpty() && !challenge.housingType.contains(
                    activityViewModel.user.housingType
                )) -> false
                (challenge.transportation.isNotEmpty() && challenge.transportation.intersect(
                    activityViewModel.user.transportation
                ).isEmpty()) -> false
                else -> true
            }
        }
        val todayChallenges = arrayListOf<Challenge>()
        personalizedChallenges.shuffled().subList(0, 3).forEach {
            it.status = CompleteStatus.UNSTARTED
            todayChallenges.add(it)
        }

        homeViewModel.todayChallenges.value?.clear()
        homeViewModel.todayChallenges.value = todayChallenges
    }

    private fun showCompleteDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(R.string.all_complete_dialog_message)
            ?.setTitle(R.string.all_complete_dialog_title)
            ?.setPositiveButton(R.string.dialog_ok, null)
            ?.create()
            ?.show()
    }
}