package com.wildcard.eMission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_you_may_start.*

/**
This is shown after starting questions and it just tell that all questions are answered and
one can proceed to main part of the app. Leads to Main Activity.
 */
class YouMayStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_may_start)

        you_may_start_proceed_button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Picasso.get().load("file:///android_asset/onboarding_start.jpg").resize(
            500,
            500
        ).into(start_imageView)
    }
}
