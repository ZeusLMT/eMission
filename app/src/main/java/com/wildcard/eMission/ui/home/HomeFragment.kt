package com.wildcard.eMission.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    private lateinit var challengesListAdapter: ChallengesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        Timber.d("Contains?: ${homeViewModel.todayChallenges.value?.contains(challenge)}")

        if (homeViewModel.todayChallenges.value!!.contains(challenge)) {
            homeViewModel.todayChallenges.value!![position].status = CompleteStatus.COMPLETE
        }

        challengesListAdapter.notifyItemChanged(position)
    }

    override fun onInfoSelected(challenge: Challenge, position: Int) {
        Toast.makeText(context, "Showing info for ${challenge.name}", Toast.LENGTH_SHORT).show()
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle= actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle= actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_home)
        actionBarSubtitle?.text = getString(R.string.subtitle_home, 132)

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
}