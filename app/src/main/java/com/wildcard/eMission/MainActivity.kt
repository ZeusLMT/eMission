package com.wildcard.eMission

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.wildcard.eMission.databinding.ActivityMainBinding
import com.wildcard.eMission.databinding.ActivityQuestionPagesBinding
import com.wildcard.eMission.databinding.ActivityStartOfQuestionsBinding
import com.wildcard.eMission.model.User
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        val navController = findNavController(R.id.nav_host_fragment)
        navController.setGraph(R.navigation.mobile_navigation)
        navView.setupWithNavController(navController)

        readUserDataFromSP()
        activityViewModel.writeUserToSP.observe(this, Observer { write ->
            if (write) {
                writeUserDataToSP()
                activityViewModel.writeUserToSP.value = false
            }
        })

        showBeginingQuestions()
    }

    private fun showBeginingQuestions() {
        val sharedPreference =
            this.getSharedPreferences(EmissionApplication.PREF_NAME, Context.MODE_PRIVATE)

        if (sharedPreference.getBoolean(EmissionApplication.PREF_ONBOARDING, true)) {
            val startingQuestionsIntent = Intent(this, StartOfQuestionsActivity::class.java)
            startActivity(startingQuestionsIntent)
        }
    }

    override fun onResume() {
        super.onResume()

        readUserDataFromSP()
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("Close application")
    }

    private fun writeUserDataToSP() {
        val jsonString = Gson().toJson(activityViewModel.user)

        val sharedPreferences = getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Utils.PREF_USER, jsonString).apply()
    }

    private fun readUserDataFromSP() {
        val sharedPreferences = getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(Utils.PREF_USER, "")!!

        if (jsonString.isNotEmpty()) {
            val user = Gson().fromJson<User>(jsonString, User::class.java)
            activityViewModel.user = user
            activityViewModel.userDataUpdated.value = !activityViewModel.userDataUpdated.value!!
        }
    }

    private fun setTheme() {
        val sharedPreferences = getSharedPreferences(Utils.SHARE_PREFS, Context.MODE_PRIVATE)
        //Change app theme accordingly to user settings
        when (sharedPreferences.getString(Utils.PREF_THEME, "LIGHT")) {
            "LIGHT" -> setTheme(R.style.AppTheme)
            "DARK" -> setTheme(R.style.AppThemeDark)
        }
    }
}
