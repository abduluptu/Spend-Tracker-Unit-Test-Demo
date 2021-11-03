package com.sohainfotech.spendtrackerunittestdemo.data.room

import androidx.room.TypeConverter
import java.util.*

//step: 1.1 Entity part2
class DateConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date?{
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}