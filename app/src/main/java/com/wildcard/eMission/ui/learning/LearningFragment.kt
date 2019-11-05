package com.wildcard.eMission.ui.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils

class LearningFragment : Fragment() {

    private lateinit var learningViewModel: LearningViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        learningViewModel =
            ViewModelProviders.of(this).get(LearningViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_learning, container, false)
        val textView: TextView = root.findViewById(R.id.text_learn)
        learningViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupActionBar()
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle = actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle = actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_learning)
        actionBarSubtitle?.text = getString(R.string.subtitle_learning)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_green),
            context!!.getColor(R.color.colorPrimary_yellow)
        )
    }
}