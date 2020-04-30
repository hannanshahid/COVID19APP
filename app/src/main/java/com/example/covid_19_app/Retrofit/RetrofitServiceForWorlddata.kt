package com.example.covid_19_app.Retrofit

import com.example.covid_19_app.Models.casedata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServiceForWorlddata {
    @GET
    fun getcasesdataService(@Url url:String): Call<casedata>
}