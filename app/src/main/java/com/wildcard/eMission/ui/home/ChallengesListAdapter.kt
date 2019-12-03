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
        val cardView: CardView = itemView.findViewById(R.id.challenge_card_cardView),
        val title: TextView = itemView.findViewById(R.id.challenge_title_textView),
        val desc: TextView = itemView.findViewById(R.id.challenge_desc_textView),
        private val pointsLayout: LinearLayout = itemView.findViewById(R.id.challenge_points_layout),
        val points: TextView = pointsLayout.findViewById(R.id.points_display_textView),
        val progressBar: ProgressBar = itemView.findViewById(R.id.challenge_progress_progressBar),
        val completeCheckpointIcon: ImageView = itemView.findViewById(R.id.challenge_complete_checkpoint_imageView),
        val ongoingCheckpointIcon: ImageView = itemView.findViewById(R.id.challenge_ongoing_checkpoint_imageView),
        val unstartedCheckpointIcon: ImageView = itemView.findViewById(R.id.challenge_unstarted_checkpoint_imageView),
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
            CompleteStatus.UNSTARTED -> {
                progress = 0
                holder.unstartedCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint))
                }
                if (challenges[position].singleTask) {
                    holder.ongoingCheckpointIcon.visibility = View.GONE
                } else {
                    holder.ongoingCheckpointIcon.visibility = View.GONE
                    holder.ongoingCheckpointIcon.setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint))
                }
                holder.completeCheckpointIcon.visibility = View.GONE
                holder.cardView.alpha = 1f
            }

            CompleteStatus.ONGOING -> {
                progress = 50
                holder.unstartedCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
                }
                holder.ongoingCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint))
                }
                holder.completeCheckpointIcon.visibility = View.GONE
                holder.cardView.alpha = 1f
            }

            CompleteStatus.COMPLETE -> {
                progress = 100
                holder.unstartedCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
                    isEnabled = false
                }
                holder.completeCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
                    isEnabled = false
                }
                holder.ongoingCheckpointIcon.apply {
                    visibility = View.VISIBLE
                    setImageDrawable(appContext.getDrawable(R.drawable.ic_challenge_checkpoint_filled))
                    isEnabled = false
                }
                holder.cardView.alpha = 0.6f
            }
        }

        holder.progressBar.setProgress(progress, false)


        holder.unstartedCheckpointIcon.setOnClickListener {
            listener.onCheckpointSelected(challenges[position], position)
        }
        holder.ongoingCheckpointIcon.setOnClickListener {
            listener.onCheckpointSelected(challenges[position], position)
        }
        holder.completeCheckpointIcon.setOnClickListener {
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