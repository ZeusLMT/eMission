package com.wildcard.eMission.ui.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import kotlinx.android.synthetic.main.fragment_learning.*
import timber.log.Timber

class LearningFragment : Fragment() {

    private lateinit var learningViewModel: LearningViewModel
    private lateinit var learningAdapter: LearningAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learning, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        learningViewModel =
            ViewModelProviders.of(this).get(LearningViewModel::class.java)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_3)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_3)
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupLearningsList()

        Timber.i("Learnings size : ${learningViewModel.learningsList.value?.size}")

        if(learningViewModel.learningsList.value == null){
            getLearningsList()
            Timber.i("Learnings size : ${learningViewModel.learningsList.value?.size}")
        }
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

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }

    private fun setupLearningsList(){
        learningAdapter = LearningAdapter(context!!)
        learning_group_recycle_view.layoutManager = LinearLayoutManager(context)
        learning_group_recycle_view.adapter = learningAdapter

        learningViewModel.learningsList.observe(this, Observer { learningsList ->
            learningAdapter.onDataChanged(learningsList)
        })
    }

    private fun getLearningsList(){
        val allLearnings = learningViewModel.getLearningsList()
        learningViewModel.learningsList.value = allLearnings
    }


}