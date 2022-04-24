package com.example.lab_2_anime.DataBaseRoom.Country

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CountryDB (
    @PrimaryKey val id: Int,
    val name: String?
)