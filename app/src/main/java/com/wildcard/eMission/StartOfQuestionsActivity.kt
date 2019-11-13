package fi.metropolia.wildcard.emission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wildcard.eMission.R
import kotlinx.android.synthetic.main.activity_start_of_questions.*

class StartOfQuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_of_questions)

        start_questions_button.setOnClickListener{
            val intent = Intent(this,QuestionPagesActivity::class.java)
            startActivity(intent)
        }
    }
}
