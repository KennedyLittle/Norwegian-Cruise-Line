package com.example.kennedycruiser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kennedycruiser.model.AppState
import com.example.kennedycruiser.model.IRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class ShipViewModel(private val repository: IRepository): ViewModel() {

    private val _shipFleet = MutableLiveData<AppState>()
    val shipFleet: LiveData<AppState>
    get() = _shipFleet

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        _shipFleet.value = AppState.Loading(true)
        coroutineScope.launch {
            repository.getFleet().collect { response->

                delay(500)
                _shipFleet.postValue(response)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel("ViewModel onCleared")
    }
}