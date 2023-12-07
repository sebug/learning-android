package ch.sebug.dessertclicker.ui

import androidx.lifecycle.ViewModel
import ch.sebug.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertClickerViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DessertClickerUiState())
    val uiState: StateFlow<DessertClickerUiState> = _uiState.asStateFlow()

    fun chooseCurrentDessert() {
        _uiState.update {
            val newRevenue = it.revenue + it.currentDessertPrice
            val newDessertsSold = it.dessertsSold + 1
            val dessertToShow = determineDessertToShow(it.desserts, newDessertsSold)
            val newDessertIndex = it.desserts.indexOf(dessertToShow)

            it.copy(
                revenue = newRevenue,
                dessertsSold = newDessertsSold,
                currentDessertIndex = newDessertIndex,
                currentDessertImageId = dessertToShow.imageId,
                currentDessertPrice = dessertToShow.price
            )
        }
    }

    fun determineDessertToShow(
        desserts: List<Dessert>,
        dessertsSold: Int
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
}