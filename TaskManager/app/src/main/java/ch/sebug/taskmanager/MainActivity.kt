package ch.sebug.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.sebug.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TasksCompleted()
                }
            }
        }
    }
}

@Composable
fun TasksCompleted(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()) {
        CheckmarkImage()
        AllTasksCompleted()
        NiceWork()
    }
}

@Composable
fun CheckmarkImage(modifier: Modifier = Modifier) {
    val checkmarkPainter = painterResource(id = R.drawable.ic_task_completed)
    Image(painter = checkmarkPainter,
        contentDescription = null,
        modifier = modifier.fillMaxWidth())
}

@Composable
fun AllTasksCompleted(modifier: Modifier = Modifier) {
    val allCompleted = stringResource(id = R.string.all_tasks_completed)
    Text(text = allCompleted,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = modifier.fillMaxWidth()
            .padding(top = 24.dp, bottom = 8.dp))
}

@Composable
fun NiceWork(modifier: Modifier = Modifier) {
    val niceWork = stringResource(id = R.string.nice_work)
    Text(text = niceWork,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskManagerTheme {
        TasksCompleted()
    }
}