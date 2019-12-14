package com.wildcard.eMission.ui.startingquestions

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.wildcard.eMission.EmissionApplication
import com.wildcard.eMission.R

/**
    This fragment is part of starting questions and here is asked are you vegetarian or not. The
    answer is stored in shared preferences.
 */
class DietFragment: Fragment(){

    private var delegate: ReturnToMainActivityDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ReturnToMainActivityDelegate){
            delegate = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dietPreference = EmissionApplication.PREF_VEGETARIAN

        val sharedPreference = requireContext().getSharedPreferences(
            EmissionApplication.PREF_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sharedPreference.edit()

        val view = inflater.inflate(R.layout.fragment_diet,container,false)
        val yesButton = view.findViewById<Button>(R.id.fragment_diet_yes_button)
        val noButton = view.findViewById<Button>(R.id.fragment_diet_no_button)

        yesButton.setOnClickListener {
            editor.putBoolean(dietPreference,true)
            editor.apply()
            delegate?.returnToMainActivity()
        }

        noButton.setOnClickListener {
            editor.putBoolean(dietPreference,false)
            editor.apply()
            delegate?.returnToMainActivity()
        }

        Picasso.get().load("file:///android_asset/onboarding_diet.jpg").resize(
            500,
            500
        ).into(view.findViewById<ImageView>(R.id.diet_imageView))

        return view
    }
}

/*
    To be able to detect from Question Pages Activity, when the button is pressed
 */
interface ReturnToMainActivityDelegate{

    fun returnToMainActivity()
}