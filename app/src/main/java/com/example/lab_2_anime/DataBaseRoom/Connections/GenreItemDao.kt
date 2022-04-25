package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GenreItemDao {
    @Query("SELECT * FROM ConnGenreItem")
    fun getAll(): List<ConnGenreItem>

    @Query("SELECT * FROM ConnGenreItem WHERE id = :first")
    fun findByID(first: Int): ConnGenreItem

    @Query("SELECT COUNT(*) FROM ConnGenreItem")
    fun findCount(): Int

    @Query("SELECT * FROM ConnGenreItem WHERE item_id = :first")
    fun findItemGenres(first: Int): List<ConnGenreItem>

    @Insert
    fun insert(item: ConnGenreItem)

    @Delete
    fun delete(item: ConnGenreItem)
}