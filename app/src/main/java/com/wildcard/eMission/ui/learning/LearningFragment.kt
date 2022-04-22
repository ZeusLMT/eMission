package com.wildcard.eMission.ui.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import com.wildcard.eMission.databinding.FragmentLearningBinding
import timber.log.Timber

/**
 * This fragment is shown as part of Main Activity. It uses Learning Adapter to show recycle view.
 * Now the content of list is just hardcoded example texts from Learning View Model
 */
class LearningFragment : Fragment() {
    private var _binding: FragmentLearningBinding? = null
    private val binding get() = _binding!!
    private val learningViewModel: LearningViewModel by viewModels()
    private lateinit var learningAdapter: LearningAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.apply {
            itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_3)
            itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_3)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLearningsList()

        Timber.i("Learnings size : ${learningViewModel.learningsList.value?.size}")

        if(learningViewModel.learningsList.value == null){
            getLearningsList()
            Timber.i("Learnings size : ${learningViewModel.learningsList.value?.size}")
        }
    }

    override fun onResume() {
        super.onResume()
        setupActionBar()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle = actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle = actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_learning)
        actionBarSubtitle?.text = getString(R.string.subtitle_learning)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            requireContext().getColor(R.color.colorPrimary_green),
            requireContext().getColor(R.color.colorPrimary_yellow)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }

    //setting the adapter for recycler view
    private fun setupLearningsList(){
        learningAdapter = LearningAdapter(requireContext())
        binding.learningGroupRecycleView.layoutManager = LinearLayoutManager(context)
        binding.learningGroupRecycleView.adapter = learningAdapter

        learningViewModel.learningsList.observe(viewLifecycleOwner, Observer { learningsList ->
            learningAdapter.onDataChanged(learningsList)
        })
    }

    //fetches the example list from learning view model
    private fun getLearningsList(){
        val allLearnings = learningViewModel.getLearningsList()
        learningViewModel.learningsList.value = allLearnings
    }


}