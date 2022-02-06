package com.test.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comment")
class CommentEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "postId")
    val postId: Int,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "body")
    val body: String,
)