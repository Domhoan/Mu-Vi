package com.example.muvi.di

import androidx.room.Room
import com.example.muvi.data.repository.FavoriteRepository
import com.example.muvi.data.repository.FavoriteRepositoryType
import com.example.muvi.data.repository.MovieRepository
import com.example.muvi.data.repository.MovieRepositoryType
import com.example.muvi.data.source.FavoriteDataSource
import com.example.muvi.data.source.MovieDataSource
import com.example.muvi.data.source.local.FavoriteLocalDataSource
import com.example.muvi.data.source.local.database.AppDatabase
import com.example.muvi.data.source.local.database.DatabaseConfig.DATABASE_NAME
import com.example.muvi.data.source.remote.MovieRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().movieDao() }
}

val favoriteRepoModule = module {
    single<FavoriteDataSource.Local> { FavoriteLocalDataSource(get()) }

    single<FavoriteRepository> { FavoriteRepositoryType(get()) }
}

val movieRepoModule = module {
    single<MovieDataSource.Remote> { MovieRemoteDataSource(get()) }

    single<MovieRepository> { MovieRepositoryType(get()) }
}
