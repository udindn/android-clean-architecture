package com.test.core.data.source.remote.network

import com.test.core.data.source.remote.response.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getAllPostUser(
    ): AllPostResponse

    @GET("users")
    suspend fun getUserList(
    ): List<UserPostResponse>

    @GET("posts/{id}/comments")
    suspend fun getCommentListByPost(
        @Path("id") id: Int
    ): CommentResponse

    @GET("users/{id}/albums")
    suspend fun getAlbumsListByUser(
        @Path("id") id: Int
    ): AlbumResponse

    @GET("albums/{id}/photos")
    suspend fun getPhotosListByAlbum(
        @Path("id") id: Int
    ): PhotoResponse
}