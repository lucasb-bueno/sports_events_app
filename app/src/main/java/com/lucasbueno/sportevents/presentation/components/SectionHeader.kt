package com.lucasbueno.sportevents.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.google.accompanist.flowlayout.FlowRow
import com.lucasbueno.sportevents.domain.model.Event
import com.lucasbueno.sportevents.domain.model.FavoriteEvent

@Composable
fun SectionHeader(
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
    Spacer(modifier = Modifier.height(8.dp))

    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        mainAxisSpacing = 8.dp,  // Spacing between items in the row
        crossAxisSpacing = 8.dp, // Spacing between rows
    ) {

        activeEvents?.forEach { event ->
            EventCard(
                event = event,
                isFavorite = favoriteEventList.filter { it.sportId == sportId }
                    .any { it.eventId == event.eventId && it.isFavorite },
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSectionHeader() {
    SectionHeader(
        sportId = "",
        title = "SPORT",
        isToggled = false,
        onToggleClick = { _, _ -> },
        onFavoriteClick = { _, _, _ -> },
        activeEvents = listOf(),
        favoriteEventList = listOf()
    )
}

