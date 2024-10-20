package com.lucasbueno.sportevents.presentation.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsEventsTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray,
        ),
        title = {
            Text("SPORT EVENTS", color = Color.White)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    SportsEventsTopBar()
}
