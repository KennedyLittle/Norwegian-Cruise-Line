package com.example.kennedycruiser.model

import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getFleet(): Flow<AppState>
}