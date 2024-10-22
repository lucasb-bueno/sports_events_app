package com.lucasbueno.sportevents.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasbueno.sportevents.domain.model.Event
import com.lucasbueno.sportevents.domain.model.FavoriteEvent

@Composable
fun HeaderSection(
    sportId: String,
    title: String,
    isToggled: Boolean,
    onToggleClick: (Boolean, String) -> Unit,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    activeEvents: List<Event>?,
    favoriteEventList: List<FavoriteEvent>
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isFavoriteSport by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clickable { isExpanded = !isExpanded }
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Circle,
                contentDescription = "Live",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(text = title)

            Spacer(modifier = Modifier.weight(1f))

            Switch(
                checked = isToggled,
                onCheckedChange = { checked ->
                    sportId.let {
                        isFavoriteSport = !isFavoriteSport
                        onToggleClick(checked, it)
                    }
                },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.Blue,
                    uncheckedThumbColor = Color.Gray,
                    checkedIconColor = Color.Yellow,
                    checkedTrackColor = Color.Gray
                ),
                thumbContent = {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        modifier = Modifier.padding(1.dp)
                    )
                }
            )

            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = if (isExpanded) "Collapse" else "Expand",
                modifier = Modifier.size(50.dp)
            )
        }

        if (isExpanded) {
            val favIds = favoriteEventList.map { it.eventId }.toSet()
            val events =
                activeEvents?.filter { !isToggled || it.eventId in favIds } ?: emptyList()

            EventsSection(
                events,
                sportId = sportId,
                onFavoriteClick = onFavoriteClick,
                favoriteEventList = favoriteEventList
            )
        }
    }
}

@Composable
fun EventsSection(
    activeEvents: List<Event>?,
    sportId: String,
    onFavoriteClick: (String, String, Boolean) -> Unit,
    favoriteEventList: List<FavoriteEvent>
) {
    val favoriteEventIds = favoriteEventList
        .filter { it.sportId == sportId && it.isFavorite }
        .map { it.eventId }
        .toSet()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .heightIn(max = 300.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(activeEvents.orEmpty()) { event ->
            EventCard(
                event = event,
                isFavorite = favoriteEventIds.contains(event.eventId),
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSectionHeader() {
    HeaderSection(
        sportId = "",
        title = "SPORT",
        isToggled = true,
        onToggleClick = { _, _ -> },
        onFavoriteClick = { _, _, _ -> },
        activeEvents = listOf(),
        favoriteEventList = listOf()
    )
}

