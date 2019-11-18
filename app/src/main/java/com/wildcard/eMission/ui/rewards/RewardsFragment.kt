package com.wildcard.eMission.ui.rewards

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import kotlinx.android.synthetic.main.fragment_rewards.*

class RewardsFragment : Fragment() {

    private lateinit var rewardsViewModel: RewardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_rewards, container, false)
        val textView: TextView = root.findViewById(R.id.text_rewards)
        rewardsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rewardsViewModel =
            ViewModelProviders.of(this).get(RewardsViewModel::class.java)

        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_2)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_2)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle= actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle= actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_rewards)
        actionBarSubtitle?.text = getString(R.string.subtitle_rewards)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_yellow),
            context!!.getColor(R.color.colorPrimary_red)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        if (optionalLayout.childCount == 0) {
            optionalLayout.gravity = Gravity.END
            val child = layoutInflater.inflate(R.layout.points_display, optionalLayout)
            rewardsViewModel.userPoints.observe( this, Observer {
                child.findViewById<TextView>(R.id.points_display_textView)?.text = it.toString()
            })
        }
    }
}