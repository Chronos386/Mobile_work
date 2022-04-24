package com.example.lab_2_anime.DataBaseRoom.Connections
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lab_2_anime.DataBaseRoom.Country.CountryDB
import com.example.lab_2_anime.DataBaseRoom.FilmData.FilmDataDB

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CountryDB::class,
            parentColumns = ["id"],
            childColumns = ["country_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FilmDataDB::class,
            parentColumns = ["id"],
            childColumns = ["film_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class ConnCountryFilm (
    @PrimaryKey val id: Int,
    val country_id: Int,
    val film_id: Int
)

