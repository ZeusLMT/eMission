package com.wildcard.eMission.ui.rewards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardTier
import timber.log.Timber

class RewardGroupsAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var rewardsList = ArrayList<Reward>()
    private var rewardTiers = arrayListOf<RewardTier>()

    class ViewHolder(
        private val groupItemView: View,
        val header: TextView = groupItemView.findViewById(R.id.section_header_textView),
        val rewardsRecyclerView: RecyclerView = groupItemView.findViewById(R.id.rewards_item_recyclerView)
    ) : RecyclerView.ViewHolder(groupItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rewards_group_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rewardTiers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).header.text = rewardTiers[position].name
    }

    fun onDataChanged(newRewards: ArrayList<Reward>) {
        rewardsList = newRewards

        rewardTiers.clear()
        rewardsList.forEach { reward ->
            if (!rewardTiers.contains(reward.tier)) {
                rewardTiers.add(reward.tier)
            }
        }

        Timber.d("Reward groups: ${rewardTiers.size}")

        notifyDataSetChanged()
    }
}