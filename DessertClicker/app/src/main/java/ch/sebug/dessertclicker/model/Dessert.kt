package ch.sebug.dessertclicker.model

import androidx.annotation.DrawableRes

data class Dessert(@DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmount: Int)
