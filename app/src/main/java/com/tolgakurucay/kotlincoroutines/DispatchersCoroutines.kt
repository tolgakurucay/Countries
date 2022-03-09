package com.tolgakurucay.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main()  {

    //Dispatchers
    //Dispatchers.Default -> CPU Intensive(Görsel işleme vb işlemci kullanıcı işlemler)
    //Dispatchers.IO -> Input/Output, Networking
    //Dispatchers.Main -> UI örneğin internetten veri çekildi veya görsel işlendi, main sayesinde kullanıcıya gösterilebiliyor
    //Dispatchers.Unconfined -> Inherited dispatcher


    runBlocking {
        launch(Dispatchers.Main) {
            println("Main Thread: ${Thread.currentThread().name}")

        }
        launch(Dispatchers.IO) {
            println("IO Thread : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) {
            println("Default Thread: ${Thread.currentThread().name}")

        }
        launch(Dispatchers.Unconfined) {
            println("Unconfined Thread : ${Thread.currentThread().name}")
        }

    }


}