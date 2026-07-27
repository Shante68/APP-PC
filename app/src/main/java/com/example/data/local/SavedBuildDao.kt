package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedBuildDao {
    @Query("SELECT * FROM saved_builds ORDER BY createdAt DESC")
    fun getAllSavedBuilds(): Flow<List<SavedBuildEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBuild(build: SavedBuildEntity): Long

    @Query("DELETE FROM saved_builds WHERE id = :id")
    suspend fun deleteBuildById(id: Int)

    @Query("SELECT * FROM saved_builds WHERE id = :id")
    suspend fun getBuildById(id: Int): SavedBuildEntity?
}
