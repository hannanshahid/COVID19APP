package com.example.covid_19_app.dataclasses

data class countries(


    var flag : String,
    var cases : Int,
    var todayCases : Int,
    var deaths : Int,
    var todayDeaths : Int,
    var recovered : Int,
    var active : Int,
    var critical : Int,
    var tests : Int,
    var country : String
)