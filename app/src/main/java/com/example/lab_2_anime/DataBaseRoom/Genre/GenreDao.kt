package com.example.lab_2_anime.DataBaseRoom.Genre
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GenreDao {
    @Query("SELECT * FROM GenreDB")
    fun getAll(): List<GenreDB>

    @Query("SELECT * FROM GenreDB WHERE id = :first")
    fun findByID(first: Int): GenreDB

    @Query("SELECT * FROM GenreDB WHERE name = :first")
    fun findByName(first: String): GenreDB

    @Query("SELECT COUNT(*) FROM GenreDB")
    fun findCount(): Int

    @Insert
    fun insert(genre: GenreDB)

    @Delete
    fun delete(genre: GenreDB)
}