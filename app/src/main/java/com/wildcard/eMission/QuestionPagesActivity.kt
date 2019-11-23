package fi.metropolia.wildcard.emission

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.wildcard.eMission.R
import com.wildcard.eMission.YouMayStartActivity
import kotlinx.android.synthetic.main.activity_question_pages.*

// interfaces are for getting information from fragments to activity
class QuestionPagesActivity : AppCompatActivity(), ToTransportationDelecate, ToDietDelegate, ReturnToMainActivityDelegate {

    private val amountOfTabs = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_pages)


        //adding the tabs to the tab layout
        for(i in 0 until amountOfTabs){
            questions_view_tablayout.addTab(questions_view_tablayout.newTab().setIcon(R.drawable.tab_selector))
        }

        //to disable of the clicking of tabs, tabs are meant to change only, when question is answered
        //https://inneka.com/programming/android/disable-tablayout/
        val tabStrip: LinearLayout = questions_view_tablayout.getChildAt(0) as LinearLayout
        tabStrip.isEnabled = false
        for(i in 0..(tabStrip.childCount-1)){
           tabStrip.getChildAt(i).isClickable = false
        }

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

        changeTab(1)

    }

    override fun toDietFragment() {

        val dietFragment = DietFragment()

        supportFragmentManager.beginTransaction()
                .replace(R.id.questions_view_fragment_container,dietFragment)
                .commit()

        changeTab(2)
    }

    //after last question fragment return to main activity
    override fun returnToMainActivity() {

        val intent = Intent(this, YouMayStartActivity::class.java)
        startActivity(intent)
    }

    private fun changeTab(tab_index: Int){
        val tab: TabLayout.Tab? = questions_view_tablayout.getTabAt(tab_index)
        tab?.select()
    }



}


