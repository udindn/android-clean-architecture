package com.test.core.data.source.remote

import android.util.Log
import com.test.core.data.source.remote.network.ApiResponse
import com.test.core.data.source.remote.network.ApiService
import com.test.core.data.source.remote.response.*
import com.test.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

//
class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllPost(): Flow<ApiResponse<List<AllPostResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                EspressoIdlingResource.increment()
                val response = apiService.getAllPostUser()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
                EspressoIdlingResource.decrement()
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllUser(): Flow<ApiResponse<List<UserPostResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getUserList()
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCommentById(postId: Int): Flow<ApiResponse<List<CommentResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getCommentListByPost(postId)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAlbumByUserId(userId: Int): Flow<ApiResponse<List<AlbumResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getAlbumsListByUser(userId)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPhotoByAlbum(albumId: Int): Flow<ApiResponse<List<PhotoResponseItem>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getPhotosListByAlbum(albumId)
                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}