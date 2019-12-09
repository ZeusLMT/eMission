package com.wildcard.eMission.ui.learning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Learning
import com.wildcard.eMission.model.LearningTier
import kotlinx.android.synthetic.main.learning_group_item_view.view.*
import timber.log.Timber

class LearningGroupsAdapter(
    private val appContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var learningsList = ArrayList<Learning>()
    private val childAdapters = arrayListOf<LearningAdapter>()
    private var learningTiers = arrayListOf<LearningTier>()
    private val viewpool = RecyclerView.RecycledViewPool()

    init{
        createChildAdapters()
    }

    class ViewHolder(
        private val groupItemView: View,
        val header: TextView = groupItemView.findViewById(R.id.learning_group_title),
        val learningRecyclerView: RecyclerView = groupItemView.findViewById(R.id.learning_item_recycleview)
    ) : RecyclerView.ViewHolder(groupItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_group_item_view, parent, false)
        /*
        val learningRecyclerView: RecyclerView = view.findViewById(R.id.learning_item_recycleview)
        learningRecyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(appContext, LinearLayoutManager.VERTICAL,false)
            layoutManager = linearLayoutManager
            setRecycledViewPool(viewpool)
            setItemViewCacheSize(2)
        }*/
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return learningTiers.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).header.text = learningTiers[position].name
        holder.learningRecyclerView.swapAdapter(childAdapters[position],true)
    }

    fun onDataChanged(newLearnings: ArrayList<Learning>){
        learningsList = newLearnings
        learningTiers.clear()
        learningsList.forEach { learning ->
            if(!learningTiers.contains(learning.tier)){
                learningTiers.add(learning.tier)
            }
        }

        createChildAdapters()

        notifyDataSetChanged()
    }

    private fun createChildAdapters() {
        childAdapters.clear()
        learningTiers.forEach{tier->
            val learnings = learningsList.filter { learning ->
                learning.tier == tier
            }
            childAdapters.add(LearningAdapter(appContext, learnings))

        }

    }

}