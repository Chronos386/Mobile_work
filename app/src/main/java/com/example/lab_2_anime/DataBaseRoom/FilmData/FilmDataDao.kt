package com.example.lab_2_anime.DataBaseRoom.FilmData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FilmDataDao {
    @Query("SELECT * FROM FilmDataDB")
    fun getAll(): List<FilmDataDB>

    @Query("SELECT * FROM FilmDataDB WHERE id = :first")
    fun findByID(first: Int): FilmDataDB

    @Query("SELECT COUNT(*) FROM FilmDataDB")
    fun findCount(): Int

    @Query("SELECT * FROM FilmDataDB WHERE kinopoiskID = :first")
    fun findByKPID(first: Long): FilmDataDB

    @Insert
    fun insert(film: FilmDataDB)

    @Delete
    fun delete(film: FilmDataDB)
}