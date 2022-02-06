package com.test.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class AlbumResponse : ArrayList<AlbumResponseItem>()

data class AlbumResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)