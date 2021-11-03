package com.sohainfotech.spendtrackerunittestdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * This function is to get the value from LiveData.
 */

fun <T> LiveData<T>.getOrAwaitValue(

    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserver: () -> Unit = {}

): T {

    var data: T? = null

    //wait for 1 thread only
    val latch = CountDownLatch(1)

    //create Observer as an anonymous inner class
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            //get the live data value and set value to data
            data = t
            //remove observer
            this@getOrAwaitValue.removeObserver(this)
            //stop waiting for Liva Data value
            latch.countDown()
        }

    }

    //attach observer to Live Data
    this.observeForever(observer)

    try {
        afterObserver.invoke()

        // wait until LiveData gets a value only for 2 seconds
        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData never gets its value")
        }
    } finally {
        //remove observer if exception throws
        this.removeObserver(observer)
    }

    //@Suppress("UNCHECKED_CAST")

    return data as T
}