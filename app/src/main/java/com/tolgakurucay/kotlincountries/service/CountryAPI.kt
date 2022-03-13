package com.tolgakurucay.kotlincountries.service

import com.tolgakurucay.kotlincountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //BASE URL
    //https://raw.githubusercontent.com/

    //EXTENSION URL
    //atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json


    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries():Single<List<Country>>

}