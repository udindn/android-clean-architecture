package com.test.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.core.data.source.local.entity.*

@Database(
    entities = [PostEntity::class, UserEntity::class, CommentEntity::class, AlbumEntity::class, PhotoEntity::class],
    version = 5,
    exportSchema = false
)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun userDao(): UserDao
    abstract fun commentDao(): CommentDao
    abstract fun albumDao(): AlbumDao
}