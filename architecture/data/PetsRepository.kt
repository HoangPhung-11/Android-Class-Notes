package com.example.petstore.data

interface PetsRepository {
    fun getPets(): List<Pet>
}