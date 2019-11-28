package com.wildcard.eMission

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseUser
import com.wildcard.eMission.Firebase.Authentication
import com.wildcard.eMission.Firebase.Database
import com.wildcard.eMission.model.User
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var activityViewModel: ActivityViewModel
    private lateinit var authentication: Authentication

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

        activityViewModel = ViewModelProviders.of(this).get(ActivityViewModel::class.java)

        authentication = Authentication(this)
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = Authentication.firebaseAuth.currentUser
        if (currentUser != null) {
            updateUI(currentUser)
            parseUserInfo(currentUser)
        } else {
            authentication.googleSignIn { intent, requestCode ->
                startActivityForResult(intent, requestCode)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == Authentication.RC_SIGN_IN) {
            Timber.d("Sign in success")
            authentication.firebaseAuthWithGoogle(data) { firebaseUser ->
                parseUserInfo(firebaseUser)
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //Do your Stuff
            Toast.makeText(this, "Hello ${user.displayName}", Toast.LENGTH_LONG).show()
        }
    }

    private fun parseUserInfo(user: FirebaseUser?) {
        if (user != null) {
            Timber.d("parseUserInfo")
            activityViewModel.user.value?.name = user.displayName!!
            user.getIdToken(false).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.d("${user.displayName} - ${task.result?.token}")
                    activityViewModel.user.value?.uId = task.result?.token!!
                    Timber.d("${activityViewModel.user.value?.name} - ${activityViewModel.user.value?.uId}")

                    writeNewUser(activityViewModel.user.value!!)
                }
            }
        } else {
            Timber.d("Invalid user")
        }
    }

    private fun writeNewUser(user: User) {
        Timber.d("Write")
        Database.database.child("users").setValue(user)
    }
}
