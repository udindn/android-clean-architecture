package com.test.core.utils

import com.test.core.data.source.local.entity.*
import com.test.core.data.source.remote.response.*
import com.test.core.domain.model.*

object DataMapper {
    fun mapPostResponseToEntities(input: List<AllPostResponseItem>): List<PostEntity> {
        val postList = ArrayList<PostEntity>()
        input.map {
            val post = PostEntity(
                id = it.id,
                title = it.title,
                body = it.body,
                name = "",
                company = "",
                userId = it.userId
            )
            postList.add(post)
        }
        return postList
    }

    fun mapUserResponsesToEntities(input: List<UserPostResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                phone = it.phone,
                website = it.website,
                address = it.address.city,
                companyName = it.company.name
            )
            userList.add(user)
        }
        return userList
    }

    fun mapPostEntitiesToDomain(input: List<PostEntity>): List<PostModel> =
        input.map {
            PostModel(
                id = it.id,
                title = it.title,
                body = it.body,
                name = it.name,
                company = it.company,
                userId = it.userId
            )
        }

    fun mapUserEntitiesToDomain(input: List<UserEntity>): List<UserModel> =
        input.map {
            UserModel(
                id = it.id,
                name = it.name,
                username = it.username,
                email = it.email,
                phone = it.phone,
                website = it.website,
                address = it.address,
                companyName = it.companyName
            )
        }

    fun mapUserEntitiesToDomain(input: UserEntity): UserModel =
        UserModel(
            id = input.id,
            name = input.name,
            username = input.username,
            email = input.email,
            phone = input.phone,
            website = input.website,
            address = input.address,
            companyName = input.companyName
        )

    fun mapCommentEntityToDomain(input: List<CommentEntity>): List<CommentModel> =
        input.map {
            CommentModel(
                postId = it.postId,
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }

    fun mapAlbumEntityToDomain(input: List<AlbumEntity>): List<AlbumModel> =
        input.map {
            AlbumModel(
                id = it.id,
                title = it.title,
                userId = it.userId
            )
        }

    fun mapPhotoEntityToDomain(input: List<PhotoEntity>): List<PhotoModel> =
        input.map {
            PhotoModel(
                id = it.id,
                title = it.title,
                albumId = it.albumId,
                thumbnailUrl = it.thumbnailUrl,
                url = it.url
            )
        }

    fun mapCommentResponseToEntities(input: List<CommentResponseItem>): List<CommentEntity> =
        input.map {
            CommentEntity(
                postId = it.postId,
                id = it.id,
                name = it.name,
                email = it.email,
                body = it.body
            )
        }

    fun mapAlbumResponseToEntities(input: List<AlbumResponseItem>): List<AlbumEntity> =
        input.map {
            AlbumEntity(
                id = it.id,
                title = it.title,
                userId = it.userId
            )
        }

    fun mapPhotoResponseToEntities(input: List<PhotoResponseItem>): List<PhotoEntity> =
        input.map {
            PhotoEntity(
                id = it.id,
                title = it.title,
                albumId = it.albumId,
                thumbnailUrl = it.thumbnailUrl,
                url = it.url,
            )
        }
//
//    fun mapDomainToTvShowsEntity(input: ContentModel) = TvShowsEntity(
//        id = input.id,
//        cover = input.posterPath.toString(),
//        title = input.title.toString(),
//        year = parseDateToYear(input.firstAirDate.toString()).toString(),
//        desc = input.overview.toString(),
//        firstAirDate = input.firstAirDate.toString(),
//        runtime = input.runtime ?: 0,
//        popularity = input.popularity ?: 0.0,
//        originalLanguage = input.originalLanguage.toString(),
//        voteAverage = input.voteAverage ?: 0.0,
//        voteCount = input.voteCount ?: 0.0,
//        createdBy = input.createdBy.toString(),
//        isFavorite = input.isFavorite
//    )
}