package com.sohainfotech.spendtrackerunittestdemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//step: 1.2 Dao

@Dao
interface SpendDao {

    //To insert a new spend into the database
    @Insert
    suspend fun addSpend(spend: Spend)

    //To get the last 20 saved spend from the database.
    @Query("SELECT * FROM spends ORDER BY date DESC LIMIT 20")
    suspend fun getLast20Spends(): List<Spend>

}