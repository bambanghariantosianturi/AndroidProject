package com.example.myandroidproject.core.domain.model.detailpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.TypeResponse

data class TypePokemonModel(
    val slot: Int?,
    val type: ItemTypeModel?
) {
    constructor(typeResponse: TypeResponse?) : this(
        slot = typeResponse?.slot ?: 0,
        type = ItemTypeModel(
            name = typeResponse?.type?.name.orEmpty(),
            url = typeResponse?.type?.url.orEmpty()
        )
    )
}