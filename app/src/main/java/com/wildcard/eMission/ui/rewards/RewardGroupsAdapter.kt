package com.wildcard.eMission.ui.rewards

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Reward
import com.wildcard.eMission.model.RewardTier
import timber.log.Timber

class RewardGroupsAdapter(
    private val appContext: Context,
    private val listener: RewardsAdapter.RewardsListListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var rewardsList = ArrayList<Reward>()
    private var rewardTiers = arrayListOf<RewardTier>()
    private val viewPool = RecyclerView.RecycledViewPool()
    private val childAdapters = arrayListOf<RewardsAdapter>()

    init {
        createChildAdapters()
    }

    class ViewHolder(
        private val groupItemView: View,
        val header: TextView = groupItemView.findViewById(R.id.section_header_textView),
        val rewardsRecyclerView: RecyclerView = groupItemView.findViewById(R.id.rewards_item_recyclerView)
    ) : RecyclerView.ViewHolder(groupItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rewards_group_item_view, parent, false)
        val rewardsRecyclerView: RecyclerView = view.findViewById(R.id.rewards_item_recyclerView)

        rewardsRecyclerView.apply {
            val linearLayoutManager =
                LinearLayoutManager(appContext, LinearLayoutManager.HORIZONTAL, false)
            linearLayoutManager.initialPrefetchItemCount = 2
            layoutManager = linearLayoutManager

            setRecycledViewPool(viewPool)
            setHasFixedSize(true)
            setItemViewCacheSize(2)
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return rewardTiers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).header.text = rewardTiers[position].name

        holder.rewardsRecyclerView.swapAdapter(childAdapters[position], true)
    }

    fun onDataChanged(newRewards: ArrayList<Reward>) {
        rewardsList = newRewards

        rewardTiers.clear()
        rewardsList.forEach { reward ->
            if (!rewardTiers.contains(reward.tier)) {
                rewardTiers.add(reward.tier)
            }
        }

        createChildAdapters()

        Timber.d("Reward groups: ${rewardTiers.size}")

        notifyDataSetChanged()
    }

    private fun createChildAdapters() {
        childAdapters.clear()
        rewardTiers.forEach { tier ->
            val rewards = rewardsList.filter { reward -> reward.tier == tier }
            childAdapters.add(RewardsAdapter(appContext, rewards, listener))
        }
    }
}