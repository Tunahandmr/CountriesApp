package com.tunahan.countriesapp.service

import com.tunahan.countriesapp.model.Country
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL -> https://raw.githubusercontent.com/
    //EXT -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    //fun getCountries():Observable<List<Country>>
    fun getCountries():Single<List<Country>>
}