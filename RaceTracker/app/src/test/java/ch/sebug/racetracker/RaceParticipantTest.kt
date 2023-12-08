package ch.sebug.racetracker

import ch.sebug.racetracker.ui.RaceParticipant
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RaceParticipantTest {
    private val raceParticipant = RaceParticipant("Runner 1")

    @Test
    fun raceParticipant_RaceStarted_ProgressUpdated() = runTest {
        val expectedProgress = 1
        launch {
            raceParticipant.run()
        }
        advanceTimeBy(raceParticipant.progressDelayMillis)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }

    @Test
    fun raceParticipant_RaceFinished_ProgressUpdated() = runTest {
        val expectedProgress = 100
        launch {
            raceParticipant.run()
        }
        advanceTimeBy(raceParticipant.progressDelayMillis * raceParticipant.maxProgress)
        runCurrent()
        assertEquals(expectedProgress, raceParticipant.currentProgress)
    }
}