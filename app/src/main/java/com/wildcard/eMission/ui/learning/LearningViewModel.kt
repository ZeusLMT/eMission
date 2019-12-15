package com.wildcard.eMission.ui.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Learning
import com.wildcard.eMission.model.LearningTier

/**
 * This is used to show the data in learning fragment.
 * Now there is hardcoded example text, but the content should come from server.
 */
class LearningViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Learning Space Fragment"
    }
    val text: LiveData<String> = _text

    val learningsList = MutableLiveData<ArrayList<Learning>>()

    //examples to show in learning-section
    fun getLearningsList(): ArrayList<Learning> {
        val learnings = ArrayList<Learning>()

        learnings.add(
            Learning(
                rId = "01",
                name = "Human influence",
                name_fin = "Ihmisen vaikutus",
                description = "Already in year 1896 Swedish scientist Svante Arrhenius proposed theory that humans can make greenhouse effect stronger with certain emissions." +
                        "The greenhouse effect was first discovered in 1824 by Joseph Fourier.",
                description_fin = "Jo vuonna 1896 ruotslainen tiedemies Svante Arrhenius esitti teorian, että ihminen voi voimistaa kasvihuoneilmiötä toimillaan." +
                        "Kasvihuoneilmiön havaitsi Joseph Fourier ensimmäisen kerran vuonna 1824.",
                tier = LearningTier.BACKGROUND
            )
        )

        learnings.add(
            Learning(
                rId = "02",
                name = "Energy labels",
                name_fin = "Energialuokat",
                description = "When one is buying home appliances it is good to check energy consumption. There can be energy labels from A to G to express this. G means highest consumption. " +
                        "Sometimes there is even labels A+ and A++",
                description_fin = "Ostaessasi kodinkonetta on hyvä tarkistaa energiankulutus. Se voidaan ilmaista kirjaimilla A:sta G:hen, " +
                        "G:n ollessa energiaa tuhlaavin. Joskus on käytössä myös A+ ja A++",
                tier=LearningTier.HOME_APPLIANCES
            )
        )

        learnings.add(
            Learning(
                rId="03",
                name="Ice in the freezer",
                name_fin = "Jäinen pakastin",
                description = "If there is thick layer of ice in freezer the energy consumption will be higher. It wood be good to defrost the freezer once a year.",
                description_fin = "Jos pakastimessa on paksu jääkerros, tämä kasvattaa sen energiankulutusta. Tästä syystä olisi hyvä sulattaa jäät pois pakastimesta kerran vuodessa.",
                tier=LearningTier.HOME_APPLIANCES
            )
        )


        return learnings
    }
}