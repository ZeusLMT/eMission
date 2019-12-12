package com.wildcard.eMission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_start_of_questions.*

class StartOfQuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_of_questions)

        Picasso.get().load("file:///android_asset/eMission.png").resize(
            500,
            500
        ).into(logo_imageView)

        start_questions_button.setOnClickListener{
            val intent = Intent(this, QuestionPagesActivity::class.java)
            intent.putExtra(Utils.PREF_REGENERATE, true)
            startActivity(intent)
        }
    }
}
