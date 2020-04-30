package com.example.covid_19_app.Models

data class CountryInfo(
    val _id : Int,
    val iso2 : String,
    val iso3 : String,
    val lat : Float,
    val long : Float,
    val flag : String
)