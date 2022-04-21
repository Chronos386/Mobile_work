package com.example.lab_2_anime

import com.example.lab_2_anime.DataFrom.AnimeDataFromDB
import com.example.lab_2_anime.DataFrom.AnimeDataFromNetwork
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [NetworkModule::class, DBModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
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