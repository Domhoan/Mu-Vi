package com.example.muvi.di

import com.example.muvi.data.source.remote.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(MovieService::class.java) }
}
