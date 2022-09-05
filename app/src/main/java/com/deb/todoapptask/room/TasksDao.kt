package com.deb.todoapptask.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(tasks: Tasks) : Long

    @Update
    suspend fun updateTask(tasks: Tasks) : Int

    @Query("SELECT * FROM table_todo ORDER BY datecreated DESC")
    fun getAllTasks(): Flow<List<Tasks>>

    @Query("SELECT task FROM table_todo")
    fun getOnlyTasks() : List<String>
}