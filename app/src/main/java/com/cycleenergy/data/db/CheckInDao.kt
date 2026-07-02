package com.cycleenergy.data.db

import androidx.room.*
import com.cycleenergy.data.db.entity.CheckInEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDao {
    @Insert
    suspend fun insert(checkIn: CheckInEntity): Long

    @Update
    suspend fun update(checkIn: CheckInEntity)

    @Delete
    suspend fun delete(checkIn: CheckInEntity)

    @Query("SELECT * FROM check_ins WHERE id = :id")
    suspend fun getById(id: Long): CheckInEntity?

    @Query("SELECT * FROM check_ins ORDER BY timestamp DESC")
    fun getAllFlow(): Flow<List<CheckInEntity>>

    @Query("SELECT * FROM check_ins ORDER BY timestamp DESC")
    suspend fun getAll(): List<CheckInEntity>

    @Query("SELECT * FROM check_ins WHERE cyclePhase = :phase ORDER BY timestamp DESC")
    suspend fun getByPhase(phase: String): List<CheckInEntity>

    @Query("SELECT * FROM check_ins WHERE timeOfDay = :timeOfDay ORDER BY timestamp DESC")
    suspend fun getByTimeOfDay(timeOfDay: String): List<CheckInEntity>

    @Query("SELECT * FROM check_ins ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentFlow(limit: Int): Flow<List<CheckInEntity>>

    @Query("DELETE FROM check_ins")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM check_ins")
    suspend fun getCount(): Int
}
