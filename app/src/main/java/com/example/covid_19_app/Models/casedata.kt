package com.example.covid_19_app.Models

data class casedata (



    val updated : Long,
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
    val testsPerOneMillion : Double,
    val affectedCountries : Int

)