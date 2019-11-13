package fi.metropolia.wildcard.emission

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.wildcard.eMission.EmissionApplication
import com.wildcard.eMission.R

class TransportationFragment: Fragment(){

    private var delegate: ToDietDelegate? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ToDietDelegate){
            delegate = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val myApplication = EmissionApplication()
        val transportationPreference = myApplication.PREF_TRANSPORTATION

        val sharedPrefence = requireContext().getSharedPreferences(myApplication.PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPrefence.edit()

        val view = inflater.inflate(R.layout.fragment_transportation,container,false)
        val carButton = view.findViewById<Button>(R.id.transportation_fragment_car_button)
        val busButton = view.findViewById<Button>(R.id.transportation_fragment_bus_button)
        val bicycleButton = view.findViewById<Button>(R.id.transportation_fragment_bicycle_button)
        val trainButton = view.findViewById<Button>(R.id.transportation_fragment_train_button)

        carButton.setOnClickListener {
            editor.putString(transportationPreference,myApplication.PREF_OPTION_TRANSPORTATION_CAR)
            editor.apply()
            delegate?.toDietFragment()
        }

        busButton.setOnClickListener {
            editor.putString(transportationPreference,myApplication.PREF_OPTION_TRANSPORTATION_BUS)
            editor.apply()
            delegate?.toDietFragment()
        }

        bicycleButton.setOnClickListener {
            editor.putString(transportationPreference,myApplication.PREF_OPTION_TRANSPORTATION_BICYCLE)
            editor.apply()
            delegate?.toDietFragment()
        }

        trainButton.setOnClickListener {
            editor.putString(transportationPreference,myApplication.PREF_OPTION_TRANSPORTATION_TRAIN)
            editor.apply()
            delegate?.toDietFragment()
        }

        return view
    }
}

interface ToDietDelegate{
    fun toDietFragment()
}