package com.sohainfotech.spendtrackerunittestdemo.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//step: 1.1 Entity
/**
 * As we are using a Date type here that is not supported by room;
 * we need to make a TypeConverter as well.
 */

@Entity(tableName = "spends")
data class Spend(
    val date: Date,
    val amount: Int,
    val description: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
