package com.example.lab_2_anime
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_2_anime.Adapters.SpisAdapter3
import com.example.lab_2_anime.DataFrom.AnimeDataFromDB
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import com.example.lab_2_anime.DataFrom.FilmsArray
import com.example.lab_2_anime.DataSource.FilmData
import com.example.lab_2_anime.DataSource.ListItem
import com.example.lab_2_anime.interf_components.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject lateinit var netHelp: AnimeDataFromNetwork
    @Inject lateinit var films: FilmsArray
    @Inject lateinit var myDataBase: AnimeDataFromDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Ещё не доделаю, но Я сделаю (это для скрытия загрузки)
        val prog: ProgressBar = findViewById(R.id.progressBar)
        prog.visibility = View.INVISIBLE
        /*val spis: RecyclerView = findViewById(R.id.SpisRV)
        val b1: Button = findViewById(R.id.button1)
        val b2: Button = findViewById(R.id.button0)
        val txt: TextView = findViewById(R.id.row_list)
        spis.visibility = View.INVISIBLE
        b1.visibility = View.INVISIBLE
        b2.visibility = View.INVISIBLE
        txt.visibility = View.INVISIBLE
        prog.visibility = View.VISIBLE*/

        val myBttns = MyButtons(R.id.button1, R.id.button0, this)
        (application as MainApp).appComponent.inject(this)
        val t = Thread(Runnable {
            myDataBase.initDataBase(this)
            if(isOnline(this)){
                films.getNetListAnimePage()
                myDataBase.saveListToDB(true, films.getArr())
            }
            else{
                films.getDataBaseListPage(myDataBase)
            }

            myBttns.initButt(films, object : FindViewById {
                override fun findView(id: Int): Button {
                    return findViewById(id)
                }
            }, object : RefrashList {
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
        val adapter = SpisAdapter3(this, films.getArr(), object : GoToAnime3 {
            override fun onClicked(tag: ListItem) {
                goToAnime(tag)
            }
        })
        SpisRV.adapter = adapter
    }

    fun goToAnime(animeData: ListItem){
        val r = Thread(Runnable {
            if(isOnline(this)){
                animeData.kinopoiskID?.let { netHelp.getAnimePage(it) }
                val data = netHelp.str.let { FilmData.fromJson(it) }
                if (data != null) {
                    myDataBase.saveFilmToDB(data)
                }
                startActivity(Intent(this, AnimePageActivity::class.java).apply {
                    putExtra("items", data)
                })
            }
            else{
                val data = animeData.kinopoiskID?.let { myDataBase.getSomeFilm(it) }
                if(data != null){
                    startActivity(Intent(this, AnimePageActivity::class.java).apply {
                        putExtra("items", data)
                    })
                }
                else{
                    val toastTXT = "Данные не загружены.\nПодключитесь к интернету и попробуйте снова."
                    runOnUiThread{
                        Toast.makeText(applicationContext, toastTXT, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        r.start()
    }

    fun refreshList(){
        val r = Thread(Runnable {
            if(isOnline(this)){
                films.getNetListAnimePage()
                myDataBase.saveListToDB(false, films.getArr())
            }
            else{
                films.getDataBaseListPage(myDataBase)
            }
            runOnUiThread {
                createList()
            }
        })
        r.start()
        row_list.text = films.getPage().toString()
    }
}