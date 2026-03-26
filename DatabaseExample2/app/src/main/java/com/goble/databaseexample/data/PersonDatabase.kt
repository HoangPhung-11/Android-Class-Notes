package com.goble.databaseexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var instance: PersonDatabase? = null

        fun getDatabase(context: Context):PersonDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    PersonDatabase::class.java,
                    name = "sample_database"
                ).build()
                    .also { instance = it }
            }
        }
    }
}