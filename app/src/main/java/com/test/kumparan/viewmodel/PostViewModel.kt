package com.test.kumparan.viewmodel

import androidx.lifecycle.*
import com.test.core.data.Resource
import com.test.core.domain.model.CommentModel
import com.test.core.domain.usecase.ContentUseCase

class PostViewModel(private val contentUseCase: ContentUseCase) : ViewModel() {
    val post = contentUseCase.getAllPost().asLiveData()
    val user = contentUseCase.getAllUser().asLiveData()

    internal fun getResponse(postId: Int): LiveData<Resource<List<CommentModel>>> {
        return contentUseCase.getCommentByPostId(postId).asLiveData()
    }

    internal fun deleteComment() = contentUseCase.deleteComment()
}