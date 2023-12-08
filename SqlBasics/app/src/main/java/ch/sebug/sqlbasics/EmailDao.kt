package ch.sebug.sqlbasics

import androidx.room.Dao
import androidx.room.Query

@Dao
interface EmailDao {
    @Query("SELECT * FROM email")
    suspend fun getAll(): List<Email>
}