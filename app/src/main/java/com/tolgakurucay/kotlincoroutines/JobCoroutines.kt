package com.tolgakurucay.kotlincoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main () {

    //Job
    runBlocking {

        val myJob= launch {
            delay(2000)
            println("job")

            val secondJob=launch {
                delay(2000)
                println("job2")
            }

        }


        myJob.invokeOnCompletion {
            //bittiğinde ne yap
            println("my job end")
        }


        myJob.cancel()



    }




}