package com.test.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CommentModel (
    val postId: Int,
    val id: Int,
    val name: String? = "",
    val email: String? = "",
    val body: String? = ""
) : Parcelable