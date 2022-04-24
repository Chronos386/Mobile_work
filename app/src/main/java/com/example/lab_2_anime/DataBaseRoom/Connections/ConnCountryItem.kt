package com.example.lab_2_anime.DataBaseRoom.Connections

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.lab_2_anime.DataBaseRoom.Country.CountryDB
import com.example.lab_2_anime.DataBaseRoom.ListItem.ListItemDB

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = CountryDB::class,
            parentColumns = ["id"],
            childColumns = ["country_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ListItemDB::class,
            parentColumns = ["id"],
            childColumns = ["item_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class ConnCountryItem (
    @PrimaryKey val id: Int,
    val country_id: Int,
    val item_id: Int
)