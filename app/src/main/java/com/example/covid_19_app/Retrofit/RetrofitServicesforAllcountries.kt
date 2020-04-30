package com.example.covid_19_app.Retrofit

import com.example.covid_19_app.Models.allcountry
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServicesforAllcountries {
    @GET
    fun getallcountryService(@Url url:String): Call<List<allcountry>>
}