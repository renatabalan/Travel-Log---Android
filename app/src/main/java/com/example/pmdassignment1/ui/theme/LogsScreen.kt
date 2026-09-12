package com.example.pmdassignment1.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pmdassignment1.data.TravelLog
import com.example.pmdassignment1.viewmodel.TravelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogsScreen(vm: TravelViewModel) {
    // 20% task  dialog targets - to delete and edit logs
    var logToDelete by remember { mutableStateOf<TravelLog?>(null) }
    var logToEdit by remember { mutableStateOf<TravelLog?>(null) }

    val query = vm.searchQuery
    val filteredLogs = vm.logs.filter {
        it.destinationTitle.contains(query, ignoreCase = true) ||
                it.note.contains(query, ignoreCase = true)
    }

    var showDialog by remember { mutableStateOf(false) }
    var selectedIndex by rememberSaveable { mutableStateOf(0) }
    var quickNote by rememberSaveable { mutableStateOf("") }
    var quickRating by rememberSaveable { mutableStateOf(5f) }
    var destMenuExpanded by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(vm.logs.size) {
        if (vm.logs.isNotEmpty()) snackbarHostState.showSnackbar("Saved ${vm.logs.last().destinationTitle}")
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("My Travel Logs") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add log")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { vm.searchQuery = it },
                label = { Text("Search logs") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            if (filteredLogs.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(" Add a log with the + button")
                }
            } else {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + expandVertically()
                ) {
                    LazyColumn {
                        items(filteredLogs, key = { it.id }) { log ->
                            val destination = vm.destinations.find { it.id == log.destinationId }
                            var menuOpen by remember { mutableStateOf(false) }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                destination?.icon?.invoke()
                                Spacer(Modifier.width(12.dp))
                                Column(Modifier.weight(1f)) {
                                    Text(
                                        text = log.destinationTitle,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text(
                                        text = log.note,
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    log.rating?.let {
                                        Text(
                                            "Rating: $it / 10",
                                            style = MaterialTheme.typography.labelSmall
                                        )
                                    }
                                }
                                //  menu for extra lab task 20%
                                Box {
                                    IconButton(onClick = { menuOpen = true }) {
                                        Icon(Icons.Filled.MoreVert, contentDescription = "Options")
                                    }
                                    DropdownMenu(
                                        expanded = menuOpen,
                                        onDismissRequest = { menuOpen = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Edit") }, //option 1 to edit the log
                                            onClick = {
                                                menuOpen = false
                                                logToEdit = log //set earlier
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Delete") }, //option 2 to delete the log
                                            onClick = {
                                                menuOpen = false
                                                logToDelete = log
                                            }
                                        )
                                    }
                                }
                            }
                            Divider()
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Quick add log") },
                text = {
                    Column {
                        OutlinedButton(
                            onClick = { destMenuExpanded = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val current = vm.destinations.getOrNull(selectedIndex)
                            Text(current?.let { "${it.title} • ${it.country}" } ?: "Select destination")
                        }
                        DropdownMenu(
                            expanded = destMenuExpanded,
                            onDismissRequest = { destMenuExpanded = false }
                        ) {
                            vm.destinations.forEachIndexed { i, d ->
                                DropdownMenuItem(
                                    text = { Text("${d.title} • ${d.country}") },
                                    onClick = {
                                        selectedIndex = i
                                        destMenuExpanded = false
                                    }
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))
                        OutlinedTextField(
                            value = quickNote,
                            onValueChange = { quickNote = it },
                            label = { Text("Note") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(Modifier.height(12.dp))
                        Text("Rating: ${quickRating.toInt()} / 10")
                        Slider(
                            value = quickRating,
                            onValueChange = { quickRating = it },
                            valueRange = 1f..10f,
                            steps = 8
                        )
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        val dest = vm.destinations.getOrNull(selectedIndex)
                        if (dest != null && quickNote.isNotBlank()) {
                            vm.quickAddLog(dest.id, quickNote.trim(), quickRating.toInt())
                            quickNote = ""
                            quickRating = 5f
                            showDialog = false
                        }
                    }) { Text("Save") }
                },
                dismissButton = { TextButton(onClick = { showDialog = false }) { Text("Cancel") } }
            )
        }

        // Delete note dialog from task 2
        if (logToDelete != null) {
            AlertDialog( //dialog asking if user wants to delete or not
                onDismissRequest = { logToDelete = null },
                title = { Text("Delete Note?") },
                text = { Text("Are you sure you want to delete this note?") },
                confirmButton = { //button to confirm or cancel
                    TextButton(onClick = {
                        vm.deleteLog(logToDelete!!.id)
                        logToDelete = null
                    }) { Text("Confirm") }
                },
                dismissButton = {
                    TextButton(onClick = { logToDelete = null }) { Text("Cancel") }
                }
            )
        }

        // This is the dialog  to edit note
        if (logToEdit != null) {
            var note by rememberSaveable(logToEdit!!.id) { mutableStateOf(logToEdit!!.note) }
            AlertDialog(
                onDismissRequest = { logToEdit = null },
                title = { Text("Edit Note") },
                text = {
                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Note") },
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                confirmButton = { //button to confirm edit note
                    TextButton(onClick = {
                        if (note.isNotBlank()) vm.updateLogNote(logToEdit!!.id, note.trim())
                        logToEdit = null
                    }) { Text("Save") }
                },
                dismissButton = { // dismiss for cancel option
                    TextButton(onClick = { logToEdit = null }) { Text("Cancel") }
                }
            )
        }
    }
}
