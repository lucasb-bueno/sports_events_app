import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@Composable
fun SportsEventsCountdownTimer(startTime: String) {
    val totalMilliseconds = parseTimeToMilliseconds(startTime)

    var remainingTime by remember { mutableLongStateOf(totalMilliseconds) }

    LaunchedEffect(remainingTime) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime -= 1000L
        }
    }

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

private fun parseTimeToMilliseconds(startTime: String): Long {
    val timeParts = startTime.split(":").map { it.toInt() }
    return (timeParts[0] * 3600 + timeParts[1] * 60 + timeParts[2]) * 1000L
}

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