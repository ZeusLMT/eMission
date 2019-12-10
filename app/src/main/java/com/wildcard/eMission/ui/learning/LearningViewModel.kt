package com.wildcard.eMission.ui.learning

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wildcard.eMission.model.Learning
import com.wildcard.eMission.model.LearningTier

class LearningViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Learning Space Fragment"
    }
    val text: LiveData<String> = _text

    val learningsList = MutableLiveData<ArrayList<Learning>>()

    fun getLearningsList(): ArrayList<Learning> {
        val learnings = ArrayList<Learning>()

        learnings.add(
            Learning(
                rId = "01",
                name = "test1",
                description = "just testing 1",
                tier = LearningTier.BACKGROUND
            )
        )

        learnings.add(
            Learning(
                rId = "02",
                name = "testing too",
                description = "testing testing",
                tier=LearningTier.HOME_APPLIANCES
            )
        )

        learnings.add(
            Learning(
                rId="03",
                name="will it crash",
                description = "wonder will it crash",
                tier=LearningTier.HOME_APPLIANCES
            )
        )

        learnings.add(
            Learning(
                rId = "03",
                name = "test 2",
                description = "just testing 2",
                tier = LearningTier.TRAVELING
            )
        )

        return learnings
    }
}