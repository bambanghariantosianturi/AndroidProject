package com.example.myandroidproject.core.domain.model.detailpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse

data class DetailPokemonModel(
    val id: Int?,
    val name: String?,
    val height: String?,
    val weight: String?,
    val abilities: List<AbilityModel>?,
    val typeResponses: List<TypePokemonModel>?
) {
    constructor(entity: DetailPokemonResponse?) : this(
        id = entity?.id ?: 0,
        name = entity?.name.orEmpty(),
        height = entity?.height.orEmpty(),
        weight = entity?.weight.orEmpty(),
        abilities = entity?.abilities?.map {
            AbilityModel(
                isHidden = it.isHidden,
                slot = it.slot,
                ability = ItemAbilityModel(it.ability)
            )
        }.orEmpty(),
        typeResponses = entity?.types?.map {
            TypePokemonModel(slot = it.slot, type = ItemTypeModel(it.type))
        }.orEmpty()
    )
}