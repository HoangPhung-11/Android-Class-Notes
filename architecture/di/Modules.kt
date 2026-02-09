package com.example.petstore.di

import com.example.petstore.data.PetsRepository
import com.example.petstore.data.PetsRepositoryImpl
import com.example.petstore.viewmodel.PetsViewModel
import org.koin.dsl.module

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl() }
    single { PetsViewModel(get()) }
}