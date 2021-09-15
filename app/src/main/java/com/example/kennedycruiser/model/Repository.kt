package com.example.kennedycruiser.model

import android.util.Log
import com.example.kennedycruiser.model.remote.FleetApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "Repository"
class Repository(private val service: FleetApi, private val scope: CoroutineScope) : IRepository {


    override fun getFleet(): Flow<AppState> {
        return flow {

//            scope.launch {
//                val r=service.getFleet("SKY")
//                val r1=service.getFleet("BLISS")
//                val r2=service.getFleet("ESCAPE")
//
//                Log.d(TAG, "getFleet: ${r}${r1}${r2}")
//            }

            val responseSky = scope.async{ service.getFleet("SKY") }
            val responseBliss =
                scope.async { service.getFleet("BLISS") }
            val responseEscape =
                scope.async { service.getFleet("ESCAPE") }

           emit(AppState.Loading(false))

            if (responseSky.await().isSuccessful &&
                responseEscape.await().isSuccessful &&
                responseBliss.await().isSuccessful)
                emit(
                    AppState.Response(
                        ShipFleet().apply {
                            add(responseEscape.await().body()!!)
                            add(responseBliss.await().body()!!)
                            add(responseSky.await().body()!!)
                        }
                    )
                )
            else
                emit(
                    AppState.Error("Error from Server")
                )
        }
    }
}