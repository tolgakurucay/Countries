package com.tolgakurucay.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Light weightness
        var i :Int=0
       /* println("global scope start")
        GlobalScope.launch {
           delay(5000)
            println("Global scope")
        }
        println("global scope end")
*/

/*
       println("runBlocking start")

        runBlocking {
            launch {
                delay(5000)
                println("run blocking")
            }
        }
        println("RunBlocking end")*/
    //coroutineScope
       println("start")
   CoroutineScope(Dispatchers.Default).launch {
       delay(5000)
       println("coroutineScope")

   }
        println("End")
/*
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
*/






    }
}