package ch.sebug.marsphotos.ui.screens

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ch.sebug.marsphotos.data.NetworkMarsPhotosRepository
import ch.sebug.marsphotos.network.MarsApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading: MarsUiState
}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMarsPhotos() {
        viewModelScope.launch {
            marsUiState = try {
                val marsPhotosRepository = NetworkMarsPhotosRepository()
                val listResult = marsPhotosRepository.getMarsPhotos()
                MarsUiState.Success(
                    "Success: ${listResult.size} Mars photos retrieved"
                )
            } catch (ioe: IOException) {
                Log.e("MarsPhotos", ioe.message ?: "An IO error occurred")
                MarsUiState.Error
            } catch (e: HttpException) {
                MarsUiState.Error
            }
        }
    }
}