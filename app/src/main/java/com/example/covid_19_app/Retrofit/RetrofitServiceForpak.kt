package com.example.covid_19_app.Retrofit

import com.example.covid_19_app.Models.Pakistanidata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitServiceForpak {
    @GET
    fun getpakdataService(@Url url:String): Call<Pakistanidata>
}