package com.test.core.domain.repository

import com.test.core.data.Resource
import com.test.core.domain.model.*
import kotlinx.coroutines.flow.Flow

interface IContentRepository {

    fun getAllPost(): Flow<Resource<List<PostModel>>>

    fun getAllUserPost(): Flow<Resource<List<UserModel>>>

    fun getAllPostFromDB(): Flow<List<PostModel>>

    fun getAllUserFromDB(): Flow<List<UserModel>>

    fun getCommentById(postId: Int): Flow<Resource<List<CommentModel>>>

    fun deleteComment()

    fun getUserById(userId: Int): Flow<Resource<UserModel>>

    fun getAlbumByUserId(userId: Int): Flow<Resource<List<AlbumModel>>>

    fun deleteAlbum()

    fun getPhotoByAlbumId(albumId: Int): Flow<Resource<List<PhotoModel>>>

    fun deletePhoto()
}