package com.example.covid_19_app.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofitclient {
    private var retrofit: Retrofit?=null
    public fun getClient(baseurll:String): Retrofit
    {
        if(retrofit==null)
        {
            retrofit= Retrofit.Builder()
                .baseUrl(baseurll)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}