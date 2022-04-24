package com.example.lab_2_anime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.core.text.bold
import com.example.lab_2_anime.DataSource.FilmData
import com.example.lab_2_anime.interf_components.downloadImage
import kotlinx.android.synthetic.main.activity_anime_page.*

class AnimePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_page)
        val item = intent.getSerializableExtra("items") as FilmData
        createPage(item)
    }

    private fun createPage(item: FilmData){
        var pr = 0
        downloadImage(item.posterURL.toString(), image_anime)
        rating.text = item.ratingKinopoisk.toString()
        val str: String = item.ratingAgeLimits.toString()
        if(item.ratingAgeLimits != null)
        {
            if(str.length == 5)
                age_rat.text = str[3].toString() + str[4].toString() + "+"
            else
                age_rat.text = str[3].toString() + "+"
        }
        else
            age_rat.text = "Отс."
        rating_count.text = item.ratingKinopoiskVoteCount.toString()
        anime_name.text = item.nameRu.toString()
        var str2 = SpannableStringBuilder()
            .bold { append("Оригинальное название: ") }
        if(item.nameOriginal != null){
            str2.append(item.nameOriginal.toString())
            anime_type.text = str2
        }
        str2 = SpannableStringBuilder()
            .bold { append("Жанры:") }
        for(i in item.genres) {
            if(pr == 0) {
                pr = 1
                str2.append(" ${i.genre}")
            }
            else
                str2.append(", ${i.genre}")
        }
        anime_year.text = str2
        str2 = SpannableStringBuilder().bold { append("Год выхода: ") }
        if(item.year != null){
            str2.append(item.year.toString())
            anime_source.text = str2
        }
        str2 = SpannableStringBuilder().bold { append("Длительность: ") }
        if(item.filmLength != null){
            str2.append(item.filmLength.toString() + " мин.")
            anime_studio.text = str2
        }
        if(item.description != null)
            anime_info.text = item.description.toString()
    }
}