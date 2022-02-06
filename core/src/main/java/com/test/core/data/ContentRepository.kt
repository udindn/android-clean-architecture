package com.test.core.data

import com.test.core.data.source.remote.RemoteDataSource
import com.test.core.domain.repository.IContentRepository
import com.arrayyan.core.utils.AppExecutors
import com.test.core.utils.DataMapper
import com.test.core.data.source.local.LocalDataSource
import com.test.core.data.source.remote.network.ApiResponse
import com.test.core.data.source.remote.response.*
import com.test.core.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContentRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IContentRepository {

    override fun getAllPost(): Flow<Resource<List<PostModel>>> =
        object : NetworkBoundResource<List<PostModel>, List<AllPostResponseItem>>() {
            override fun loadFromDB(): Flow<List<PostModel>> {
                return localDataSource.getAllPost().map {
                    DataMapper.mapPostEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<PostModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<AllPostResponseItem>>> =
                remoteDataSource.getAllPost()

            override suspend fun saveCallResult(data: List<AllPostResponseItem>) {
                val postList = DataMapper.mapPostResponseToEntities(data)
                localDataSource.insertPost(postList)
            }
        }.asFlow()

    override fun getAllUserPost(): Flow<Resource<List<UserModel>>> =
        object : NetworkBoundResource<List<UserModel>, List<UserPostResponse>>() {
            override fun loadFromDB(): Flow<List<UserModel>> {
                return localDataSource.getAllUser().map {
                    DataMapper.mapUserEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<UserModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<UserPostResponse>>> =
                remoteDataSource.getAllUser()

            override suspend fun saveCallResult(data: List<UserPostResponse>) {
                val userList = DataMapper.mapUserResponsesToEntities(data)
                localDataSource.insertUser(userList)
                for (user in userList) {
                    localDataSource.updatePost(user.id, user.name, user.companyName)
                }
            }
        }.asFlow()

    override fun getAllPostFromDB(): Flow<List<PostModel>> {
        return localDataSource.getAllPost().map {
            DataMapper.mapPostEntitiesToDomain(it)
        }
    }

    override fun getAllUserFromDB(): Flow<List<UserModel>> {
        return localDataSource.getAllUser().map {
            DataMapper.mapUserEntitiesToDomain(it)
        }
    }

    override fun getCommentById(postId: Int): Flow<Resource<List<CommentModel>>> =
        object : NetworkBoundResource<List<CommentModel>, List<CommentResponseItem>>() {
            override fun loadFromDB(): Flow<List<CommentModel>> {
                return localDataSource.getCommentByPostId().map {
                    DataMapper.mapCommentEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<CommentModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<CommentResponseItem>>> =
                remoteDataSource.getCommentById(postId)

            override suspend fun saveCallResult(data: List<CommentResponseItem>) {
                val commentList = DataMapper.mapCommentResponseToEntities(data)
                localDataSource.insertComment(commentList)
            }
        }.asFlow()

    override fun deleteComment() {
        appExecutors.diskIO().execute {
            localDataSource.deleteComment()
        }
    }

    override fun getUserById(userId: Int): Flow<Resource<UserModel>> {
        TODO("Not yet implemented")
    }

    override fun getAlbumByUserId(userId: Int): Flow<Resource<List<AlbumModel>>> =
        object : NetworkBoundResource<List<AlbumModel>, List<AlbumResponseItem>>() {
            override fun loadFromDB(): Flow<List<AlbumModel>> {
                return localDataSource.getAlbum().map {
                    DataMapper.mapAlbumEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<AlbumModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<AlbumResponseItem>>> =
                remoteDataSource.getAlbumByUserId(userId)

            override suspend fun saveCallResult(data: List<AlbumResponseItem>) {
                val albumList = DataMapper.mapAlbumResponseToEntities(data)
                localDataSource.insertAlbum(albumList)
            }
        }.asFlow()

    override fun deleteAlbum() {
        appExecutors.diskIO().execute {
            localDataSource.deleteAlbum()
        }
    }

    override fun getPhotoByAlbumId(albumId: Int): Flow<Resource<List<PhotoModel>>> =
        object : NetworkBoundResource<List<PhotoModel>, List<PhotoResponseItem>>() {
            override fun loadFromDB(): Flow<List<PhotoModel>> {
                return localDataSource.getPhoto().map {
                    DataMapper.mapPhotoEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<PhotoModel>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<PhotoResponseItem>>> =
                remoteDataSource.getPhotoByAlbum(albumId)

            override suspend fun saveCallResult(data: List<PhotoResponseItem>) {
                val photosList = DataMapper.mapPhotoResponseToEntities(data)
                localDataSource.insertPhoto(photosList)
            }
        }.asFlow()

    override fun deletePhoto() {
        appExecutors.diskIO().execute {
            localDataSource.deletePhoto()
        }
    }
}