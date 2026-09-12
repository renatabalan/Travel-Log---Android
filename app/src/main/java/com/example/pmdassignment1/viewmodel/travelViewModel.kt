package com.example.pmdassignment1.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Castle
import androidx.compose.material.icons.filled.Dining
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Surfing
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.pmdassignment1.data.*


class TravelViewModel : ViewModel() {
    var searchQuery by mutableStateOf("")


    var destinations by mutableStateOf(
        listOf(
            Destination("1","Paris","France","Eiffel tower, Seine cruise, Louvre")  {
                Icon(Icons.Filled.LocationCity, contentDescription = null)},
            Destination("2","Edinburgh","Scotland","Christmas markets, Harry Potter streets"){
                Icon(Icons.Filled.Castle, contentDescription = null)
            },
            Destination("3","Marbella","Spain","Beaches and nightlife"){
                Icon(Icons.Filled.BeachAccess, contentDescription = null)
            },
            Destination("4","Monte Carlo","Monaco","Casino and F1 track"){
                Icon(Icons.Filled.AttachMoney, contentDescription = null)
            },
            Destination("5","Amsterdam","The Netherlands","History and architecture"){
                Icon(Icons.Filled.DirectionsBike, contentDescription = null)},
            Destination("6","Reykjavik","Iceland","Hot springs and Northern Lights"){
                Icon(Icons.Filled.AutoAwesome, contentDescription = null)
            },
            Destination("7","New York","USA","Pizza and skyscrapers"){
                Icon(Icons.Filled.Apartment, contentDescription = null)
            },
            Destination("8","Positano","Italy","Colourful buildings and pasta"){
                Icon(Icons.Filled.Dining, contentDescription = null)
            },
            Destination("9","Sydney","Australia","Surfing, beaches, kangaroos"){
                Icon(Icons.Filled.Surfing, contentDescription = null)
            },
            Destination("10","Banff","Canada","Lakes and mountains") {
                Icon(Icons.Filled.Terrain, contentDescription = null)
            }
                )
    )
        private set

    var logs by mutableStateOf(listOf<TravelLog>())
        private set

    fun getDestination(id: String) = destinations.firstOrNull { it.id == id }

    fun addLog(destinationId: String, note: String, rating: Int) {
        val dest = getDestination(destinationId) ?: return
        val log = TravelLog(
            id = System.currentTimeMillis().toString(),
            destinationId = destinationId,
            destinationTitle = dest.title,
            note = note,
            rating = rating
        )
        logs = logs + log
    }
    fun quickAddLog(destinationId: String, note: String, rating: Int = 5) {
        addLog(destinationId, note, rating)
    }
    fun deleteLog(id: String) { logs = logs.filterNot { it.id == id } } //added for 20% task
    fun updateLogNote(id: String, note: String) {  // added for 20% task to update note in logs
        logs = logs.map { if (it.id == id) it.copy(note = note) else it }
    }


}
