package com.example.quizdatabaseapp.ui.question

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizdatabaseapp.data.Category
import com.example.quizdatabaseapp.data.Question
import com.example.quizdatabaseapp.data.QuestionEntity
import com.example.quizdatabaseapp.data.QuestionRepository
import kotlinx.coroutines.flow.MutableStateFlow

class QuestionEntryViewModel(private val questionRepository: QuestionRepository): ViewModel() {
    var questionState by mutableStateOf(QuestionState())
        private set

    suspend fun saveQuestion() {
        questionRepository.insertQuestion(questionState.questionDetails.toQuestion())
    }

    fun updateUIState(questionDetails: QuestionDetails) {
        questionState = QuestionState(questionDetails = questionDetails)
    }

    companion object {
        fun factory(repository: QuestionRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return QuestionEntryViewModel(repository) as T
                }
            }
        }
    }
}

data class QuestionState(
    val questionDetails: QuestionDetails = QuestionDetails()
)

data class QuestionDetails(
    val id:Int = 0,
    val category: String = "",
    val question: String = "",
    val options: List<String> = emptyList(),
    val answer:Int = 0
)

fun QuestionDetails.toQuestion(): Question = Question(
    id = id,
    category = category,
    question = question,
    options = options,
    answer = answer
)

fun QuestionEntity.toQuestion() = Question(
    id = id,
    category = category.displayName,
    question = question,
    options = options,
    answer = answer
)

fun Question.toQuestionEntity() = QuestionEntity(
    id = id,
    category = when(category) {
        "Computer Science" -> Category.COMPSCI
        "Math" -> Category.MATH
        else -> Category.POPCULT
    },
    question = question,
    options = options,
    answer = answer
)