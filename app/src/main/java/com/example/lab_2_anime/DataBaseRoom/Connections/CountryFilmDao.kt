package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CountryFilmDao {
    @Query("SELECT * FROM ConnCountryFilm")
    fun getAll(): List<ConnCountryFilm>

    @Query("SELECT * FROM ConnCountryFilm WHERE id = :first")
    fun findByID(first: Int): ConnCountryFilm

    @Query("SELECT COUNT(*) FROM ConnCountryFilm")
    fun findCount(): Int

    @Query("SELECT * FROM ConnCountryFilm WHERE film_id = :first")
    fun findFilmCountry(first: Int): List<ConnCountryFilm>

    @Insert
    fun insert(item: ConnCountryFilm)

    @Delete
    fun delete(item: ConnCountryFilm)
}