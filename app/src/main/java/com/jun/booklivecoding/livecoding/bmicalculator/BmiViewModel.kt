package com.jun.booklivecoding.livecoding.bmicalculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BmiViewModel: ViewModel() {
    private var bmi: MutableLiveData<BmiDTO> = MutableLiveData()

    fun loadBmiData() {
        Thread {
            bmi.postValue(PreferenceHandler.getData())
        }.start()
    }

    fun getBmiData(): LiveData<BmiDTO> {
        return bmi
    }
}

data class BmiDTO(val height: Int, val weight: Int)

object ViewModelFactory: ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.newInstance()
    }
}