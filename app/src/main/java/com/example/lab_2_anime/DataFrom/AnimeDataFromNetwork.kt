package com.example.lab_2_anime.DataFrom
import okhttp3.OkHttpClient
import okhttp3.Request

class AnimeDataFromNetwork {
    private val client = OkHttpClient()
    private val request = Request.Builder()
    var str: String = ""

    fun getAnimeList (page: Int){
        request.url("https://kinopoiskapiunofficial.tech/api/v2.2/films?countries=16&genres=24&order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1000&yearTo=3000&page=$page")
            .header("X-API-KEY", "8c8e1a50-6322-4135-8875-5d40a5420d86")
            .addHeader("accept", "application/json")
        client.newCall(request.build()).execute().use { response -> str = response.body!!.string() }
    }

    fun getAnimePage (id: Long){
        request.url("https://kinopoiskapiunofficial.tech/api/v2.2/films/$id")
            .header("X-API-KEY", "8c8e1a50-6322-4135-8875-5d40a5420d86")
            .addHeader("accept", "application/json")
        client.newCall(request.build()).execute().use { response -> str = response.body!!.string() }
    }
}