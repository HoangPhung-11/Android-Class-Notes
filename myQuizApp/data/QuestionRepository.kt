package com.example.quizdatabaseapp.data

import kotlinx.coroutines.flow.Flow

class QuestionRepository(private val questionDao: QuestionDao) {
    fun getQuestionsByCategory(category: Category): Flow<List<Question>> = questionDao.getQuestionByCategory(category)

    fun getQuestionById(id:Int): Flow<Question> = questionDao.getQuestionById(id)

    suspend fun insertQuestion(question: Question) = questionDao.insertQuestion(question)

    suspend fun updateQuestion(question: Question) = questionDao.updateQuestion(question)
}