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
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.CompleteStatus
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : Fragment(), ChallengesListAdapter.ChallengesListListener {
    private lateinit var homeViewModel: HomeViewModel
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

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_1)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_1)

//        nav_view.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_1)
//        nav_view.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_1)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupChallengesList()

        Timber.d("todayChallenges size: ${homeViewModel.todayChallenges.value?.size}")

        if (homeViewModel.todayChallenges.value == null) {
            generateTodayChallenges()
        }
    }

    //Implement Challenges List Adapter Listener's functions
    override fun onCheckpointSelected(challenge: Challenge, position: Int) {
        val allChallenges = homeViewModel.todayChallenges.value
        if (allChallenges!!.contains(challenge)) {
            allChallenges[position].status = CompleteStatus.COMPLETE
        }
        challengesListAdapter.notifyItemChanged(position)
        homeViewModel.carbonSaved.value = homeViewModel.carbonSaved.value?.plus(challenge.points)


        if (allChallenges.all { it.status == CompleteStatus.COMPLETE }) {
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
        homeViewModel.generateTodayChallenges()
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