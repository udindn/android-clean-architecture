package com.test.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Photo")
class PhotoEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "albumId")
    val albumId: Int,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String,

    @ColumnInfo(name = "url")
    val url: String
)