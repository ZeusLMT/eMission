package com.wildcard.eMission

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class ActivityViewModel : ViewModel() {
    var firebaseAuth: FirebaseAuth? = null
    var firebaseDatabase: FirebaseDatabase? = null
    var user: FirebaseUser? = null

    init {
        firebaseAuth = FirebaseAuth.getInstance()
    }
}