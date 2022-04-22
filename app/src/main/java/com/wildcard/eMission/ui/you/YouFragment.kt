package com.wildcard.eMission.ui.you

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.databinding.FragmentLearningBinding
import com.wildcard.eMission.databinding.FragmentYouBinding
import com.wildcard.eMission.model.RewardType

class YouFragment : Fragment() {
    private var _binding: FragmentYouBinding? = null
    private val binding get() = _binding!!
    private val youViewModel: YouViewModel by viewModels()
    private val activityViewModel: ActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYouBinding.inflate(inflater, container, false)
        val view = binding.root

        Picasso
            .get()
            .load(if (activityViewModel.user.picture.isEmpty()) "file:///android_asset/rewards_profile.png" else activityViewModel.user.picture)
            .resize(
            500,
            500
            )
            .transform(Utils.Companion.PicassoCircleTransformation())
            .into(view.findViewById<ImageView>(R.id.profile_picture_imageView))

        view.findViewById<TextView>(R.id.profile_title_textView).text = activityViewModel.user.title
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.apply {
            itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_4)
            itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_4)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupStreak()
        setupReward()
        setupChallenge()
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupReward() {
        val rewardList = activityViewModel.user.rewards
        if (rewardList.isNotEmpty()) {
            val latestReward = rewardList.last()

            binding.mostRecentRewardTextView.text = getString(R.string.most_recent_reward_text)
            binding.mostRecentRewardValueTextView.text = latestReward.name

            if (latestReward.image.isNotEmpty()) {
                Picasso.get().load(latestReward.image).resize(
                    500,
                    500
                ).into(binding.mostRecentRewardImageView)
            } else {
                when (latestReward.type) {
                    RewardType.TITLE -> Picasso.get().load("file:///android_asset/rewards_title.jpg").resize(
                        500,
                        500
                    ).into(binding.mostRecentRewardImageView)
                    RewardType.PROFILE_PIC -> Picasso.get().load("file:///android_asset/rewards_profile.png").resize(
                        500,
                        500
                    ).into(binding.mostRecentRewardImageView)
                    RewardType.CHALLENGE_PACK -> Picasso.get().load("file:///android_asset/rewards_pack.jpg").resize(
                        500,
                        500
                    ).into(binding.mostRecentRewardImageView)
                    else -> Picasso.get().load("file:///android_asset/rewards_general.jpg").resize(
                        500,
                        500
                    ).into(binding.mostRecentRewardImageView)
                }
            }
        } else {
            binding.mostRecentRewardCardView.visibility = View.GONE
        }
    }

    private fun setupChallenge() {
        val challengeList = activityViewModel.user.completed_challenges

        if (challengeList.isNotEmpty()) {
            var biggestChallenge = challengeList[0]
            challengeList.forEach { challenge ->
                if (biggestChallenge.points <= challenge.points) {
                    biggestChallenge = challenge
                }
            }

            binding.bigChallengeTextView.text = getString(R.string.biggest_impact_challenge_text)
            binding.bigChallengeValueTextView.text = biggestChallenge.name
        } else {
            binding.bigChallengeCardView.visibility = View.GONE
            binding.streakCardView.visibility = View.GONE

        }
    }

    private fun setupStreak() {
        if (binding.streakCardView.visibility != View.GONE) {
            binding.streakTextView.text = getString(R.string.streak_text)
            binding.streakValueTextView.text = activityViewModel.user.completed_challenges.size.toString()
            Picasso.get().load("file:///android_asset/graphs_demo.jpg").resize(
                500,
                500
            ).into(binding.streakImageView)
        }
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle = actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle = actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_you)
        actionBarSubtitle?.text = getString(R.string.subtitle_you)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            requireContext().getColor(R.color.colorPrimary_red),
            requireContext().getColor(R.color.colorPrimary_blue)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }
}