package com.wildcard.eMission.ui.learning

import android.content.ClipDescription
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Learning
import com.wildcard.eMission.model.LearningTier
import java.util.*
import kotlin.collections.ArrayList

/**
    This is for recycler view in Learning fragment. In this is shown various information:
    background information about climate change and some facts and tips concerning carbon
    footprint.
 */
class LearningAdapter(
    private val appContext: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var learningsList = ArrayList<Learning>()
    private var learningTiers = arrayListOf<LearningTier>()



    class ViewHolder(
        private val itemView: View,
        val tier: TextView = itemView.findViewById(R.id.learning_tier_textview),
        val header: TextView = itemView.findViewById(R.id.learning_header_textview),
        val description: TextView = itemView.findViewById(R.id.learning_desc_textView)
    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.learning_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return learningsList.size
    }

    //needs to check what language is on used device, if finnish, shows finnish versions of text
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
            (holder as ViewHolder).tier.text=tier_fin
            holder.header.text = learningsList[position].name_fin
            holder.description.text = learningsList[position].description_fin

        }else {
            (holder as ViewHolder).tier.text=learningsList[position].tier.name
            holder.header.text = learningsList[position].name
            holder.description.text = learningsList[position].description
        }
        holder.tier.setTextColor(ContextCompat.getColor(appContext,setColor(learningsList[position].tier.name)))
    }

    //changes the color of learning category name
    private fun setColor(tier_name: String): Int{
        if (tier_name.equals(LearningTier.HOME_APPLIANCES.name)){
            return R.color.colorPrimary_blue
        }else if(tier_name.equals(LearningTier.BACKGROUND.name)){
            return R.color.colorPrimary_green
        }else if(tier_name.equals(LearningTier.TRAVELING.name)){
            return R.color.colorPrimary_red
        }else{
            return R.color.colorPrimaryDark
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