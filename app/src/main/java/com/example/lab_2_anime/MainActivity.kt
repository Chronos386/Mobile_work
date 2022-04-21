package com.example.lab_2_anime
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import com.example.lab_2_anime.DataFrom.FilmsArray
import com.example.lab_2_anime.DataSource.FilmData
import com.example.lab_2_anime.DataSource.ListItem
import com.example.lab_2_anime.interf_components.MyButtons
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject lateinit var netHelp: AnimeDataFromNetwork
    @Inject lateinit var films: FilmsArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pop = MyButtons(R.layout.activity_main, R.id.button1, R.id.button0, this)
        (application as MainApp).appComponent.inject(this)
        val t = Thread(Runnable {
            films.getNetListAnimePage()
            pop.InitButt(films, object : findViewById {
                override fun findView(id: Int): Button {
                    return findViewById(id)
                }
            }, object : refrashList {
                override fun smenPage() {
                    refreshList()
                }
            })
            runOnUiThread {
                createList()
            }
        })
        t.start()
        row_list.text = films.getPage().toString()
    }

    private fun createList(){
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        SpisRV.layoutManager = linearLayoutManager
        val adapter = SpisAdapter3(this, films.getArr(), object : GoToAnime3{
            override fun onClicked(tag: ListItem) {
                goToAnime(tag)
            }
        })
        SpisRV.adapter = adapter
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
            films.getNetListAnimePage()
            runOnUiThread {
                createList()
            }
        })
        r.start()
        row_list.text = films.getPage().toString()
    }
}