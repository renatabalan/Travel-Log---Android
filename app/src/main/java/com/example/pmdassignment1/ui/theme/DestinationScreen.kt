package com.example.pmdassignment1.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.pmdassignment1.data.Destination
import com.example.pmdassignment1.viewmodel.TravelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinationsScreen(vm: TravelViewModel, onOpenDetails: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Travel Log - Destinations") })
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(vm.destinations) { dest ->
                DestinationRow(dest = dest, onClick = { onOpenDetails(dest.id) })
                Divider()
            }
        }
    }
}

@Composable
fun DestinationRow(dest: Destination, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(dest.title, fontWeight = FontWeight.SemiBold) },
        supportingContent = {
            Text(
                "${dest.country} - ${dest.summary}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        leadingContent = { dest.icon() },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 4.dp)
    )
}
