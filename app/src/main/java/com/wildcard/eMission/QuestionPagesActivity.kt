package com.wildcard.eMission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.wildcard.eMission.model.Diet
import com.wildcard.eMission.model.HousingType
import com.wildcard.eMission.model.Transportation
import com.wildcard.eMission.model.User
import com.wildcard.eMission.ui.startingquestions.*
import kotlinx.android.synthetic.main.activity_question_pages.*
import timber.log.Timber

/**
This class is for representing starting questions in separate fragments.
Interfaces are for getting information from fragments to activity.
 */
class QuestionPagesActivity : AppCompatActivity(), ToTransportationDelegate, ToDietDelegate,
    ReturnToMainActivityDelegate {
    private val amountOfTabs = 3

    /** Handler to run tests in the background */
    private var handler: Handler? = null
    private var handlerThread: HandlerThread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_pages)


        //adding the tabs to the tab layout
        for(i in 0 until amountOfTabs){
            questions_view_tablayout.addTab(questions_view_tablayout.newTab().setIcon(R.drawable.tab_selector))

        }

        //to disable of the clicking of tabs, tabs are meant to change only, when question is answered
        //https://inneka.com/programming/android/disable-tablayout/
        val tabStrip: LinearLayout = questions_view_tablayout.getChildAt(0) as LinearLayout
        tabStrip.isEnabled = false
        for (i in 0 until tabStrip.childCount) {
           tabStrip.getChildAt(i).isClickable = false
        }

        val livingStyleFragment = LivingStyleFragment()

        //to show first fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.questions_view_fragment_container, livingStyleFragment)
                .commit()
    }

    override fun onResume() {
        super.onResume()

        if (handler == null) {
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
        }
    }

    override fun onPause() {
        handlerThread?.quitSafely()
        try {
            handlerThread?.join()
            handlerThread = null
            handler = null
        } catch (e: InterruptedException) {
            Timber.e(e, "Exception!")
        }

        super.onPause()
    }

    @Synchronized
    private fun runInBackground(r: Runnable) {
        if (handler != null) {
            Timber.d("Handler is not null")
            handler?.post(r)
        } else {
            Timber.d("Handler is null")
            handlerThread = HandlerThread("inference")
            handlerThread?.start()
            handler = Handler(handlerThread?.looper!!)
            handler?.post(r)
        }
    }

    //after button press in living style fragment, show transportation fragment
    override fun toTransportationFragment(fragment: LivingStyleFragment) {

        val transportationFragment = TransportationFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.questions_view_fragment_container,transportationFragment)
            .commit()

        changeTab(1)

    }

    //after button press in transportation fragment, show diet fragment
    override fun toDietFragment() {

        val dietFragment = DietFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.questions_view_fragment_container,dietFragment)
            .commit()

        changeTab(2)
    }

    //after last question fragment return to main activity
    override fun returnToMainActivity() {
        initializeUserAndSave()

        val intent = Intent(this, YouMayStartActivity::class.java)
        startActivity(intent)
    }

    //to be able to store data of the user
    private fun initializeUserAndSave() {
        runInBackground(
            Runnable {
                val onboardingSP =
                    getSharedPreferences(EmissionApplication.PREF_NAME, Context.MODE_PRIVATE)
                onboardingSP.edit().putBoolean(EmissionApplication.PREF_ONBOARDING, false).apply()


                val vegan = onboardingSP.getBoolean(EmissionApplication.PREF_VEGETARIAN, false)
                val apartment = onboardingSP.getBoolean(EmissionApplication.PREF_APPARTMENT, false)
                val transportation = arrayListOf<Transportation>()
                when (onboardingSP.getString(EmissionApplication.PREF_TRANSPORTATION, "")) {
                    EmissionApplication.PREF_OPTION_TRANSPORTATION_BICYCLE -> transportation.add(
                        Transportation.BICYCLE
                    )
                    EmissionApplication.PREF_OPTION_TRANSPORTATION_TRAIN -> transportation.add(
                        Transportation.TRAIN
                    )
                    EmissionApplication.PREF_OPTION_TRANSPORTATION_BUS -> transportation.add(
                        Transportation.BUS
                    )
                    EmissionApplication.PREF_OPTION_TRANSPORTATION_CAR -> transportation.add(
                        Transportation.CAR
                    )
                    else -> transportation.add(Transportation.WALKING)
                }

                val user = User(
                    title = "Test User",
                    picture = "",
                    age = null,
                    carbonFootprint = 0,
                    carbonSaved = 0,
                    rewardPoints = if (vegan) 5000 else 0,
                    rewards = arrayListOf(),
                    completed_challenges = arrayListOf(),
                    diet = if (vegan) Diet.VEGAN else Diet.NON_VEGAN,
                    housingType = if (apartment) HousingType.APARTMENT else null,
                    transportation = transportation
                )

                val jsonString = Gson().toJson(user)

                val sharedPreferences =
                    getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString(Utils.PREF_USER, jsonString).apply()
            }
        )
    }

    private fun changeTab(tab_index: Int){
        val tab: TabLayout.Tab? = questions_view_tablayout.getTabAt(tab_index)
        tab?.select()
    }



}


