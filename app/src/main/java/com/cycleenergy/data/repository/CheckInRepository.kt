package com.cycleenergy.data.repository

import com.cycleenergy.data.db.CheckInDao
import com.cycleenergy.data.db.entity.toDomain
import com.cycleenergy.data.db.entity.toEntity
import com.cycleenergy.domain.model.CheckIn
import com.cycleenergy.domain.model.CyclePhase
import com.cycleenergy.domain.model.TimeOfDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CheckInRepository(private val checkInDao: CheckInDao) {

    suspend fun saveCheckIn(checkIn: CheckIn): Long {
        return checkInDao.insert(checkIn.toEntity())
    }

    suspend fun updateCheckIn(checkIn: CheckIn) {
        checkInDao.update(checkIn.toEntity())
    }

    suspend fun deleteCheckIn(checkIn: CheckIn) {
        checkInDao.delete(checkIn.toEntity())
    }

    suspend fun getCheckInById(id: Long): CheckIn? {
        return checkInDao.getById(id)?.toDomain()
    }

    fun getAllCheckInsFlow(): Flow<List<CheckIn>> {
        return checkInDao.getAllFlow().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun getAllCheckIns(): List<CheckIn> {
        return checkInDao.getAll().map { it.toDomain() }
    }

    suspend fun getCheckInsByPhase(phase: CyclePhase): List<CheckIn> {
        return checkInDao.getByPhase(phase.name).map { it.toDomain() }
    }

    suspend fun getCheckInsByTimeOfDay(timeOfDay: TimeOfDay): List<CheckIn> {
        return checkInDao.getByTimeOfDay(timeOfDay.name).map { it.toDomain() }
    }

    fun getRecentCheckInsFlow(limit: Int = 10): Flow<List<CheckIn>> {
        return checkInDao.getRecentFlow(limit).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun deleteAllCheckIns() {
        checkInDao.deleteAll()
    }

    suspend fun getCheckInsCount(): Int {
        return checkInDao.getCount()
    }
}
