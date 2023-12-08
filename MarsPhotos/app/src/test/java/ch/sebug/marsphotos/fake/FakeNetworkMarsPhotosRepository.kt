package ch.sebug.marsphotos.fake

import ch.sebug.marsphotos.data.MarsPhotosRepository
import ch.sebug.marsphotos.network.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}