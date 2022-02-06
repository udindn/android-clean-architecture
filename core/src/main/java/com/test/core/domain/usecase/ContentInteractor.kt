package com.test.core.domain.usecase

import com.test.core.domain.repository.IContentRepository

class ContentInteractor(private val contentRepository: IContentRepository) : ContentUseCase {

    override fun getAllPost() = contentRepository.getAllPost()

    override fun getAllPostFromDB() = contentRepository.getAllPostFromDB()

    override fun getAllUserFromDB() = contentRepository.getAllUserFromDB()

    override fun getUserById(userId: Int) = contentRepository.getUserById(userId)

    override fun getAllUser() =  contentRepository.getAllUserPost()

    override fun getCommentByPostId(postId: Int) = contentRepository.getCommentById(postId)

    override fun deleteComment() = contentRepository.deleteComment()

    override fun getAlbumByUserId(userId: Int) = contentRepository.getAlbumByUserId(userId)

    override fun deleteAlbum() = contentRepository.deleteAlbum()

    override fun getPhotoByAlbumId(albumId: Int) = contentRepository.getPhotoByAlbumId(albumId)

    override fun deletePhoto() = contentRepository.deletePhoto()
}