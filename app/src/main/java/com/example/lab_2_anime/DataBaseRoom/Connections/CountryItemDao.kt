package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CountryItemDao {
    @Query("SELECT * FROM ConnCountryItem")
    fun getAll(): List<ConnCountryItem>

    @Query("SELECT * FROM ConnCountryItem WHERE id = :first")
    fun findByID(first: Int): ConnCountryItem

    @Query("SELECT COUNT(*) FROM ConnCountryItem")
    fun findCount(): Int

    @Query("SELECT * FROM ConnCountryItem WHERE item_id = :first")
    fun findItemCountry(first: Int): List<ConnCountryItem>

    @Insert
    fun insert(item: ConnCountryItem)

    @Delete
    fun delete(item: ConnCountryItem)
}