package com.example.quizdatabaseapp.data

object CategoryRepository {
    fun getCategories(): List<Category> = Category.entries
}