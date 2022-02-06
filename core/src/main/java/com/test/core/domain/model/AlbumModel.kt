package com.test.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class AlbumModel (
    val id: Int,
    val title: String,
    val userId: Int
) : Parcelable