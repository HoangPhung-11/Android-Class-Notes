package com.example.petstore.viewmodel

import androidx.lifecycle.ViewModel
import com.example.petstore.data.PetsRepository

class PetsViewModel(
    private val petsRepository: PetsRepository
): ViewModel() {
    fun getPets() = petsRepository.getPets()
}