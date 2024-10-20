import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun CountdownFromString(startTime: String) {
    // Convert the input string "HH:mm:ss" to total milliseconds
    val totalMilliseconds = parseTimeToMilliseconds(startTime)

    var remainingTime by remember { mutableLongStateOf(totalMilliseconds) }

    // LaunchedEffect to update the remaining time every second
    LaunchedEffect(remainingTime) {
        while (remainingTime > 0) {
            delay(1000L) // Delay for 1 second
            remainingTime -= 1000L // Subtract one second
        }
    }

    // Derive the formatted time based on remainingTime
    val formattedTime = formatRemainingTime(remainingTime)

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = formattedTime,
            color = Color.White
        )
    }
}

// Function to convert "HH:mm:ss" to total milliseconds
private fun parseTimeToMilliseconds(startTime: String): Long {
    val timeParts = startTime.split(":").map { it.toInt() }
    return (timeParts[0] * 3600 + timeParts[1] * 60 + timeParts[2]) * 1000L
}

// Function to format remaining time in "HH:mm:ss"
private fun formatRemainingTime(remainingTime: Long): String {
    return if (remainingTime > 0) {
        val hours = TimeUnit.MILLISECONDS.toHours(remainingTime)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        "Started"
    }
}