package com.example.myandroidproject.core.domain.model.detailpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.ItemAbilityResponse

data class ItemAbilityModel (
    val name: String,
    val url: String
) {
    constructor(itemAbilityResponse: ItemAbilityResponse?): this (
        name = itemAbilityResponse?.name.orEmpty(),
        url = itemAbilityResponse?.url.orEmpty()
    )
}