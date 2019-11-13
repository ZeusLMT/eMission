package com.wildcard.eMission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import fi.metropolia.wildcard.emission.StartOfQuestionsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home,
//                R.id.navigation_rewards,
//                R.id.navigation_learning,
//                R.id.navigation_you
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        showBeginingQuestions()
    }

    fun showBeginingQuestions(){
        val myApplication =  EmissionApplication()
        val prefname = myApplication.PREF_NAME
        val appartmentPref = myApplication.PREF_APPARTMENT
        val transportationPref = myApplication.PREF_TRANSPORTATION
        val dietPref = myApplication.PREF_VEGETARIAN

        val sharedPreference = this.getSharedPreferences(prefname, Context.MODE_PRIVATE)

        if(!sharedPreference.contains(appartmentPref) || !sharedPreference.contains(transportationPref) || !sharedPreference.contains(dietPref)){
            val startingQuestionsIntent = Intent(this, StartOfQuestionsActivity::class.java)
            startActivity(startingQuestionsIntent)
        }
    }
}
