package com.sohainfotech.spendtrackerunittestdemo.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//step: 1.3 Database

@Database(entities = [Spend::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class SpendsDatabase : RoomDatabase() {

    abstract fun getSpendDao(): SpendDao

    companion object{
        private const val DB_NAME = "Spends-Database.db"

        @Volatile  //meaning that writes to this field are immediately made visible to other threads.
        private var instance: SpendsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            SpendsDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()

    }
}
