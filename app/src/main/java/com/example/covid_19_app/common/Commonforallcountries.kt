package com.example.covid_19_app.common

import com.example.covid_19_app.Retrofit.RetrofitServiceForpak
import com.example.covid_19_app.Retrofit.RetrofitServicesforAllcountries
import com.example.covid_19_app.Retrofit.Retrofitclient

object Commonforallcountries {
    private val GOOGLE_API_URL="https://corona.lmao.ninja/v2/countries/"
    public fun getpakdatasdata(): RetrofitServicesforAllcountries
    {
        return  Retrofitclient.getClient(GOOGLE_API_URL).create(RetrofitServicesforAllcountries::class.java)


    }
}