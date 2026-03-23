package com.example.quizdatabaseapp

import android.app.Application
import com.example.quizdatabaseapp.data.QuestionDatabase
import com.example.quizdatabaseapp.data.QuestionRepository

class QuizApplication : Application() {
    val database by lazy { QuestionDatabase.getDatabase(this) }

    val repository by lazy { QuestionRepository(database.questionDao() ) }
}