package ch.sebug.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.sebug.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeActionablePicture()
                }
            }
        }
    }
}

@Composable
fun LemonadeActionablePicture(initialState: Int = 1, modifier: Modifier = Modifier
    .fillMaxSize()) {
    Column(modifier = modifier) {
        LemonadeHeader()
        LemonadeActions(initialState = initialState)
    }
}

@Composable
fun LemonadeActions(initialState: Int, modifier: Modifier = Modifier) {
    var state by remember {
        mutableStateOf(initialState)
    }
    var numberOfSqueezes by remember {
        mutableStateOf(2)
    }
    val imageResource = when (state) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_restart
    }
    val contentDescriptionResource = when (state) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        4 -> R.string.empty_glass
        else -> R.string.empty_glass
    }
    val textResource = when (state) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.keep_tapping_to_squeeze
        3 -> R.string.tap_to_drink
        4 -> R.string.tap_empty_glass
        else -> R.string.tap_empty_glass
    }
    Column(modifier = modifier
        .fillMaxSize()
        .wrapContentSize(align = Alignment.Center)) {
        Button(onClick =
        {
            var newState: Int = state
            if (state == 1) {
                newState = 2
                numberOfSqueezes = (2..4).random()
            } else if (state == 2) {
                numberOfSqueezes -= 1
                if (numberOfSqueezes <= 0) {
                    newState = 3
                }
            } else if (state == 3) {
                newState = 4
            } else {
                newState = 1
            }
            state = newState
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(203, 235, 212)
            )
        ) {
            Image(painter = painterResource(id = imageResource),
                contentDescription = stringResource(contentDescriptionResource),
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
            )
        }
        Text(text = stringResource(textResource),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun LemonadeHeader(modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.lemonade),
        fontSize = 24.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(Color(245, 225, 104))
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonadeActionablePicture(2)
    }
}