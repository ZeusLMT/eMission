package com.wildcard.eMission.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.CompleteStatus


class ChallengesListAdapter (
    private val appContext: Context,
    private val listener: ChallengesListListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var challenges= ArrayList<Challenge>()

    interface ChallengesListListener {
        fun onCheckpointSelected(challenge: Challenge, position: Int)
        fun onInfoSelected(challenge: Challenge, position: Int)
    }

    class ViewHolder (
        private val itemView: View,
        val cardView: CardView = itemView.findViewById(R.id.challenge_card_cardView),
        val title: TextView = itemView.findViewById(R.id.challenge_title_textView),
        private val pointsLayout: LinearLayout = itemView.findViewById(R.id.challenge_points_layout),
        val points: TextView = pointsLayout.findViewById(R.id.points_display_textView),
        val progressBar: ProgressBar = itemView.findViewById(R.id.challenge_progress_progressBar),
        val checkpointIcon: ImageView = itemView.findViewById(R.id.challenge_checkpoint_imageView2),
        val infoIcon: ImageButton = itemView.findViewById(R.id.info_imageButton)
        ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemViewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.challenges_list_item_view,
            parent,
            false
        )
        return ViewHolder(itemViewDataBinding.root)
    }

    override fun getItemCount(): Int {
        return challenges.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).title.text = challenges[position].description
        holder.points.text = challenges[position].points.toString()

        val progress: Int = when (challenges[position].status) {
            CompleteStatus.UNSTARTED -> 0
            CompleteStatus.ONGOING -> 50
            CompleteStatus.COMPLETE -> 100
        }
        holder.progressBar.setProgress(progress, false)

        if (holder.progressBar.progress == 100) {
            holder.checkpointIcon.setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
            holder.cardView.alpha = 0.6f
            holder.checkpointIcon.isEnabled = false
        } else {
            holder.checkpointIcon.setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint))
            holder.cardView.alpha = 1f
            holder.checkpointIcon.isEnabled = true
        }


        holder.checkpointIcon.setOnClickListener {
            listener.onCheckpointSelected(challenges[position], position)
        }
        holder.infoIcon.setOnClickListener {
            listener.onInfoSelected(challenges[position], position)
        }
    }

    fun onDataChanged(newChallenges: ArrayList<Challenge>) {
        val sizeDifference = challenges.size - newChallenges.size
        val lastIndex = challenges.lastIndex
        challenges = newChallenges

        when {
            sizeDifference > 0 -> {
                notifyItemRangeRemoved(lastIndex, sizeDifference)
            }
            sizeDifference < 0 -> {
                notifyItemRangeInserted(lastIndex, sizeDifference)
            }
        }
    }
}