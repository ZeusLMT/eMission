package com.wildcard.eMission.ui.learning

import android.content.ClipDescription
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Learning
import com.wildcard.eMission.model.LearningTier
import java.util.*
import kotlin.collections.ArrayList

class LearningAdapter(
    private val appContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var learningsList = ArrayList<Learning>()
    private var learningTiers = arrayListOf<LearningTier>()



    class ViewHolder(
        private val groupItemView: View,
        val header: TextView = groupItemView.findViewById(R.id.learning_header_textview),
        val description: TextView = groupItemView.findViewById(R.id.learning_desc_textView)
    ) : RecyclerView.ViewHolder(groupItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return learningsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val language = Locale.getDefault().displayLanguage
        if(language.equals("suomi")) {
            val tier = learningsList[position].tier.name
            var tier_fin = tier
            if (tier.equals(LearningTier.HOME_APPLIANCES.name)){
                tier_fin = "KODINKONEET"
            }else if(tier.equals(LearningTier.BACKGROUND.name)){
                tier_fin = "TAUSTAA"
            }else if(tier.equals(LearningTier.TRAVELING.name)){
                tier_fin = "MATKUSTUS"
            }
            (holder as ViewHolder).header.text = "$tier_fin: ${learningsList[position].name_fin}"
            holder.description.text = learningsList[position].description_fin
        }else {
            (holder as ViewHolder).header.text = "${learningsList[position].tier}: ${learningsList[position].name}"
            holder.description.text = learningsList[position].description
        }
    }



    fun onDataChanged(newLearnings: ArrayList<Learning>){
        learningsList = newLearnings
        learningTiers.clear()
        learningsList.forEach { learning ->
            if(!learningTiers.contains(learning.tier)){
                learningTiers.add(learning.tier)
            }
        }

        notifyDataSetChanged()
    }



}