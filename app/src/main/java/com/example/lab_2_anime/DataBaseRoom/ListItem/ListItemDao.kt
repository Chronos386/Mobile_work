package com.example.lab_2_anime.DataBaseRoom.ListItem
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ListItemDao {
    @Query("SELECT * FROM ListItemDB")
    fun getAll(): List<ListItemDB>

    @Query("SELECT * FROM ListItemDB WHERE id = :first")
    fun findByID(first: Int): ListItemDB

    @Query("SELECT * FROM ListItemDB WHERE kinopoiskID = :first")
    fun findByKPID(first: Int): ListItemDB

    @Insert
    fun insert(item: ListItemDB)

    @Delete
    fun delete(item: ListItemDB)
}