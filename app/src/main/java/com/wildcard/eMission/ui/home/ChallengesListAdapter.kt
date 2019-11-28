package com.wildcard.eMission.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wildcard.eMission.R
import com.wildcard.eMission.model.Challenge
import com.wildcard.eMission.model.CompleteStatus
import java.util.*
import kotlin.collections.ArrayList


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
        val title: TextView = itemView.findViewById(R.id.challenge_title_textView),
        val points: TextView = itemView.findViewById(R.id.challenge_points_textView),
        val progressBar: ProgressBar = itemView.findViewById(R.id.challenge_progress_progressBar),
        val checkpointIcon: ImageView = itemView.findViewById(R.id.challenge_checkpoint_imageView2),
        val infoIcon: ImageButton = itemView.findViewById(R.id.info_imageButton)
        ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.challenges_list_item_view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return challenges.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //language detection
        val language = Locale.getDefault().displayLanguage
        if(language.equals("suomi")) {
            (holder as ViewHolder).title.text = challenges[position].description_fin
        }else {
            (holder as ViewHolder).title.text = challenges[position].description
        }
        holder.points.text = challenges[position].points.toString()

        var progress = 0
        when (challenges[position].status) {
            CompleteStatus.UNSTARTED -> progress = 0
            CompleteStatus.ONGOING -> progress = 50
            CompleteStatus.COMPLETE -> progress = 100
        }
        holder.progressBar.setProgress(progress, false)

        if (holder.progressBar.progress == 100) {
            holder.checkpointIcon.setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
        } else {
            holder.checkpointIcon.setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint))
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