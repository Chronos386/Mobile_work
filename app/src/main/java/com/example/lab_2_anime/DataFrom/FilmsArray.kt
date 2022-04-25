package com.example.lab_2_anime.DataFrom
import com.example.lab_2_anime.DaggerAppComponent
import com.example.lab_2_anime.DataSource.FilmList
import com.example.lab_2_anime.DataSource.ListItem
import javax.inject.Inject

class FilmsArray {
    private var array: ArrayList<ListItem> = ArrayList()
    private var pageCount: Long = 2
    private var listPage: Long = 1
    @Inject
    lateinit var netHelp: AnimeDataFromNetwork

    fun getNetListAnimePage() {
        DaggerAppComponent.builder()
            .build()
            .inject_func(this)
        netHelp.getAnimeList(listPage)
        array.clear()
        val topPage = netHelp.str.let { FilmList.fromJson(it) }
        if (topPage != null) {
            pageCount = topPage.totalPages!!
            for(i in topPage.items) {
                array.add(i)
            }
        }
    }

    fun getDataBaseListPage(myDataBase: AnimeDataFromDB) {
        pageCount = myDataBase.getPageCount().toLong()
        array.clear()
        array = myDataBase.getSomeList(listPage.toInt())
    }

    fun getArr(): ArrayList<ListItem>{
        return array
    }

    fun getCountPage(): Long {
        return pageCount
    }

    fun getPage(): Long {
        return listPage
    }

    fun nextPage(){
        listPage++
    }

    fun previousPage(){
        listPage--
    }
}