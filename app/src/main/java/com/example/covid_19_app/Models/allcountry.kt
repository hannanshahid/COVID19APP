package com.example.covid_19_app.Models

data class allcountry(
    val updated : Long,
    val country : String,
    val countryInfo : CountryInfo,
    val cases : Int,
    val todayCases : Int,
    val deaths : Int,
    val todayDeaths : Int,
    val recovered : Int,
    val active : Int,
    val critical : Int,
    val casesPerOneMillion : Int,
    val deathsPerOneMillion : Int,
    val tests : Int,
    val testsPerOneMillion : Int,
    val continent : String
)