package com.example.lab_2_anime

import android.widget.ImageView
import com.example.lab_2_anime.DataSource.ListItem
import com.squareup.picasso.Picasso

interface GoToAnime3 {
    fun onClicked(tag: ListItem)
}

fun downloadImage(link: String, image: ImageView){
    Picasso.get().load(link).fit().placeholder(R.mipmap.ic_launcher).into(image)
}