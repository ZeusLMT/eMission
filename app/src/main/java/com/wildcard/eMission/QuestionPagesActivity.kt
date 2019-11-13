package fi.metropolia.wildcard.emission

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.wildcard.eMission.MainActivity
import com.wildcard.eMission.R
import kotlinx.android.synthetic.main.activity_question_pages.*

// interfaces are for getting information from fragments to activity
class QuestionPagesActivity : AppCompatActivity(), ToTransportationDelecate, ToDietDelegate, ReturnToMainActivityDelegate {

    private val amoutOfTabs = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_pages)

        /*
        for(i in 0 until amoutOfTabs){
            questions_view_tablayout.addTab(questions_view_tablayout.newTab().setIcon(R.drawable.tab_selector))
        }*/

        val livingStyleFragment = LivingStyleFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.questions_view_fragment_container, livingStyleFragment)
                .commit()
    }


    override fun toTransportationFragment(fragment: LivingStyleFragment) {

        val transportationFragment = TransportationFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.questions_view_fragment_container,transportationFragment)
                .commit()
    }

    override fun toDietFragment() {

        val dietFragment = DietFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.questions_view_fragment_container,dietFragment)
                .commit()
    }

    //after last question fragment return to main activity
    override fun returnToMainActivity() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}


