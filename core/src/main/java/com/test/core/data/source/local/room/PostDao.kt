package com.test.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.core.data.source.local.entity.PostEntity
import com.test.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getAllPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM Post WHERE id = :postId")
    fun getPostById(postId: Int): LiveData<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: List<PostEntity>)

    @Query("UPDATE Post SET name = :name, company = :company  WHERE user_id = :userId")
    suspend fun updatePost(userId: Int, name: String, company: String)

}
