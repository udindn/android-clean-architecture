package com.test.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class PhotoResponse : ArrayList<PhotoResponseItem>()

data class PhotoResponseItem(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)