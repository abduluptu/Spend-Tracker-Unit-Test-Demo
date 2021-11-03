package com.sohainfotech.spendtrackerunittestdemo.data.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sohainfotech.spendtrackerunittestdemo.data.room.SpendsDatabase
import com.sohainfotech.spendtrackerunittestdemo.getOrAwaitValue
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//import org.junit.Assert.*
@RunWith(AndroidJUnit4::class)
class SpendViewModelTest : TestCase() {

    private lateinit var viewModel: SpendViewModel
    private lateinit var db: SpendsDatabase

    /**
     * A JUnit Test Rule that swaps the background executor used by the Architecture Components
     * with a different one which executes each task synchronously.
     *You can use this rule for your host side tests that use Architecture Components.
     */
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()
        //get context
        val context = ApplicationProvider.getApplicationContext<Context>()
        //create database
        db = Room.inMemoryDatabaseBuilder(
            context, SpendsDatabase::class.java
        ).allowMainThreadQueries().build()

        //get SpendsTrackerDataSource
        val dataSource = SpendsTrackerDataSource(db.getSpendDao())
        //create ViewModel
        viewModel = SpendViewModel(dataSource)

    }

    @Test
    fun testSpendViewModel() {
        viewModel.addSpend(170, "Bought some flowers")
        viewModel.getLast20Spends()

        // This function is to get the value from LiveData.
        val result = viewModel.last20SpendsLiveData.getOrAwaitValue().find {
            it.amount == 170 && it.description == "Bought some flowers"
        }
        assertThat(result != null).isTrue()
    }
}