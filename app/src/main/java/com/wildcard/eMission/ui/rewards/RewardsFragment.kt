package com.wildcard.eMission.ui.rewards

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.BR
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.databinding.FragmentRewardsBinding
import com.wildcard.eMission.model.ChallengePack
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardStatus
import com.wildcard.eMission.model.RewardType
import timber.log.Timber
import java.util.*

class RewardsFragment : Fragment(), RewardsAdapter.RewardsListListener {
    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!
    private val rewardsViewModel: RewardsViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by activityViewModels()
    private lateinit var rewardGroupsAdapter: RewardGroupsAdapter
    /** Handler to run tests in the background */
    private var handler: Handler? = null
    private var handlerThread: HandlerThread? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityViewModel.userDataUpdated.observe(this, Observer {
            rewardsViewModel.userPoints.value = activityViewModel.user.rewardPoints
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRewardsList()

        Timber.d("Rewards size: ${rewardsViewModel.rewardsList.value?.size}")

        if (rewardsViewModel.rewardsList.value == null) {
            getRewardsList()
        }
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.apply {
            itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_2)
            itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_2)
        }

        if (handler == null) {
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
        }
    }

    override fun onPause() {
        handlerThread?.quitSafely()
        try {
            handlerThread?.join()
            handlerThread = null
            handler = null
        } catch (e: InterruptedException) {
            Timber.e(e, "Exception!")
        }

        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Synchronized
    private fun runInBackground(r: Runnable) {
        if (handler != null) {
            Timber.d("Handler is not null")
            handler?.post(r)
        } else {
            Timber.d("Handler is null")
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
            handler?.post(r)
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
            requireContext().getColor(R.color.colorPrimary_yellow),
            requireContext().getColor(R.color.colorPrimary_red)
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
        runInBackground(
            Runnable {
                val allRewards = rewardsViewModel.getRewardsList()
                allRewards.forEach { reward ->
                    if (activityViewModel.user.rewards.find { completedReward -> completedReward == reward } != null) {
                        reward.status = RewardStatus.CLAIMED
                    }
                }
                requireActivity().runOnUiThread {
                    rewardsViewModel.rewardsList.value = allRewards
                }
            }
        )
    }

    private fun setupRewardsList() {
        rewardGroupsAdapter = RewardGroupsAdapter(requireContext(), this)
        binding.rewardsGroupRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.rewardsGroupRecyclerView.adapter = rewardGroupsAdapter

        rewardsViewModel.rewardsList.observe(viewLifecycleOwner, Observer { rewardsList ->
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
                Toast.makeText(context, getString(R.string.reward_toast_ranked_up), Toast.LENGTH_SHORT).show()
            }

            RewardType.CHALLENGE_PACK -> {
                activityViewModel.unlockedChallengePacks.add(reward.content as ChallengePack)
                Toast.makeText(context, getString(R.string.reward_toast_unlock_challenges), Toast.LENGTH_SHORT).show()

                runInBackground(
                    Runnable {
                        val sharedPreferences =
                            activity?.getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        val jsonString = Gson().toJson(activityViewModel.unlockedChallengePacks)
                        editor?.putString(Utils.PREF_UNLOCKED_PACK, jsonString)?.apply()
                    }
                )
            }

            RewardType.ACTION -> TODO()

            RewardType.THEME -> {
                Toast.makeText(context, getString(R.string.reward_toast_applying_theme), Toast.LENGTH_SHORT).show()

                runInBackground(
                    Runnable {
                        val sharedPreferences =
                            activity?.getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
                        val editor = sharedPreferences?.edit()
                        editor?.putString(Utils.PREF_THEME, reward.content as String)?.apply()
                        activity?.runOnUiThread {
                            activity?.recreate()
                        }
                    }
                )
            }

            RewardType.PROFILE_PIC -> activityViewModel.updateUserData { user ->
                user.picture = reward.content as String
                Toast.makeText(context, getString(R.string.reward_toast_unlock_picture), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}