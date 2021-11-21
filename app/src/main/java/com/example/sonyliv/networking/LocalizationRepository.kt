package com.example.sonyliv.networking

import com.example.sonyliv.networking.RetrofitService.Companion.createService
import androidx.lifecycle.MutableLiveData
import com.example.sonyliv.model.LocalizationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LocalizationRepository {
    var localizationApi: LocalizationApi = createService(LocalizationApi::class.java)
    val localizationData = MutableLiveData<LocalizationResponse>()

    fun getLocalization(language: String): MutableLiveData<LocalizationResponse> {
        localizationApi.getLocalization(language).enqueue(object : Callback<LocalizationResponse> {
            override fun onResponse(
                call: Call<LocalizationResponse?>,
                response: Response<LocalizationResponse?>
            ) {
                if (response.isSuccessful) {
                    localizationData.value = response.body()
                } else {
                    localizationData.value = null
                }
            }

            override fun onFailure(call: Call<LocalizationResponse?>, t: Throwable) {
                localizationData.value = null
            }
        })
        return localizationData
    }
}