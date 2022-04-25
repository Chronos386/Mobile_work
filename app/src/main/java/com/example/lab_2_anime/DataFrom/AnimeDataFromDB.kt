package com.example.lab_2_anime.DataFrom
import android.content.Context
import androidx.room.Room
import com.example.lab_2_anime.DataBaseRoom.AppDataBase
import com.example.lab_2_anime.DataBaseRoom.Connections.ConnCountryFilm
import com.example.lab_2_anime.DataBaseRoom.Connections.ConnCountryItem
import com.example.lab_2_anime.DataBaseRoom.Connections.ConnGenreFilm
import com.example.lab_2_anime.DataBaseRoom.Connections.ConnGenreItem
import com.example.lab_2_anime.DataBaseRoom.Country.CountryDB
import com.example.lab_2_anime.DataBaseRoom.FilmData.FilmDataDB
import com.example.lab_2_anime.DataBaseRoom.Genre.GenreDB
import com.example.lab_2_anime.DataBaseRoom.ListItem.ListItemDB
import com.example.lab_2_anime.DataSource.Country
import com.example.lab_2_anime.DataSource.FilmData
import com.example.lab_2_anime.DataSource.Genre
import com.example.lab_2_anime.DataSource.ListItem

class AnimeDataFromDB {
    private lateinit var myData: AppDataBase
    var str: String = ""

    fun initDataBase(cont: Context){
        myData = Room.databaseBuilder(
            cont,
            AppDataBase::class.java,
            "myDataBase"
        ).build()
    }

    private fun clearCountryFilmTable() {
        val count = myData.countryFilmDao().findCount()
        for(i in 1..count){
            val row = myData.countryFilmDao().findByID(i)
            myData.countryFilmDao().delete(row)
        }
    }

    private fun clearCountryItemTable() {
        val count = myData.countryItemDao().findCount()
        for(i in 1..count){
            val row = myData.countryItemDao().findByID(i)
            myData.countryItemDao().delete(row)
        }
    }

    private fun clearGenreFilmTable() {
        val count = myData.genreFilmDao().findCount()
        for(i in 1..count){
            val row = myData.genreFilmDao().findByID(i)
            myData.genreFilmDao().delete(row)
        }
    }

    private fun clearGenreItemTable() {
        val count = myData.genreItemDao().findCount()
        for(i in 1..count){
            val row = myData.genreItemDao().findByID(i)
            myData.genreItemDao().delete(row)
        }
    }

    private fun clearCountryTable() {
        val count = myData.countryDao().findCount()
        for(i in 1..count){
            val row = myData.countryDao().findByID(i)
            myData.countryDao().delete(row)
        }
    }

    private fun clearGenreTable() {
        val count = myData.genreDao().findCount()
        for(i in 1..count){
            val row = myData.genreDao().findByID(i)
            myData.genreDao().delete(row)
        }
    }

    private fun clearFilmDataTable() {
        val count = myData.filmDataDao().findCount()
        for(i in 1..count){
            val row = myData.filmDataDao().findByID(i)
            myData.filmDataDao().delete(row)
        }
    }

    private fun clearListItemTable() {
        val count = myData.listItemDao().findCount()
        for(i in 1..count){
            val row = myData.listItemDao().findByID(i)
            myData.listItemDao().delete(row)
        }
    }

    private fun clearItems() {
        clearCountryItemTable()
        clearGenreItemTable()
        clearListItemTable()
    }

    fun clearDataBase() {
        clearCountryFilmTable()
        clearCountryItemTable()
        clearGenreFilmTable()
        clearGenreItemTable()
        clearCountryTable()
        clearGenreTable()
        clearFilmDataTable()
        clearListItemTable()
    }

