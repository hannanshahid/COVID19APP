package com.example.covid_19_app.common

import com.example.covid_19_app.Retrofit.RetrofitServiceForpak
import com.example.covid_19_app.Retrofit.Retrofitclient

object CommonFroPakdata {
    private val GOOGLE_API_URL="https://corona.lmao.ninja/v2/countries/Pakistan/"
    public fun getpakdatasdata(): RetrofitServiceForpak
    {
        return  Retrofitclient.getClient(GOOGLE_API_URL).create(RetrofitServiceForpak::class.java)


    }
}