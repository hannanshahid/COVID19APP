package com.example.covid_19_app.Models

data class Pakistanidata (

    var updated : Long,
    var country : String,
    var countryInfo : CountryInfo,
    var cases : Int,
    var todayCases : Int,
    var deaths : Int,
    var todayDeaths : Int,
    var recovered : Int,
    var active : Int,
    var critical : Int,
    var casesPerOneMillion : Int,
    var deathsPerOneMillion : Int,
    var tests : Int,
    var testsPerOneMillion : Int,
    var continent : String
)