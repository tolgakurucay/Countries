package com.tolgakurucay.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tolgakurucay.kotlincountries.model.Country


@Dao
interface CountryDAO {

    //Data access object

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>


    //insert ->Insert into
    //suspend -> coroutine içerisinde çağırılıyor, pause & resume
    //vararg -> tekil objeyi farklı sayılarda verebilen keyword
    // List<Long> -> primary keys


    @Query("SELECT * FROM country")
    suspend fun getAllCountries() : List<Country>

    @Query("SELECT * FROM country where uuid= :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM COUNTRY")
    suspend fun deleteFromCountry()

    @Query("DELETE FROM COUNTRY WHERE uuid=:countryId")
    suspend fun deleteFromCountryWithId(countryId:Int)


}