package com.example.lab_2_anime.DataBaseRoom.Country
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryDao {
    @Query("SELECT * FROM CountryDB")
    fun getAll(): List<CountryDB>

    @Query("SELECT * FROM CountryDB WHERE id = :first")
    fun findByID(first: Int): CountryDB

    @Insert
    fun insert(country: CountryDB)

    @Delete
    fun delete(country: CountryDB)
}