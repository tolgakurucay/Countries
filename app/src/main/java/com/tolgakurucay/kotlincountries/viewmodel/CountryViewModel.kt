package com.tolgakurucay.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.kotlincountries.model.Country
import com.tolgakurucay.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application):BaseViewModel(application) {

    val countryLiveData=MutableLiveData<Country>()


    fun getDataFromRoom(uuid: Int){


        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country=dao.getCountry(uuid)

            countryLiveData.value=country



        }






    }
}