    fun saveListToDB(first: Boolean, films: ArrayList<ListItem>) {
        if(first){
            clearItems()
        }
        for(item in films){
            val find = item.kinopoiskID?.let { myData.listItemDao().findByKPID(it) }
            if(find == null) {
                var count = myData.listItemDao().findCount()
                val newItem = ListItemDB(
                    count + 1, item.kinopoiskID, item.imdbID, item.nameRu, item.nameEn,
                    item.nameOriginal, item.ratingKinopoisk, item.ratingImdb, item.year,
                    item.type, item.posterURL, item.posterURLPreview)
                myData.listItemDao().insert(newItem)
                for (gen in item.genres) {
                    var gg = 0
                    val fnd = myData.genreDao().findByName(gen.genre)
                    if (fnd == null) {
                        count = myData.genreDao().findCount()
                        val newGenre = GenreDB(count + 1, gen.genre)
                        myData.genreDao().insert(newGenre)
                        gg = newGenre.id
                    }
                    else{
                        gg = fnd.id
                    }
                    count = myData.genreItemDao().findCount()
                    val newRow = ConnGenreItem(count + 1, gg, newItem.id)
                    myData.genreItemDao().insert(newRow)
                }
                for (cou in item.countries) {
                    var cc = 0
                    val fnd = myData.countryDao().findByName(cou.country)
                    if (fnd == null) {
                        count = myData.countryDao().findCount()
                        val newCountry = CountryDB(count + 1, cou.country)
                        myData.countryDao().insert(newCountry)
                        cc = newCountry.id
                    }
                    else{
                        cc = fnd.id
                    }
                    count = myData.countryItemDao().findCount()
                    val newRow = ConnCountryItem(count + 1, cc, newItem.id)
                    myData.countryItemDao().insert(newRow)
                }
            }
        }
    }

    fun saveFilmToDB(film: FilmData){
        val find = myData.filmDataDao().findByKPID(film.kinopoiskID)
        if(find == null){
            var count = myData.filmDataDao().findCount()
            val newItem = FilmDataDB(count + 1, film.kinopoiskID, film.imdbID, film.nameRu,
            film.nameEn, film.nameOriginal, film.posterURL, film.posterURLPreview, film.coverURL,
            film.reviewsCount, film.ratingGoodReview, film.ratingGoodReviewVoteCount,
            film.ratingKinopoisk, film.ratingKinopoiskVoteCount, film.ratingImdb,
            film.ratingImdbVoteCount, film.ratingFilmCritics, film.ratingFilmCriticsVoteCount,
            film.ratingAwait, film.ratingAwaitCount, film.ratingRFCritics,
            film.ratingRFCriticsVoteCount, film.webURL, film.year, film.filmLength,
            film.slogan, film.description, film.shortDescription, film.editorAnnotation,
            film.isTicketsAvailable, film.productionStatus, film.type, film.ratingMPAA,
            film.ratingAgeLimits, film.startYear, film.endYear, film.serial, film.shortFilm,
            film.completed, film.hasImax, film.has3D, film.lastSync)
            myData.filmDataDao().insert(newItem)
            for (gen in film.genres) {
                var gg = 0
                val fnd = myData.genreDao().findByName(gen.genre)
                if (fnd == null) {
                    count = myData.genreDao().findCount()
                    val newGenre = GenreDB(count + 1, gen.genre)
                    myData.genreDao().insert(newGenre)
                    gg = newGenre.id
                }
                else{
                    gg = fnd.id
                }
                count = myData.genreFilmDao().findCount()
                val newRow = ConnGenreFilm(count + 1, gg, newItem.id)
                myData.genreFilmDao().insert(newRow)
            }
            for (cou in film.countries) {
                var cc = 0
                val fnd = myData.countryDao().findByName(cou.country)
                if (fnd == null) {
                    count = myData.countryDao().findCount()
                    val newCountry = CountryDB(count + 1, cou.country)
                    myData.countryDao().insert(newCountry)
                    cc = newCountry.id
                }
                else{
                    cc = fnd.id
                }
                count = myData.countryFilmDao().findCount()
                val newRow = ConnCountryFilm(count + 1, cc, newItem.id)
                myData.countryFilmDao().insert(newRow)
            }
        }
    }

