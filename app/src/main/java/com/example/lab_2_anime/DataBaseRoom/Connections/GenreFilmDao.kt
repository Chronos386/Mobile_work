package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GenreFilmDao {
    @Query("SELECT * FROM ConnGenreFilm")
    fun getAll(): List<ConnGenreFilm>

    @Query("SELECT * FROM ConnGenreFilm WHERE id = :first")
    fun findByID(first: Int): ConnGenreFilm

    @Query("SELECT * FROM ConnGenreFilm WHERE film_id = :first")
    fun findFilmGenres(first: Int): List<ConnGenreFilm>

    @Insert
    fun insert(item: ConnGenreFilm)

    @Delete
    fun delete(item: ConnGenreFilm)
}