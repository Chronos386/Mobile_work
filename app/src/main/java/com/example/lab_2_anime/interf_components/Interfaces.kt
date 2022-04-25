package com.example.lab_2_anime.interf_components

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.example.lab_2_anime.DataSource.ListItem
import com.example.lab_2_anime.R
import com.squareup.picasso.Picasso

interface GoToAnime3 {
    fun onClicked(tag: ListItem)
}

interface RefrashList {
    fun smenPage()
}

interface FindViewById {
    fun findView(id: Int): Button
}

fun downloadImage(link: String, image: ImageView){
    Picasso.get().load(link).fit().placeholder(R.mipmap.ic_launcher).into(image)
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
}