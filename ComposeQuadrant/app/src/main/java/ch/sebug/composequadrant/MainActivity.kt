package ch.sebug.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ch.sebug.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Quadrants()
                }
            }
        }
    }
}

@Composable
fun Quadrants(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier
            .fillMaxSize()
            .weight(1F)) {
            Quadrant(
                titleResourceID = R.string.text_composable_title,
                descriptionResourceID = R.string.text_composable_description,
                backgroundColorID = R.color.text_composable_color,
                modifier = modifier.weight(1F)
            )
            Quadrant(
                titleResourceID = R.string.image_composable_title,
                descriptionResourceID = R.string.image_composable_description,
                backgroundColorID = R.color.image_composable_color,
                modifier = modifier.weight(1F)
            )
        }
        Row(modifier = modifier
            .fillMaxSize()
            .weight(1F)) {
            Quadrant(
                titleResourceID = R.string.row_composable_title,
                descriptionResourceID = R.string.row_composable_description,
                backgroundColorID = R.color.row_composable_color,
                modifier = modifier.weight(1F)
            )
            Quadrant(
                titleResourceID = R.string.column_composable_title,
                descriptionResourceID = R.string.column_composable_description,
                backgroundColorID = R.color.column_composable_color,
                modifier = modifier.weight(1F)
            )
        }
    }
}

@Composable
fun Quadrant(
    titleResourceID: Int,
    descriptionResourceID: Int,
    backgroundColorID: Int,
    modifier: Modifier = Modifier) {
    val color = colorResource(id = backgroundColorID)
    val title = stringResource(id = titleResourceID)
    val description = stringResource(id = descriptionResourceID)
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .background(color)
            .padding(16.dp)
    ) {
        Title(text = title,
            modifier = modifier)
        Description(text = description)
    }
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier) {
    Text(text = text,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(bottom = 16.dp))
}

@Composable
fun Description(text: String, modifier: Modifier = Modifier) {
    Text(text = text,
        textAlign = TextAlign.Justify)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        Quadrants()
    }
}