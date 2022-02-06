package com.test.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test.core.data.source.remote.response.ContentResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListContentResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val result: List<ContentResponse>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null
) : Parcelable