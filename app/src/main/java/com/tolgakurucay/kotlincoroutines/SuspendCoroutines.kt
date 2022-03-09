package com.tolgakurucay.kotlincoroutines

import kotlinx.coroutines.*

fun main() {
    println("Start")
    runBlocking {
        myFunction()
    }


}


suspend fun myFunction(){
    coroutineScope {
        delay(4000)
        println("suspend function")

    }
}