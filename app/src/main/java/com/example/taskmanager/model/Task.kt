package com.example.taskmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    @PrimaryKey (autoGenerate = true)
    val id : Int? = null,
    var title: String? = null,
    var desc: String? = null
) : java.io.Serializable
