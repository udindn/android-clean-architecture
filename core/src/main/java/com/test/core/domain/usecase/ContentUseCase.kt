package com.test.core.domain.usecase

import com.test.core.data.Resource
import com.test.core.domain.model.PostModel
import com.test.core.domain.model.CommentModel
import com.test.core.domain.model.UserModel
import com.test.core.domain.model.AlbumModel
import com.test.core.domain.model.PhotoModel
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {

    fun getAllPost(): Flow<Resource<List<PostModel>>>

    fun getAllPostFromDB() : Flow<List<PostModel>>

    fun getAllUserFromDB() : Flow<List<UserModel>>

    fun getUserById(userId: Int) : Flow<Resource<UserModel>>

    fun getAllUser(): Flow<Resource<List<UserModel>>>

    fun getCommentByPostId(postId: Int): Flow<Resource<List<CommentModel>>>

    fun deleteComment()

    fun getAlbumByUserId(userId: Int): Flow<Resource<List<AlbumModel>>>

    fun deleteAlbum()

    fun getPhotoByAlbumId(albumId: Int): Flow<Resource<List<PhotoModel>>>

    fun deletePhoto()
}