package com.sohainfotech.spendtrackerunittestdemo.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sohainfotech.spendtrackerunittestdemo.data.room.Spend
import com.sohainfotech.spendtrackerunittestdemo.data.room.SpendDao
import com.sohainfotech.spendtrackerunittestdemo.data.room.SpendsDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
//import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*


@RunWith(AndroidJUnit4::class)
//class SpendsDatabaseTest : TestCase(){
class SpendsDatabaseTest {

    private lateinit var db: SpendsDatabase
    private lateinit var dao: SpendDao


    @Before
    //public override fun setUp() {
    fun setUp() {
        //get context
        val context = ApplicationProvider.getApplicationContext<Context>()
        //Creates a RoomDatabase.Builder for an in memory database.
        // Information stored in an in memory database disappears when the process is killed.
        db = Room.inMemoryDatabaseBuilder(
            context,
            SpendsDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
        //get dao
        dao = db.getSpendDao()

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadSpend() = runBlocking {
        val date = Date()
        val spend = Spend(date, 100, "for Bread")
        //ad spend to db
        dao.addSpend(spend)

        //get spends from db
        val spends = dao.getLast20Spends()
        //assertThat(spends.contains(spend)).isTrue()
        assertThat(spends.contains(spend)).isTrue()

    }
}