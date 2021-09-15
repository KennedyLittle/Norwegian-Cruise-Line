package com.example.kennedycruiser.model

sealed class AppState{
    data class Response(val data: ShipFleet): AppState()
    data class Error(val error: String): AppState()
    data class Loading(val isLoading: Boolean): AppState()
}
