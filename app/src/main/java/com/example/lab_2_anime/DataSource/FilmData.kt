package com.example.lab_2_anime.DataSource

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import java.io.Serializable
private val klaxon = Klaxon()

data class FilmData (
    @Json(name = "kinopoiskId")
    val kinopoiskID: Long,

    @Json(name = "imdbId")
    val imdbID: String? = null,

    val nameRu: String? = null,
    val nameEn: String? = null,
    val nameOriginal: String? = null,

    @Json(name = "posterUrl")
    val posterURL: String? = null,

    @Json(name = "posterUrlPreview")
    val posterURLPreview: String? = null,

    @Json(name = "coverUrl")
    val coverURL: String? = null,

    val reviewsCount: Long? = null,
    val ratingGoodReview: Double? = null,
    val ratingGoodReviewVoteCount: Long? = null,
    val ratingKinopoisk: Double? = null,
    val ratingKinopoiskVoteCount: Long?,
    val ratingImdb: Double? = null,
    val ratingImdbVoteCount: Double? = null,
    val ratingFilmCritics: Double? = null,
    val ratingFilmCriticsVoteCount: Double? = null,
    val ratingAwait: Double? = null,
    val ratingAwaitCount: Double? = null,

    @Json(name = "ratingRfCritics")
    val ratingRFCritics: Double? = null,

    @Json(name = "ratingRfCriticsVoteCount")
    val ratingRFCriticsVoteCount: Long? = null,

    @Json(name = "webUrl")
    val webURL: String? = null,

    val year: Long? = null,
    val filmLength: Long? = null,
    val slogan: String? = null,
    val description: String? = null,
    val shortDescription: String? = null,
    val editorAnnotation: String? = null,
    val isTicketsAvailable: Boolean? = null,
    val productionStatus: String? = null,
    val type: String? = null,

    @Json(name = "ratingMpaa")
    val ratingMPAA: String? = null,

    val ratingAgeLimits: String? = null,
    val countries: List<Country>,
    val genres: List<Genre>,
    val startYear: Long? = null,
    val endYear: Long? = null,
    val serial: Boolean? = null,
    val shortFilm: Boolean? = null,
    val completed: Boolean? = null,
    val hasImax: Boolean? = null,
    val has3D: Boolean? = null,
    val lastSync: String? = null
) : Serializable {
    public fun toJson() = klaxon.toJsonString(this)
    public fun fromJson(json: String) = klaxon.parse<FilmData>(json)
    companion object {
        public fun fromJson(json: String) = klaxon.parse<FilmData>(json)
    }
}