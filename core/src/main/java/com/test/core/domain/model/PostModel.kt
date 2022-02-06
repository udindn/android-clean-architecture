package com.test.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PostModel(
    val id: Int,
    val title: String? = "",
    val body: String? = "",
    var name: String? = "",
    var company: String? = "",
    val userId: Int,
) : Parcelable
