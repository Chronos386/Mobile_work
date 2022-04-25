package com.example.lab_2_anime.DataSource
import com.beust.klaxon.Klaxon
private val klaxon = Klaxon()

class FilmList (
    val total: Long? = null,
    val totalPages: Long? = null,
    val items: List<ListItem>
) {
    public fun toJson() = klaxon.toJsonString(this)
    companion object {
        public fun fromJson(json: String) = klaxon.parse<FilmList>(json)
    }
}