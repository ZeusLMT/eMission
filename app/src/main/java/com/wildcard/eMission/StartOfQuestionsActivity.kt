package com.wildcard.eMission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wildcard.eMission.databinding.ActivityStartOfQuestionsBinding

/**
If user don't have answers to starting question in shared preferences, this welcoming to
starting questions is shown and here one can proceed to those questions.
 */
class StartOfQuestionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartOfQuestionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartOfQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Picasso.get().load("file:///android_asset/eMission.png").resize(
            500,
            500
        ).into(binding.logoImageView)

        binding.startQuestionsButton.setOnClickListener{
            val intent = Intent(this, QuestionPagesActivity::class.java)
            intent.putExtra(Utils.PREF_REGENERATE, true)
            startActivity(intent)
        }
    }
}
