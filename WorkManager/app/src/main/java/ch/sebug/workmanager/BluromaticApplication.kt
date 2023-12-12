package ch.sebug.workmanager

import android.app.Application
import ch.sebug.workmanager.data.AppContainer
import ch.sebug.workmanager.data.DefaultAppContainer

class BluromaticApplication : Application()  {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}