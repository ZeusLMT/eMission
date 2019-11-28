package com.wildcard.eMission.ui.you

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import com.wildcard.eMission.ActivityViewModel
import com.wildcard.eMission.R
import com.wildcard.eMission.Utils
import kotlinx.android.synthetic.main.fragment_you.*

class YouFragment : Fragment() {
    private lateinit var youViewModel: YouViewModel
    private lateinit var activityViewModel: ActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activityViewModel = ViewModelProviders.of(activity!!).get(ActivityViewModel::class.java)
        youViewModel =
            ViewModelProviders.of(this).get(YouViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_you, container, false)
        val textView: TextView = root.findViewById(R.id.text_you)
        youViewModel.text.observe(this, Observer {
            textView.text = it
        })

        Picasso.get().load("file:///android_asset/rewards_profile.jpg").resize(
            500,
            500
        ).into(root.findViewById<ImageView>(R.id.profile_picture_imageView))
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemTextColor = context?.getColorStateList(R.color.nav_item_color_state_list_4)
        activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.itemIconTintList = context?.getColorStateList(R.color.nav_item_color_state_list_4)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profile_title_textView.setOnClickListener {
            profile_title_textView.text = activityViewModel.user.value?.name.toString()
        }
    }

    private fun setupActionBar() {
        val actionBar = activity?.findViewById<Toolbar>(R.id.toolbar)

        val actionBarTitle = actionBar?.findViewById<TextView>(R.id.actionbar_title)
        val actionBarSubtitle = actionBar?.findViewById<TextView>(R.id.actionbar_subtitle)
        actionBarTitle?.text = getString(R.string.title_you)
        actionBarSubtitle?.text = getString(R.string.subtitle_you)

        Utils.setGradientTextColor(
            actionBarTitle!!,
            context!!.getColor(R.color.colorPrimary_red),
            context!!.getColor(R.color.colorPrimary_blue)
        )

        val optionalLayout = actionBar.findViewById<LinearLayout>(R.id.optional_layout)
        optionalLayout.removeAllViews()
    }
}