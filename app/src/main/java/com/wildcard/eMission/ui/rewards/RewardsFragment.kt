package com.wildcard.eMission.ui.rewards

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.BR
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardStatus
import com.wildcard.eMission.model.RewardType
import kotlinx.android.synthetic.main.fragment_rewards.*
import timber.log.Timber

class RewardsFragment : Fragment(), RewardsAdapter.RewardsListListener {
    private lateinit var rewardsViewModel: RewardsViewModel
    private lateinit var activityViewModel: ActivityViewModel
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
        rewardsViewModel = ViewModelProviders.of(this).get(RewardsViewModel::class.java)
        activityViewModel = ViewModelProviders.of(activity!!).get(ActivityViewModel::class.java)

        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_2)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_2)

        activityViewModel.userDataUpdated.observe(this, Observer {
            rewardsViewModel.userPoints.value = activityViewModel.user.rewardPoints

        })
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

            rewardsViewModel.userPoints.value = activityViewModel.user.rewardPoints
        }
    }

    private fun getRewardsList() {
        val allRewards = rewardsViewModel.getRewardsList()
        allRewards.forEach { reward ->
            if (activityViewModel.user.rewards.find { completedReward -> completedReward == reward } != null) {
                reward.status = RewardStatus.CLAIMED
            }
        }
        rewardsViewModel.rewardsList.value = allRewards
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

    override fun onRewardClaimed(reward: Reward, position: Int, adapterCallback: RewardsAdapter) {
        var currentPoints = activityViewModel.user.rewardPoints

        if (currentPoints >= reward.points) {
            currentPoints -= reward.points
            reward.status = RewardStatus.CLAIMED
            activityViewModel.user.rewards.add(reward)

            adapterCallback.notifyItemChanged(position)

            Timber.d("${rewardsViewModel.userPoints.value}")

            activityViewModel.updateUserData { user ->
                user.rewardPoints -= reward.points
            }

            applyReward(reward)

        } else {
            showPurchaseDialog()
        }
    }

    private fun showPurchaseDialog() {
        val builder: AlertDialog.Builder? = activity?.let {
            AlertDialog.Builder(it)
        }

        builder?.setMessage(R.string.reward_claim_dialog_message)
            ?.setTitle(R.string.reward_claim_dialog_title)
            ?.setNeutralButton(R.string.dialog_cancel, null)
            ?.create()
            ?.show()
    }

    private fun applyReward(reward: Reward) {
        when (reward.type) {
            RewardType.TITLE -> activityViewModel.updateUserData { user ->
                user.title = reward.content as String
            }
            RewardType.CHALLENGE_PACK -> TODO()
            RewardType.ACTION -> TODO()
            RewardType.THEME -> TODO()
            RewardType.PROFILE_PIC -> activityViewModel.updateUserData { user ->
                user.picture = reward.content as String
            }
        }
    }
}