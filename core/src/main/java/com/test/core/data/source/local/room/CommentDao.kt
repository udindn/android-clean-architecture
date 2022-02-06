package com.test.core.data.source.local.room

import androidx.room.*
import com.test.core.data.source.local.entity.CommentEntity
import com.test.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {

    @Query("SELECT * FROM Comment")
    fun getCommentByPostId(): Flow<List<CommentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(user: List<CommentEntity>)

    @Query("DELETE FROM Comment")
    fun deleteComment()
}