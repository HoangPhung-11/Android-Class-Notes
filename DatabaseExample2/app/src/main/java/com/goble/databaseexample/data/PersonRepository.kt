package com.goble.databaseexample.data

import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {
    fun getPersonById(id: Int): Flow<Person> = personDao.getPersonById(id)

    fun getContacts(): Flow<List<Person>> = personDao.getAllPeople()

    suspend fun updateContact(person:Person) = personDao.updatePerson(person)

    suspend fun deleteContact(person:Person) = personDao.deletePerson(person)

    suspend fun addContact(person:Person) = personDao.addPerson(person)
}