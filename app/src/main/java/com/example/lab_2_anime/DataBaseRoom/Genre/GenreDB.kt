package com.example.lab_2_anime.DataBaseRoom.Genre

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GenreDB (
    @PrimaryKey var id: Int,
    val name: String?
)
