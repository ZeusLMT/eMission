package com.wildcard.eMission

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YouViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is you Fragment"
    }
    val text: LiveData<String> = _text
}