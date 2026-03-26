package com.goble.databaseexample.data

data class ContactState(
    val contacts: List<Person> = emptyList(),
    val name:String = "",
    val age:Int = 0,
    val phone:String = "",
    val sortType: SortType = SortType.NAME
)
