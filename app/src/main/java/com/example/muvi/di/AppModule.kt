package com.example.muvi.di

import com.example.muvi.ui.actor.MovieOfActorViewModel
import com.example.muvi.ui.company.MovieOfCompanyViewModel
import com.example.muvi.ui.detail.DetailViewModel
import com.example.muvi.ui.favorite.FavoriteViewModel
import com.example.muvi.ui.genres.GenresViewModel
import com.example.muvi.ui.home.HomeViewModel
import com.example.muvi.ui.search.SearchViewModel
import com.example.muvi.ui.seeall.SeeAllViewModel
import com.example.muvi.ui.upcoming.UpComingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GenresViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { UpComingViewModel(get(),get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { MovieOfActorViewModel(get()) }
    viewModel { MovieOfCompanyViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { SeeAllViewModel(get()) }
}
