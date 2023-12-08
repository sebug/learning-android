package ch.sebug.marsphotos

import ch.sebug.marsphotos.fake.FakeDataSource
import ch.sebug.marsphotos.fake.FakeNetworkMarsPhotosRepository
import ch.sebug.marsphotos.rules.TestDispatcherRule
import ch.sebug.marsphotos.ui.screens.MarsUiState
import ch.sebug.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()
    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )
        assertEquals(
            MarsUiState.Success(FakeDataSource.photosList),
            marsViewModel.marsUiState
        )
    }
}