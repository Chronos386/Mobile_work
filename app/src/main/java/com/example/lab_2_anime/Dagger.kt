package com.example.lab_2_anime

import android.content.Context
import androidx.room.Room
import com.example.lab_2_anime.DataBaseRoom.AppDataBase
import com.example.lab_2_anime.DataFrom.AnimeDataFromDB
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import com.example.lab_2_anime.DataFrom.FilmsArray
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [ArrFilmModule::class, NetworkModule::class, DBModule::class, AppDataBaseModule::class])//, MainActivityModule::class
@Singleton
interface AppComponent {
    //fun context(): Context
    fun inject(activity: MainActivity)
    fun inject_func(func: FilmsArray)
}

@Module
class NetworkModule {
    @Provides
    fun provideDataFromNetwork(): AnimeDataFromNetwork {
        return AnimeDataFromNetwork()
    }
}

@Module
class DBModule {
    @Provides
    fun provideDataFromDB(): AnimeDataFromDB {
        return AnimeDataFromDB()
    }
}

@Module
class ArrFilmModule {
    @Provides
    fun provideFilmsArray(): FilmsArray {
        return FilmsArray()
    }
}

@Module
object AppDataBaseModule {
    @Singleton
    @Provides
    fun provideMyDatabase(app: Context): AppDataBase = Room.databaseBuilder(
        app,
        AppDataBase::class.java,
        "myDataBase"
    ).build()

    @Singleton
    @Provides
    fun countryDao(db: AppDataBase) = db.countryDao()

    @Singleton
    @Provides
    fun genreDao(db: AppDataBase) = db.genreDao()

    @Singleton
    @Provides
    fun filmDataDao(db: AppDataBase) = db.filmDataDao()

    @Singleton
    @Provides
    fun listItemDao(db: AppDataBase) = db.listItemDao()

    @Singleton
    @Provides
    fun countryFilmDao(db: AppDataBase) = db.countryFilmDao()

    @Singleton
    @Provides
    fun countryItemDao(db: AppDataBase) = db.countryItemDao()

    @Singleton
    @Provides
    fun genreFilmDao(db: AppDataBase) = db.genreFilmDao()

    @Singleton
    @Provides
    fun genreItemDao(db: AppDataBase) = db.genreItemDao()
}

@Module
class MainActivityModule(private val context: Context) {
    @Provides
    fun context(): Context {
        return context
    }
}