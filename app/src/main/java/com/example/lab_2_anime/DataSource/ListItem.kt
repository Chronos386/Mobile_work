package com.example.lab_2_anime.DataSource

import com.beust.klaxon.Json
import java.io.Serializable

data class ListItem (
    @Json(name = "kinopoiskId")
    val kinopoiskID: Long? = null,

    @Json(name = "imdbId")
    val imdbID: String? = null,

    val nameRu: String? = null,
    val nameEn: String? = null,
    val nameOriginal: String? = null,
    val countries: List<Country>,
    val genres: List<Genre>,
    val ratingKinopoisk: Double? = null,
    val ratingImdb: Double? = null,
    val year: Long? = null,
    val type: String? = null,

    @Json(name = "posterUrl")
    val posterURL: String? = null,

    @Json(name = "posterUrlPreview")
    val posterURLPreview: String? = null
) : Serializable