package com.example.lab_2_anime

import android.widget.Button
import android.widget.ImageView
import com.example.lab_2_anime.DataSource.ListItem
import com.squareup.picasso.Picasso

interface GoToAnime3 {
    fun onClicked(tag: ListItem)
}

interface refrashList {
    fun smenPage()
}

interface findViewById {
    fun findView(id: Int): Button
}

fun downloadImage(link: String, image: ImageView){
    Picasso.get().load(link).fit().placeholder(R.mipmap.ic_launcher).into(image)
}