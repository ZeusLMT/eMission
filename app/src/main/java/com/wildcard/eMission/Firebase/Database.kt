package com.wildcard.eMission.Firebase

import com.google.firebase.database.FirebaseDatabase

class Database {
    companion object {
        val database = FirebaseDatabase.getInstance().reference
    }
}