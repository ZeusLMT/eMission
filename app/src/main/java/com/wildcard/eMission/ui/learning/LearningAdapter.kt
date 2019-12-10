package com.wildcard.eMission.ui.learning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Learning
import kotlinx.android.synthetic.main.learning_item_view.view.*

class LearningAdapter(
    private var appContext: Context,
    private var learnigs: List<Learning>
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class ViewHolder(
        private val itemView: View,
        val learningTitle: TextView = itemView.findViewById(R.id.learning_header_textview),
        val learningDescription: TextView = itemView.findViewById(R.id.learning_desc_textView)
    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

       val itemViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
           LayoutInflater.from(parent.context),
           R.layout.learning_item_view,
           parent,
           false)

        return ViewHolder(itemViewDataBinding.root)
        /*
        val view = LayoutInflater.from(appContext)
            .inflate(R.layout.learning_group_item_view,parent,false)
        val viewHolder = ViewHolder(view)
        return viewHolder
*/

    }

    override fun getItemCount(): Int {
        return learnigs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).learningTitle.text = learnigs[position].name
        (holder as ViewHolder).learningDescription.text = learnigs[position].description
    }

}