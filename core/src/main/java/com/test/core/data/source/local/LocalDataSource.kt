package com.test.core.data.source.local

import com.test.core.data.source.local.entity.*
import com.test.core.data.source.local.room.AlbumDao
import com.test.core.data.source.local.room.CommentDao
import com.test.core.data.source.local.room.PostDao
import com.test.core.data.source.local.room.UserDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val postDao: PostDao,
    private val userDao: UserDao,
    private val commentDao: CommentDao,
    private val albumDao: AlbumDao
) {

    fun getAllPost(): Flow<List<PostEntity>> = postDao.getAllPost()

    suspend fun insertPost(postList: List<PostEntity>) = postDao.insertPost(postList)

    suspend fun updatePost(userId: Int, name: String, company: String) =
        postDao.updatePost(userId, name, company)

    fun getAllUser(): Flow<List<UserEntity>> = userDao.getUser()

    suspend fun insertUser(userList: List<UserEntity>) = userDao.insertUser(userList)

    fun getCommentByPostId(): Flow<List<CommentEntity>> = commentDao.getCommentByPostId()

    suspend fun insertComment(commentList: List<CommentEntity>) = commentDao.insertComment(commentList)

    fun deleteComment() = commentDao.deleteComment()

    fun getAlbum(): Flow<List<AlbumEntity>> = albumDao.getAlbum()

    suspend fun insertAlbum(albumList: List<AlbumEntity>) = albumDao.insertAlbum(albumList)

    fun deleteAlbum() = albumDao.deleteAlbum()

    fun getPhoto(): Flow<List<PhotoEntity>> = albumDao.getPhoto()

    suspend fun insertPhoto(photoList: List<PhotoEntity>) = albumDao.insertPhoto(photoList)

    fun deletePhoto() = albumDao.deletePhoto()
}