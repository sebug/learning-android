package ch.sebug.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    assertEquals(expectedRouteName, this.currentBackStackEntry?.destination?.route)
}