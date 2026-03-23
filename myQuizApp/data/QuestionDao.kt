package com.example.quizdatabaseapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Update
    suspend fun updateQuestion(question: Question)

    @Query("SELECT * FROM questions WHERE id = :id")
    fun getQuestionById(id:Int): Flow<Question>

    @Query("SELECT * FROM questions WHERE category = :category")
    fun getQuestionByCategory(category: Category): Flow<List<Question>>
}