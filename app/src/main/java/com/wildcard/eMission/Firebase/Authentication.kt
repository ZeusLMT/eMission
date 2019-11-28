package com.wildcard.eMission.Firebase

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.wildcard.eMission.R
import timber.log.Timber

class Authentication(private val appContext: Context) {
    companion object {
        //Google Login Request Code
        val RC_SIGN_IN = 7
        //Firebase Auth
        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    }

    //Google Sign In Client
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    fun googleSignIn(startActivity: ((Intent, Int) -> Unit)) {
        Toast.makeText(appContext, "Signing in with Google account", Toast.LENGTH_SHORT).show()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(appContext.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(appContext, gso)

        val signInIntent = mGoogleSignInClient.signInIntent
//        startActivityForResult(signInIntent, RC_SIGN_IN)
        startActivity(signInIntent, RC_SIGN_IN)
    }

    fun firebaseAuthWithGoogle(data: Intent?, onSuccess: ((FirebaseUser) -> Unit)) {
        Timber.d("firebaseAuthWithGoogle")
        val getGoogleAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = getGoogleAccountTask.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
            firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Timber.d("signInWithCredential:success")
                        val user = firebaseAuth.currentUser
                        onSuccess(user!!)
                    } else {
                        // If sign in fails, display a message to the user.
                        Timber.w("signInWithCredential:failure: ${task.exception}")
                        Toast.makeText(appContext, "Auth Failed", Toast.LENGTH_LONG).show()
                    }
                }
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Timber.w("Google sign in failed: $e")
            // ...
        }
    }

    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //Do your Stuff
            Toast.makeText(appContext, "Hello ${user.displayName}", Toast.LENGTH_LONG).show()
        }
    }
}