package com.goble.databaseexample.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM people WHERE id = :id")
    fun getPersonById(id:Int): Flow<Person>

    @Query("SELECT * FROM people ORDER BY name DESC")
    fun getAllPeople(): Flow<List<Person>>

    @Query("SELECT * FROM people ORDER BY phone ASC")
    fun getPeopleByPhone(): Flow<List<Person>>

    @Update
    suspend fun updatePerson(person:Person)

    @Delete
    suspend fun deletePerson(person:Person)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerson(person:Person)
}