package com.cycleenergy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cycleenergy.data.db.entity.CheckInEntity

@Database(
    entities = [CheckInEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CycleDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckInDao

    companion object {
        @Volatile
        private var INSTANCE: CycleDatabase? = null

        fun getDatabase(context: Context): CycleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CycleDatabase::class.java,
                    "cycle_energy_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
