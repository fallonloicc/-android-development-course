package com.studi.workshopmovieapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studi.workshopmovieapp.model.dao.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovie(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()
}