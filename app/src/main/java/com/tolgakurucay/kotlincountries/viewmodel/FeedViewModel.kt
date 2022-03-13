package com.tolgakurucay.kotlincountries.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tolgakurucay.kotlincountries.model.Country
import com.tolgakurucay.kotlincountries.service.CountryAPIService
import com.tolgakurucay.kotlincountries.service.CountryDatabase
import com.tolgakurucay.kotlincountries.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FeedViewModel(application: Application) : BaseViewModel(application) {

    //farklı farklı sınıflarda coroutine kullanılacaksa yeni bir sınıfta yapmak daha mantıklı

    private val countryAPIService=CountryAPIService()
    private val disposable=CompositeDisposable()
    private var customPreferences=CustomSharedPreferences(getApplication<Application>().applicationContext)
    private var refreshTime=10*60*1000*1000*1000L//10 dk

    val countries=MutableLiveData<List<Country>>()//değiştirilebilir canlı veri
    val countryError=MutableLiveData<Boolean>()
    val countryLoading=MutableLiveData<Boolean>()



    fun refreshData(){
        val updateTime=customPreferences.getTime()
        if (updateTime!=null && updateTime!=0L && System.nanoTime() - updateTime<refreshTime){
           getDataFromSQLite()
        }
        else
        {
            getDataFromAPI()
        }



    }

    fun refreshFromAPI(){
        getDataFromAPI()
    }

    private fun getDataFromSQLite(){

        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            showCountries(countries)
            Toast.makeText(getApplication(),"Countries From SQLite",Toast.LENGTH_SHORT).show()
        }
    }


    private fun getDataFromAPI(){
        countryLoading.value=true
        disposable.add(
            countryAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {

                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"Countries From API",Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(e: Throwable) {
                        countryError.value=true
                        countryLoading.value=false
                        e.printStackTrace()

                    }

                })


        )

    }
    private fun showCountries(countryList: List<Country>){
        countries.value=countryList
        countryError.value=false
        countryLoading.value=false

    }
    //suspend fonksiyonlar coroutine içerisinde çağırılabilir

    private fun storeInSQLite(list: List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteFromCountry()
           val listLong= dao.insertAll(*list.toTypedArray())//-> diziden individual hale getiriyor
            var i=0
            while(i<list.size){
                list[i].uuid=listLong[i].toInt()
                i++
            }
            showCountries(list)

        }
        customPreferences.saveTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }



}





































