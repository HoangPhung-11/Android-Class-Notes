package com.goble.databaseexample.data

sealed interface ContactEvent {
    object SaveContact: ContactEvent

    data class SetName(val name:String): ContactEvent
    data class SetAge(val age:Int): ContactEvent
    data class SetPhone(val phone:String): ContactEvent

    data class SortContacts(val sortType: SortType): ContactEvent
    data class DeleteContact(val contact:Person): ContactEvent
}