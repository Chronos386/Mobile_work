package com.example.lab_2_anime.DataBaseRoom.ListItem
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListItemDB (
    @PrimaryKey val id: Int,
    val kinopoiskID: Long? = null,
    val imdbID: String? = null,
    val nameRu: String? = null,
    val nameEn: String? = null,
    val nameOriginal: String? = null,

    val ratingKinopoisk: Double? = null,
    val ratingImdb: Double? = null,
    val year: Long? = null,
    val type: String? = null,
    val posterURL: String? = null,
    val posterURLPreview: String? = null
)