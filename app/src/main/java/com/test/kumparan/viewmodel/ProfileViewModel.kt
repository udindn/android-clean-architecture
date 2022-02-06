package com.test.kumparan.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.test.core.data.Resource
import com.test.core.domain.model.AlbumModel
import com.test.core.domain.model.CommentModel
import com.test.core.domain.model.PhotoModel
import com.test.core.domain.model.UserModel
import com.test.core.domain.usecase.ContentUseCase

class ProfileViewModel(private val contentUseCase: ContentUseCase) : ViewModel() {

    internal fun getAlbum(userId: Int): LiveData<Resource<List<AlbumModel>>> {
        return contentUseCase.getAlbumByUserId(userId).asLiveData()
    }

    internal fun getPhoto(albumId: Int): LiveData<Resource<List<PhotoModel>>> {
        return contentUseCase.getPhotoByAlbumId(albumId).asLiveData()
    }

    val userFromDB = contentUseCase.getAllUserFromDB().asLiveData()

    internal fun deletePhoto() = contentUseCase.deletePhoto()
    internal fun deleteAlbum() = contentUseCase.deleteAlbum()
}