package com.wildcard.eMission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wildcard.eMission.databinding.ActivityYouMayStartBinding

/**
This is shown after starting questions and it just tell that all questions are answered and
one can proceed to main part of the app. Leads to Main Activity.
 */
class YouMayStartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYouMayStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYouMayStartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.youMayStartProceedButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        Picasso.get().load("file:///android_asset/onboarding_start.jpg").resize(
            500,
            500
        ).into(binding.startImageView)
    }
}
