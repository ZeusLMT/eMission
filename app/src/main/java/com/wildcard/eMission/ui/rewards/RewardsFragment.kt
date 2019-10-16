package com.wildcard.eMission.ui.rewards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wildcard.eMission.R

class RewardsFragment : Fragment() {

    private lateinit var rewardsViewModel: RewardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rewardsViewModel =
            ViewModelProviders.of(this).get(RewardsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_rewards, container, false)
        val textView: TextView = root.findViewById(R.id.text_rewards)
        rewardsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}