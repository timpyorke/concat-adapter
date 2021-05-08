package com.mangofactory.concatadapter.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val oneLiveData: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().apply {
            value =
                listOf("A1", "A2", "A3", "A4", "A5")
        }
    }

    val twoLiveData: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().apply {
            value =
                listOf("C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "C11", "C12")
        }
    }

    val horizontalLiveData: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().apply {
            value =
                listOf("B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8")
        }
    }
}