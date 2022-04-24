package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lab_2_anime.DataBaseRoom.Genre.GenreDB
import com.example.lab_2_anime.DataBaseRoom.ListItem.ListItemDB

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GenreDB::class,
            parentColumns = ["id"],
            childColumns = ["genre_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ListItemDB::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class ConnGenreItem (
    @PrimaryKey val id: Int,
    val genre_id: Int,
    val item_id: Int
)