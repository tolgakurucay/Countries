package com.tolgakurucay.kotlincountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tolgakurucay.kotlincountries.model.Country


@Database(entities = arrayOf(Country::class), version = 1)
//bunu oluşturmadaki amaç veritabanından sadece 1 obje oluşturulmasını sağlamak için
//farklı yerlerden, threadlerden tek bir veritabanına ulaşılmaya çalışılırsa çakışma olacaktır
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao() : CountryDAO

    //Singleton   içerisinden tek bir obje oluşturulabiilir

    companion object{//diğer scopelardan ulaşılmayı sağlar companion object
        @Volatile private var instance:CountryDatabase?=null//volatile dediğimiz zaman bu obje diğer threadlerde de görünür hale geliyor

        private val lock=Any()

        operator fun invoke(context: Context) = instance?: synchronized(lock){//instance var mı yok mu kontrol et
            instance ?: makeDatabase(context).also {
                instance=it
            }//varsa instance dönüyor, yoksa makeDatabase'yi çağır ve senkronize yaparak farklı threadlerden çağırıldığı takdirde tek database döndür
        }
        //senkronize dediğimiz tek bir thread tarafından ulaşılma işlemidir


        private fun makeDatabase(context: Context) =
            Room.databaseBuilder(
            context.applicationContext,CountryDatabase::class.java,"countrydatabase"
        ).build()

    }




}