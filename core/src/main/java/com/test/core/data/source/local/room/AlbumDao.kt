package com.test.core.data.source.local.room

import androidx.room.*
import com.test.core.data.source.local.entity.AlbumEntity
import com.test.core.data.source.local.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {

    @Query("SELECT * FROM Album")
    fun getAlbum(): Flow<List<AlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: List<AlbumEntity>)

    @Query("SELECT * FROM Photo")
    fun getPhoto(): Flow<List<PhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(album: List<PhotoEntity>)

    @Query("DELETE FROM Album")
    fun deleteAlbum()

    @Query("DELETE FROM Photo")
    fun deletePhoto()
}