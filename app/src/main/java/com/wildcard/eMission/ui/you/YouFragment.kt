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
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.RewardType
import kotlinx.android.synthetic.main.fragment_you.*

class YouFragment : Fragment() {
    private lateinit var youViewModel: YouViewModel
    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        youViewModel = ViewModelProviders.of(this).get(YouViewModel::class.java)
        activityViewModel = ViewModelProviders.of(activity!!).get(ActivityViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_you, container, false)

        Picasso
            .get()
            .load(if (activityViewModel.user.picture.isEmpty()) "file:///android_asset/rewards_profile.jpg" else activityViewModel.user.picture)
            .resize(
            500,
            500
            )
            .transform(Utils.Companion.PicassoCircleTransformation())
            .into(root.findViewById<ImageView>(R.id.profile_picture_imageView))

        root.findViewById<TextView>(R.id.profile_title_textView).text = activityViewModel.user.title
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_4)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_4)
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupStreak()
        setupReward()
        setupChallenge()
//        setupTitle()
    }

//    private fun setupTitle() {
//        profile_title_textView.text = activityViewModel.user.title
//        val rewardList = activityViewModel.user.rewards
//        if (rewardList.isNotEmpty()) {
//            val titles = rewardList.filter { reward ->
//                reward.type == RewardType.TITLE
//            }
//            if (titles.isNotEmpty()) {
//                profile_title_textView.text = titles.last().content as String
//            }
//        }
//    }

    private fun setupReward() {
        val rewardList = activityViewModel.user.rewards
        if (rewardList.isNotEmpty()) {
            val latestReward = rewardList.last()

            most_recent_reward_textView.text = getString(R.string.most_recent_reward_text)
            most_recent_reward_value_textView.text = latestReward.name

            if (latestReward.image.isNotEmpty()) {
                Picasso.get().load(latestReward.image).resize(
                    500,
                    500
                ).into(most_recent_reward_imageView)
            } else {
                when (latestReward.type) {
                    RewardType.TITLE -> Picasso.get().load("file:///android_asset/rewards_title.jpg").resize(
                        500,
                        500
                    ).into(most_recent_reward_imageView)
                    RewardType.PROFILE_PIC -> Picasso.get().load("file:///android_asset/rewards_profile.jpg").resize(
                        500,
                        500
                    ).into(most_recent_reward_imageView)
                    RewardType.CHALLENGE_PACK -> Picasso.get().load("file:///android_asset/rewards_pack.jpg").resize(
                        500,
                        500
                    ).into(most_recent_reward_imageView)
                    else -> Picasso.get().load("file:///android_asset/rewards_general.jpg").resize(
                        500,
                        500
                    ).into(most_recent_reward_imageView)
                }
            }
        } else {
            most_recent_reward_cardView.visibility = View.GONE
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

            big_challenge_textView.text = getString(R.string.biggest_impact_challenge_text)
            big_challenge_value_textView.text = biggestChallenge.name
        } else {
            big_challenge_cardView.visibility = View.GONE
        }
    }

    private fun setupStreak() {
        streak_textView.text = getString(R.string.streak_text)
        streak_value_textView.text = activityViewModel.user.completed_challenges.size.toString()
        Picasso.get().load("file:///android_asset/graphs_demo.jpg").resize(
            500,
            500
        ).into(streak_imageView)
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle = actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle = actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_you)
        actionBarSubtitle?.text = getString(R.string.subtitle_you)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_red),
            context!!.getColor(R.color.colorPrimary_blue)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }
}