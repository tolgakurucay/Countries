package com.tolgakurucay.kotlincoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    var userName=""
    var userAge=0

    runBlocking {

        var age=async {
            downloadAge()
        }
        var name=async{
            downloadName()
        }


    userName=name.await()//atama işlemi için bitmesini bekleyecek
        userAge=age.await()



        println("${userName} ${userAge}")

    }
















}

suspend fun downloadName(): String{
    delay(2000)
    val username="tolga : "
    println("username download")
    return username

}

suspend fun downloadAge():Int{
    delay(4000)
    val age=35
    println("age download")
    return age
}