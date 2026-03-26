package com.goble.databaseexample.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.goble.databaseexample.data.ContactEvent
import com.goble.databaseexample.data.ContactState
import com.goble.databaseexample.data.Person
import com.goble.databaseexample.data.PersonDao
import com.goble.databaseexample.data.PersonRepository
import com.goble.databaseexample.data.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PersonViewModel(private val dao: PersonDao): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _state = MutableStateFlow(ContactState())
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _contact = _sortType.flatMapLatest { sortType ->
        when(sortType) {
            SortType.NAME -> dao.getAllPeople()
            SortType.PHONE_NUMBER -> dao.getPeopleByPhone()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _sortType, _contact) { state, sortType, contact ->
        state.copy(
            contacts = contact,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun onEvent(event: ContactEvent) {
        when(event) {
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deletePerson(event.contact)
                }
            }
            ContactEvent.SaveContact -> {
                val name = state.value.name
                val age = state.value.age
                val phone = state.value.phone

                if (name.isBlank() || phone.isBlank()) {
                    return
                }

                val person = Person(
                    name = name,
                    age = age,
                    phone = phone
                )

                viewModelScope.launch {
                    dao.addPerson(person)
                }
            }

            is ContactEvent.SetAge -> {
                _state.update { it.copy(
                    age = event.age
                )}
            }
            is ContactEvent.SetName -> {
                _state.update { it.copy(
                    name = event.name
                )}
            }
            is ContactEvent.SetPhone -> {
                _state.update { it.copy(
                    phone = event.phone
                )}
            }
            is ContactEvent.SortContacts -> {
                _sortType.value = event.sortType
            }
        }
    }

    companion object {
        fun factory(dao: PersonDao): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return PersonViewModel(dao) as T
                }
            }
        }
    }
}