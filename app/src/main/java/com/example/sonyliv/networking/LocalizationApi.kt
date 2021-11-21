package com.example.sonyliv.networking

import retrofit2.http.GET
import com.example.sonyliv.model.LocalizationResponse
import retrofit2.Call
import retrofit2.http.Path

interface LocalizationApi {
    @GET("{language}")
    fun getLocalization(@Path("language") language: String ): Call<LocalizationResponse>
}