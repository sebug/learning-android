package ch.sebug.marsphotos.fake

import ch.sebug.marsphotos.network.MarsApiService
import ch.sebug.marsphotos.network.MarsPhoto

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}