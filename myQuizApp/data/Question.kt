package com.example.quizdatabaseapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val category: Category,
    val question: String,
    val options: List<String>,
    val answer:Int
)

data class Question(
    val id:Int = 0,
    val category: String,
    val question: String,
    val options: List<String>,
    val answer: Int
)