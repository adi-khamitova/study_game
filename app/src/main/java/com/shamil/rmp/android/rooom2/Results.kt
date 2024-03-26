package com.example.game3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "results")
data class Results(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val playerName: String,
    val score: Int
)
