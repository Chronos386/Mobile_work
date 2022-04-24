package com.example.lab_2_anime.DataBaseRoom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab_2_anime.DataBaseRoom.Connections.*
import com.example.lab_2_anime.DataBaseRoom.Country.*
import com.example.lab_2_anime.DataBaseRoom.FilmData.*
import com.example.lab_2_anime.DataBaseRoom.Genre.*
import com.example.lab_2_anime.DataBaseRoom.ListItem.*

@Database(entities = [CountryDB::class, GenreDB::class, FilmDataDB::class, ListItemDB::class, ConnCountryFilm::class, ConnCountryItem::class, ConnGenreFilm::class, ConnGenreItem::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun genreDao(): GenreDao
    abstract fun filmDataDao(): FilmDataDao
    abstract fun listItemDao(): ListItemDao
    abstract fun countryFilmDao(): CountryFilmDao
    abstract fun countryItemDao(): CountryItemDao
    abstract fun genreFilmDao(): GenreFilmDao
    abstract fun genreItemDao(): GenreItemDao
}