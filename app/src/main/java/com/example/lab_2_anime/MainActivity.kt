package com.example.lab_2_anime

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import com.example.lab_2_anime.DataSource.FilmData
import com.example.lab_2_anime.DataSource.FilmList
import com.example.lab_2_anime.DataSource.ListItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    var array: ArrayList<ListItem> = ArrayList()
    var listPage: Int = 1

    @Inject lateinit var netHelp: AnimeDataFromNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MainApp).appComponent.inject(this)
        setContentView(R.layout.activity_main)
        val t = Thread(Runnable {
            getListAnimePage(array)
            runOnUiThread {
                createList()
            }
        })
        t.start()
        row_list.text = listPage.toString()
        button1.visibility = View.INVISIBLE
        button1.setOnClickListener {
            if(listPage == 20)
                button0.visibility = View.VISIBLE
            listPage--
            if(listPage == 1)
                button1.visibility = View.INVISIBLE
            refreshList()
        }
        button0.setOnClickListener {
            if(listPage == 1)
                button1.visibility = View.VISIBLE
            listPage++
            if(listPage == 20)
                button0.visibility = View.INVISIBLE
            refreshList()
        }
    }

    private fun createList(){
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        SpisRV.layoutManager = linearLayoutManager
        val adapter = SpisAdapter3(this, array, object : GoToAnime3{
            override fun onClicked(tag: ListItem) {
                goToAnime(tag)
            }
        })
        SpisRV.adapter = adapter
    }

    private fun getListAnimePage(array: ArrayList<ListItem>) {
        netHelp.getAnimeList(listPage)
        array.clear()
        val topPage = netHelp.str.let { FilmList.fromJson(it) }
        if (topPage != null) {
            for(i in topPage.items) {
                array.add(i)
            }
        }
    }

    fun goToAnime(animeData: ListItem){
        val r = Thread(Runnable {
            animeData.kinopoiskID?.let { netHelp.getAnimePage(it) }
            val data = netHelp.str.let { FilmData.fromJson(it) }
            startActivity(Intent(this, AnimePageActivity::class.java).apply {
                putExtra("items", data)
            })
        })
        r.start()
    }

    fun refreshList(){
        val r = Thread(Runnable {
            getListAnimePage(array)
            runOnUiThread {
                createList()
            }
        })
        r.start()
        row_list.text = listPage.toString()
    }
}