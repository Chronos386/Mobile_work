package com.example.lab_2_anime

import com.example.lab_2_anime.DataFrom.AnimeDataFromDB
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import com.example.lab_2_anime.DataFrom.FilmsArray
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [ArrFilmModule::class, NetworkModule::class, DBModule::class])
interface AppComponent {
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
    fun provideDataFromDB(): FilmsArray {
        return FilmsArray()
    }
}