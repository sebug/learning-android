package ch.sebug.learntogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.sebug.learntogether.R.drawable.bg_compose_background
import ch.sebug.learntogether.R.string.first_paragraph
import ch.sebug.learntogether.R.string.main_title
import ch.sebug.learntogether.R.string.second_paragraph
import ch.sebug.learntogether.ui.theme.LearnTogetherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnTogetherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DocumentationPage()
                }
            }
        }
    }
}

@Composable
fun DocumentationPage(modifier: Modifier = Modifier) {
    Column {
        HeaderImage()
        DocumentationHeader()
        FirstParagraph()
        SecondParagraph()
    }
}

@Composable
fun DocumentationHeader(modifier: Modifier = Modifier) {
    val titleText = stringResource(id = main_title)
    Text(text = titleText,
        fontSize = 24.sp,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp))
}

@Composable
fun FirstParagraph(modifier: Modifier = Modifier) {
    val firstParagraphText = stringResource(id = first_paragraph)
    Text(text = firstParagraphText,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(start = 16.dp,
            end = 16.dp))
}

@Composable
fun SecondParagraph(modifier: Modifier = Modifier) {
    val secondParagraphText = stringResource(id = second_paragraph)
    Text(text = secondParagraphText,
        textAlign = TextAlign.Justify,
        modifier = modifier.padding(16.dp))
}

@Composable
fun HeaderImage(modifier: Modifier = Modifier) {
    val painter = painterResource(bg_compose_background)
    Image(painter = painter,
        contentDescription =  null
        )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnTogetherTheme {
        DocumentationPage()
    }
}