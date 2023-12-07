package ch.sebug.dessertclicker.ui

import androidx.annotation.DrawableRes
import ch.sebug.dessertclicker.data.Datasource

data class DessertClickerUiState(val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertIndex: Int = 0,
    val currentDessertPrice: Int = Datasource.dessertList[0].price,
    @DrawableRes val currentDessertImageId: Int = Datasource.dessertList[0].imageId)