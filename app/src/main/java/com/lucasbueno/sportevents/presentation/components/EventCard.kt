package com.lucasbueno.sportevents.presentation.components

import SportsEventsCountdownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucasbueno.sportevents.domain.model.Event
import com.lucasbueno.sportevents.utils.formatTimestampToTime

@Composable
fun EventCard(
    event: Event,
    isFavorite: Boolean,
    onFavoriteClick: (String, String, Boolean) -> Unit,
) {
    val parts = event.eventName?.split("-")
    val comp1 = parts?.get(0)
    val comp2 = parts?.get(1)

    Column(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SportsEventsCountdownTimer(event.eventTimestamp.formatTimestampToTime() ?: "00:00:00")

        IconButton(onClick = {
            onFavoriteClick(event.sportId.orEmpty(), event.eventId, !isFavorite)
        }) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarOutline,
                contentDescription = if (isFavorite) "Collapse" else "Expand",
                modifier = Modifier.size(35.dp),
                tint = Color.Yellow
            )
        }

        Text(comp1.orEmpty(), color = Color.White, maxLines = 2, overflow = TextOverflow.Ellipsis)
        Text("vs", color = Color.White)
        Text(comp2.orEmpty(), color = Color.White, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEventCard() {
    EventCard(
        event = Event(
            eventId = "123",
            sportId = "123",
            eventName = "Competitor 1-Competidor 2",
            eventTimestamp = "12:00:00".toLongOrNull()
        ),
        isFavorite = true,
        onFavoriteClick = { _, _, _ -> }
    )
}
