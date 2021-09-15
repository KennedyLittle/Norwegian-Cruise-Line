package com.example.kennedycruiser.model

class ShipFleet: ArrayList<ShipResponse>()

data class ShipResponse(
    val shipName: String,
    val shipFacts: ShipFacts,
    val bgeImagePath: String
)

data class ShipFacts(
    val passengerCapacity: String,
    val crew: String,
    val inauguralDate: String
)
