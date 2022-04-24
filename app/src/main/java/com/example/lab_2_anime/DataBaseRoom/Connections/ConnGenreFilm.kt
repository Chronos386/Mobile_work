package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lab_2_anime.DataBaseRoom.FilmData.FilmDataDB
import com.example.lab_2_anime.DataBaseRoom.Genre.GenreDB


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GenreDB::class,
            parentColumns = ["id"],
            childColumns = ["genre_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FilmDataDB::class,
            parentColumns = ["id"],
            childColumns = ["film_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class ConnGenreFilm (
    @PrimaryKey val id: Int,
    val genre_id: Int,
    val film_id: Int
)