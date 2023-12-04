package ch.sebug.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.sebug.courses.data.DataSource
import ch.sebug.courses.model.Topic
import ch.sebug.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesGrid(topics = DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun CoursesGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(topics) { topic ->
            TopicCard(topic = topic, modifier = modifier)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(id = topic.nameResourceId),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp))
            Column(modifier = Modifier
                .padding(start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 0.dp)) {
                Text(text = stringResource(topic.nameResourceId),
                    style = MaterialTheme.typography.bodyMedium)
                Row(modifier = Modifier
                    .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.MoreVert,
                        contentDescription = "Details",
                        modifier = Modifier.padding(end = 8.dp))
                    Text(text = topic.numberOfParticipants.toString(),
                        style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursesGridPreview() {
    CoursesTheme {
        CoursesGrid(topics = DataSource.topics)
    }
}