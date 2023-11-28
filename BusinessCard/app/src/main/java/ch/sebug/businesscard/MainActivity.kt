package ch.sebug.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    Column(modifier = modifier.fillMaxSize()) {
        Heading(contactInformation = contactInformation)
        ContactInformationList(contactInformation = contactInformation)
    }
}

@Composable
fun Heading(contactInformation: ContactInformation, modifier: Modifier = Modifier) {
    val picturePainter = painterResource(id = R.drawable.android_logo)
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Image(painter = picturePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.size(200.dp)
        )
        Text(text = contactInformation.fullName,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.fillMaxWidth())
        Text(text = contactInformation.title,
            fontSize = 24.sp)
    }
}

@Composable
fun ContactInformationList(contactInformation: ContactInformation,
                           modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
        ) {
        Row {
            Column {
                Row {
                    Icon(Icons.Rounded.Phone, contentDescription = "Phone",
                        modifier = modifier.padding(end = 8.dp))
                    Text(text = contactInformation.phoneNumber)
                }
                Row {
                    Icon(Icons.Rounded.Face, contentDescription = "Social Media",
                        modifier = modifier.padding(end = 8.dp))
                    Text(text =  contactInformation.socialMediaHandle)
                }
                Row {
                    Icon(Icons.Rounded.Email,
                        contentDescription = "Email",
                        modifier = modifier.padding(end = 8.dp))
                    Text(text = contactInformation.email)
                }
            }
        }
    }
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