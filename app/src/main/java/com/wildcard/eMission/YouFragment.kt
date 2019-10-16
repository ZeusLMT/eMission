package com.wildcard.eMission

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class YouFragment : Fragment() {

    private lateinit var youViewModel: YouViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        youViewModel =
            ViewModelProviders.of(this).get(YouViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_you, container, false)
        val textView: TextView = root.findViewById(R.id.text_you)
        youViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}