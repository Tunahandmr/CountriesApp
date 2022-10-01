package com.tunahan.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tunahan.countriesapp.model.Country
import com.tunahan.countriesapp.service.CountryAPIService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedViewModel : ViewModel() {

    private val countryAPIService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        getDataFromAPI()
    }

    private fun getDataFromAPI(){

        countryLoading.value = true

        disposable.add(countryAPIService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                override fun onSuccess(t:List<Country>) {
                    countries.value = t
                    countryError.value = false
                    countryLoading.value = false
                }

                override fun onError(e: Throwable) {
                    countryError.value = true
                    countryLoading.value = false
                    e.printStackTrace()
                }

            })

        )


    }
}