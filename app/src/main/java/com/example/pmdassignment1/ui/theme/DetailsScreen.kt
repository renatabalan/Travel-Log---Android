package com.example.pmdassignment1.ui.theme

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pmdassignment1.viewmodel.TravelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    id: String,
    vm: TravelViewModel,
    onBack: () -> Unit
) {
    val dest = vm.getDestination(id)
    val context = LocalContext.current

    var note by rememberSaveable { mutableStateOf("") }
    var rating by rememberSaveable { mutableStateOf(5f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = { Text(dest?.title ?: "Destination") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(Modifier.height(16.dp))
        Text(dest?.summary ?: "", fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Your notes") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Text("Rating: ${rating.toInt()} / 10")
        Slider(
            value = rating,
            onValueChange = { rating = it },
            valueRange = 1f..10f,
            steps = 8,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        // --- Save button ---
        Button(
            onClick = {
                if (note.isNotBlank()) {
                    vm.addLog(id, note, rating.toInt())
                    Toast.makeText(context, "Log saved!", Toast.LENGTH_SHORT).show()
                    note = ""
                } else {
                    Toast.makeText(context, "Please enter a note", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Text("Save Log")
        }
    }
}
