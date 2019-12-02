package com.wildcard.eMission.ui.rewards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardStatus
import com.wildcard.eMission.model.RewardTier
import com.wildcard.eMission.model.RewardType

class RewardsAdapter(
    private var appContext: Context,
    private var rewards: List<Reward>,
    private val listener: RewardsListListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface RewardsListListener {
        fun onRewardClaimed(reward: Reward, position: Int, adapterCallback: RewardsAdapter)
    }

    class ViewHolder(
        private val itemView: View,
        val rewardCard: CardView = itemView.findViewById(R.id.reward_cardView),
        val rewardImg: ImageView = itemView.findViewById(R.id.reward_img_imageView),
        val rewardTitle: TextView = itemView.findViewById(R.id.reward_header_textView),
        val rewardBottomLayout: ConstraintLayout = itemView.findViewById(R.id.reward_bottom_layout),
        val rewardClaimedBottomLayout: ConstraintLayout = itemView.findViewById(R.id.rewards_claimed_bottom_layout),
        private val rewardPointsLayout: LinearLayout = itemView.findViewById(R.id.points_display_layout),
        val rewardPoints: TextView = rewardPointsLayout.findViewById(R.id.points_display_textView),
        val claimButton: Button = itemView.findViewById(R.id.reward_claim_button)

    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rewards_item_view,
            parent,
            false
        )

        Utils.setGradientTextColor(
            itemViewDataBinding.root.findViewById(
                R.id.reward_claimed_textView
            ),
            appContext.getColor(R.color.colorPrimary_yellow),
            appContext.getColor(R.color.colorPrimary_red)
        )

        itemViewDataBinding.root.findViewById<ConstraintLayout>(R.id.rewards_claimed_bottom_layout)
            .visibility = View.GONE
        return ViewHolder(itemViewDataBinding.root)
    }

    override fun getItemCount(): Int {
        return rewards.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).rewardTitle.text = rewards[position].name
        holder.rewardPoints.text = rewards[position].points.toString()

        when (rewards[position].type) {
            RewardType.TITLE -> when (rewards[position].tier) {
                RewardTier.PRIVATE_GREEN -> Picasso.get().load("file:///android_asset/rewards_title_green.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.CORPORAL_BEE -> Picasso.get().load("file:///android_asset/rewards_title_bee.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.SERGEANT_FLOWER -> Picasso.get().load("file:///android_asset/rewards_title_flower.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.LIEUTENANT_TREE -> Picasso.get().load("file:///android_asset/rewards_title_tree.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.CAPTAIN_WOOD -> Picasso.get().load("file:///android_asset/rewards_title_wood.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.MAJOR_NATURE -> Picasso.get().load("file:///android_asset/rewards_title_nature.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.COLONEL_ENVIRONMENT -> Picasso.get().load("file:///android_asset/rewards_title_environment.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.GENERAL_CLIMATE -> Picasso.get().load("file:///android_asset/rewards_title_climate.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
                RewardTier.PRESIDENT_CARBON_NEUTRAL -> Picasso.get().load("file:///android_asset/rewards_title_CN.jpg").resize(
                    500,
                    500
                ).into(holder.rewardImg)
            }
            RewardType.PROFILE_PIC -> Picasso.get().load("file:///android_asset/rewards_profile.jpg").resize(
                500,
                500
            ).into(holder.rewardImg)
            RewardType.CHALLENGE_PACK -> Picasso.get().load("file:///android_asset/rewards_pack.jpg").resize(
                500,
                500
            ).into(holder.rewardImg)
            else -> Picasso.get().load("file:///android_asset/rewards_general.jpg").resize(
                500,
                500
            ).into(holder.rewardImg)
        }

        when (rewards[position].status) {
            RewardStatus.CLAIMED -> {
                holder.rewardCard.alpha = 0.6f
                holder.rewardBottomLayout.visibility = View.INVISIBLE
                holder.rewardClaimedBottomLayout.visibility = View.VISIBLE
            }
            else -> {
                holder.rewardCard.alpha = 1f
                holder.rewardBottomLayout.visibility = View.VISIBLE
                holder.rewardClaimedBottomLayout.visibility = View.GONE
            }
        }

        holder.claimButton.setOnClickListener {
            listener.onRewardClaimed(rewards[position], position, this)
        }
    }
}