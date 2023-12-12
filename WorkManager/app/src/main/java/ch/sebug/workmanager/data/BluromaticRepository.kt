package ch.sebug.workmanager.data

import androidx.work.WorkInfo
import kotlinx.coroutines.flow.Flow

interface BluromaticRepository {
    val outputWorkInfo: Flow<WorkInfo?>
    fun applyBlur(blurLevel: Int)
    fun cancelWork()
}