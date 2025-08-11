package com.example.kennedycruiser.di

import com.example.kennedycruiser.model.IRepository
import com.example.kennedycruiser.model.Repository
import com.example.kennedycruiser.model.remote.FleetApi
import com.example.kennedycruiser.viewmodel.ShipViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val shipModule = module {
    single { FleetApi.initRetrofit() }
    factory<IRepository> { Repository(get(), get()) }
    single { CoroutineScope(Dispatchers.IO) }
    viewModel{ ShipViewModel(get()) }
}