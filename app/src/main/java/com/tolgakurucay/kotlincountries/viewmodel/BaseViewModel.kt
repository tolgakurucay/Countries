package com.tolgakurucay.kotlincountries.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
//fragment veya activity context yerine androidviewmodel contexti kullanılır(app contexti)

abstract class  BaseViewModel(application : Application) :  AndroidViewModel(application) , CoroutineScope
{
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main// işi yap sonra mainThread'e dön

    override fun onCleared() {
        super.onCleared()
        job.cancel()//app context gidilirse iptal
    }


}