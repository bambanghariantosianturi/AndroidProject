package com.example.myandroidproject.core.domain.model.detailpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.ItemTypeResponse

data class ItemTypeModel (
    val name: String?,
    val url: String?
) {
    constructor(itemTypeResponse: ItemTypeResponse?): this (
        name = itemTypeResponse?.name.orEmpty(),
        url = itemTypeResponse?.url.orEmpty()
    )
}