    fun getSomeFilm(kpID: Long): FilmData?{
        val film = myData.filmDataDao().findByKPID(kpID)
        if(film != null){
            val connCountry = myData.countryFilmDao().findFilmCountry(film.id)
            val connGenre = myData.genreFilmDao().findFilmGenres(film.id)
            val arrayCountry: ArrayList<Country> = ArrayList()
            val arrayGenre: ArrayList<Genre> = ArrayList()
            for(ccIt in connCountry){
                val cc = myData.countryDao().findByID(ccIt.country_id)
                val myCC = cc.name?.let { Country(it) }
                if (myCC != null) {
                    arrayCountry.add(myCC)
                }
            }
            for(ggIt in connGenre){
                val gg = myData.genreDao().findByID(ggIt.genre_id)
                val myGG = gg.name?.let { Genre(it) }
                if (myGG != null) {
                    arrayGenre.add(myGG)
                }
            }
            val newFilm = FilmData(film.kinopoiskID, film.imdbID, film.nameRu,
                film.nameEn, film.nameOriginal, film.posterURL, film.posterURLPreview, film.coverURL,
                film.reviewsCount, film.ratingGoodReview, film.ratingGoodReviewVoteCount,
                film.ratingKinopoisk, film.ratingKinopoiskVoteCount, film.ratingImdb,
                film.ratingImdbVoteCount, film.ratingFilmCritics, film.ratingFilmCriticsVoteCount,
                film.ratingAwait, film.ratingAwaitCount, film.ratingRFCritics,
                film.ratingRFCriticsVoteCount, film.webURL, film.year, film.filmLength,
                film.slogan, film.description, film.shortDescription, film.editorAnnotation,
                film.isTicketsAvailable, film.productionStatus, film.type, film.ratingMPAA,
                film.ratingAgeLimits, arrayCountry, arrayGenre, film.startYear, film.endYear, film.serial, film.shortFilm,
                film.completed, film.hasImax, film.has3D, film.lastSync)
            return newFilm
        }
        else
            return null
    }

    fun getPageCount(): Int {
        val count = myData.listItemDao().findCount()
        return if(count != 0){
            count / 20 + 1
        } else{
            0
        }
    }

    fun getSomeList(page: Int): ArrayList<ListItem>{
        val array: ArrayList<ListItem> = ArrayList()
        val items = myData.listItemDao().getAll()
        val len = myData.listItemDao().findCount()
        var cycleLen = len - (20*page)
        cycleLen = if(cycleLen < 0)
            page*20 + cycleLen
        else{
            page*20
        }
        for(i in (page-1)*20 until cycleLen){
            val connCountry = myData.countryItemDao().findItemCountry(items[i].id)
            val connGenre = myData.genreItemDao().findItemGenres(items[i].id)
            val arrayCountry: ArrayList<Country> = ArrayList()
            val arrayGenre: ArrayList<Genre> = ArrayList()
            for(ccIt in connCountry){
                val cc = myData.countryDao().findByID(ccIt.country_id)
                val myCC = cc.name?.let { Country(it) }
                if (myCC != null) {
                    arrayCountry.add(myCC)
                }
            }
            for(ggIt in connGenre){
                val gg = myData.genreDao().findByID(ggIt.genre_id)
                val myGG = gg.name?.let { Genre(it) }
                if (myGG != null) {
                    arrayGenre.add(myGG)
                }
            }
            val newItem = ListItem(items[i].kinopoiskID, items[i].imdbID, items[i].nameRu,
                items[i].nameEn, items[i].nameOriginal, arrayCountry, arrayGenre,
                items[i].ratingKinopoisk, items[i].ratingImdb, items[i].year, items[i].type,
                items[i].posterURL, items[i].posterURLPreview)
            array.add(newItem)
        }
        return array
    }
}