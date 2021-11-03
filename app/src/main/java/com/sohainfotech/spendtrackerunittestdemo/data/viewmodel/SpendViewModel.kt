package com.sohainfotech.spendtrackerunittestdemo.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sohainfotech.spendtrackerunittestdemo.data.room.Spend
import kotlinx.coroutines.launch
import java.util.*

/**
 *If you want to test your ViewModels using JUnit,
 *  you must try to keep your ViewModels independent from Android Specific Classes.
 */

/**
 * But, it’s not always possible to keep ViewModels independent like this,
 * which is why we will learn about Roboelectric as well.
 * That will help us to run this kind of test without using an emulator.
 */

class SpendViewModel(
    private val dataSource: SpendsTrackerDataSource
) : ViewModel() {

    private val _last20SpendsLiveData = MutableLiveData<List<Spend>>()

    //It will be use when we perform ViewModel test
    val last20SpendsLiveData: LiveData<List<Spend>>
    get() = _last20SpendsLiveData

    fun addSpend(amount: Int, description: String) = viewModelScope.launch {
        dataSource.addSpend(Spend(Date(), amount, description))
    }

    fun getLast20Spends() = viewModelScope.launch {
        //LiveData which publicly exposes setValue(T) and postValue(T) method.
        _last20SpendsLiveData.value = dataSource.getLast20Spends()
    }
}