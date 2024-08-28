package com.example.myandroidproject.core.domain.model.listnewsmodel

import android.os.Parcelable
import com.example.myandroidproject.core.data.source.remote.response.listnews.ItemNewsResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListNewsModel (
    val id: String,
    val title: String,
    val description: String,
    val bannerUrl: String,
    val timeCreated: Int,
    val rank: Int
): Parcelable {
    constructor(entity: ItemNewsResponse?): this(
        id = entity?.id.orEmpty(),
        title = entity?.title.orEmpty(),
        description = entity?.description.orEmpty(),
        bannerUrl = entity?.bannerUrl.orEmpty(),
        timeCreated = entity?.timeCreated ?: 0,
        rank = entity?.rank ?: 0
    )
}