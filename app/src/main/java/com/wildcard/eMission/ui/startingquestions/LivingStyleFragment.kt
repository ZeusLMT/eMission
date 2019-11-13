package fi.metropolia.wildcard.emission

import android.content.Context
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.wildcard.eMission.EmissionApplication
import com.wildcard.eMission.R

class LivingStyleFragment(): Fragment(){

    private val myApplication = EmissionApplication()
    private var delecate : ToTransportationDelecate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("delegatet","onAttach")
        if(context is ToTransportationDelecate){
            delecate = context
            Log.i("delegatet","is context now")
        }

        
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val preferenceName = myApplication.PREF_NAME
        val livingPreference = myApplication.PREF_APPARTMENT

        val sharedPreference = requireContext().getSharedPreferences(preferenceName,Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        val view = inflater.inflate(R.layout.fragment_living_style,container,false)
        val yesButton = view.findViewById<Button>(R.id.living_style_fragment_yes_button)
        yesButton.setOnClickListener {
            editor.putBoolean(livingPreference,true)
            editor.apply()
            delecate?.toTransportationFragment(this)
        }
        val noButton = view.findViewById<Button>(R.id.living_style_fragment_no_button)
        noButton.setOnClickListener {
            editor.putBoolean(livingPreference,false)
            editor.apply()
            delecate?.toTransportationFragment(this)
        }


        return view
    }


}

interface ToTransportationDelecate {
    fun toTransportationFragment(fragment: LivingStyleFragment)
}