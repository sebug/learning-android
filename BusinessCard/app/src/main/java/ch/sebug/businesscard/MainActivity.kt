package ch.sebug.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ch.sebug.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactInformation = ContactInformation("Ian Freely", "Dr.",
            "+41 123 456",
            socialMediaHandle = "@ian_freely",
            email = "ian.freely@gmail.com")
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(contactInformation = contactInformation)
                }
            }
        }
    }
}

@Composable
fun BusinessCard(contactInformation: ContactInformation, modifier: Modifier = Modifier) {
    Text(
        text = contactInformation.fullName,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val contactInformation = ContactInformation("Ian Freely", "Dr.",
        "+41 123 456",
        socialMediaHandle = "@ian_freely",
        email = "ian.freely@gmail.com")
    BusinessCardTheme {
        BusinessCard(contactInformation = contactInformation)
    }
}