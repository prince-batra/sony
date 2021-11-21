package com.example.sonyliv.viewmodels

import com.example.sonyliv.networking.LocalizationRepository.getLocalization
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.sonyliv.model.LocalizationResponse
import androidx.lifecycle.LiveData
import com.example.sonyliv.constants.UrlConstants

class LocalizationViewModel : ViewModel() {
    private lateinit var mutableLiveData: MutableLiveData<LocalizationResponse>

    fun init() {
        mutableLiveData = getLocalization(UrlConstants.ENGLISH)
    }

    val dataSource: LiveData<LocalizationResponse?>
        get() = mutableLiveData



    fun onButtonClick(string: String){
        mutableLiveData.value = LocalizationResponse("")
        mutableLiveData.value = getLocalization(string).value
    }

}