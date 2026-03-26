package com.goble.databaseexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "people")
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name:String,
    val age:Int,
    val phone:String
)