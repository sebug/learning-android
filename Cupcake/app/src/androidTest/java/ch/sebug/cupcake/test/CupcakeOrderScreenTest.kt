package ch.sebug.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import ch.sebug.cupcake.ui.SelectOptionScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        // Given list of options
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        // And subtotal
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(ch.sebug.cupcake.R.string.subtotal_price,
                subtotal)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(ch.sebug.cupcake.R.string.next)
            .assertIsNotEnabled()
    }
}