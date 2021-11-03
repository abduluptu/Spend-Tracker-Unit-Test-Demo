package com.sohainfotech.spendtrackerunittestdemo.data.viewmodel

import com.sohainfotech.spendtrackerunittestdemo.data.room.Spend
import com.sohainfotech.spendtrackerunittestdemo.data.room.SpendDao

/**
 * It is a Repository
 */

/**
 * As you can see, our DataSource requires the SpendDao, that we tested in the last post.
 * To get SpendDao we need the Database Instance. Building the Database Instance requires Context,
 * an Android-specific class; hence, we cannot do a normal Unit Test.
 */

class SpendsTrackerDataSource(
    private val db: SpendDao
) {

    //To insert a new spend into the database
    suspend fun addSpend(spend: Spend) = db.addSpend(spend)

    //To get the last 20 saved spend from the database.
    suspend fun getLast20Spends() = db.getLast20Spends()
}