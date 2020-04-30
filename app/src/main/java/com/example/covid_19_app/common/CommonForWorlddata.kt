package com.example.covid_19_app.common

import com.example.covid_19_app.Retrofit.RetrofitServiceForWorlddata
import com.example.covid_19_app.Retrofit.Retrofitclient


object CommonForWorlddata {
    private val GOOGLE_API_URL="https://corona.lmao.ninja/v2/all/"
    public fun getcasesdata():RetrofitServiceForWorlddata
    {
       return  Retrofitclient.getClient(GOOGLE_API_URL).create(RetrofitServiceForWorlddata::class.java)


    }
}