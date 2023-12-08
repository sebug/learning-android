package ch.sebug.marsphotos

import android.app.Application
import ch.sebug.marsphotos.data.AppContainer
import ch.sebug.marsphotos.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}