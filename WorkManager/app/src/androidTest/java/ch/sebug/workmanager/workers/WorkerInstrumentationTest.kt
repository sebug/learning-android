package ch.sebug.workmanager.workers

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.WorkRequest
import androidx.work.testing.TestListenableWorkerBuilder
import ch.sebug.workmanager.KEY_BLUR_LEVEL
import ch.sebug.workmanager.KEY_IMAGE_URI
import ch.sebug.workmanager.getImageUri
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WorkerInstrumentationTest {
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun cleanupWorker_doWork_resultSuccess() {
        val worker = TestListenableWorkerBuilder<CleanupWorker>(context)
            .build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }

    @Test
    fun blurWorker_doWork_resultSuccessReturnsUri() {
        val imageUri = context.getImageUri()
        val blurLevel = 1
        val builder = Data.Builder()
        builder.putString(KEY_IMAGE_URI, imageUri.toString()).putInt(KEY_BLUR_LEVEL, blurLevel)

        val worker = TestListenableWorkerBuilder<BlurWorker>(context)
            .setInputData(builder.build())
            .build()
        runBlocking {
            val result = worker.doWork()
            val resultUri = result.outputData.getString(KEY_IMAGE_URI)
            assert(resultUri!!.contains(".png"))
        }
    }
}