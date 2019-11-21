package com.wildcard.eMission.ui.rewards

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.BR
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.Reward
import kotlinx.android.synthetic.main.fragment_rewards.*
import timber.log.Timber

class RewardsFragment : Fragment(), RewardsAdapter.RewardsListListener {
    private lateinit var rewardsViewModel: RewardsViewModel
    private lateinit var rewardGroupsAdapter: RewardGroupsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rewards, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rewardsViewModel =
            ViewModelProviders.of(this).get(RewardsViewModel::class.java)

        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_2)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_2)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRewardsList()

        Timber.d("Rewards size: ${rewardsViewModel.rewardsList.value?.size}")

        if (rewardsViewModel.rewardsList.value == null) {
            getRewardsList()
        }

    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle= actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle= actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_rewards)
        actionBarSubtitle?.text = getString(R.string.subtitle_rewards)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_yellow),
            context!!.getColor(R.color.colorPrimary_red)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        if (optionalLayout.childCount == 0) {
            optionalLayout.gravity = Gravity.END
            val child = DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater,
                R.layout.points_display,
                optionalLayout,
                true
            )
            child.setVariable(BR.small, false)
            rewardsViewModel.userPoints.observe( this, Observer {
                child.root.findViewById<TextView>(R.id.points_display_textView)?.text =
                    it.toString()
            })
        }
    }

    private fun getRewardsList() {
        rewardsViewModel.getRewardsList()
    }

    private fun setupRewardsList() {
        rewardGroupsAdapter = RewardGroupsAdapter(context!!, this)
        rewards_group_recyclerView.layoutManager = LinearLayoutManager(context)
        rewards_group_recyclerView.adapter = rewardGroupsAdapter

        rewardsViewModel.rewardsList.observe(this, Observer { rewardsList ->
            Timber.d("Rewards List changed")
            rewardGroupsAdapter.onDataChanged(rewardsList)
        })
    }

    override fun onRewardClaimed(reward: Reward, position: Int) {
        Timber.d("Reward: ${reward.name} claimed")
    }
}