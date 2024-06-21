package com.example.myandroidproject.core.domain.model.detailpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.AbilityResponse

data class AbilityModel(
    val isHidden: Boolean?,
    val slot: Int?,
    val ability: ItemAbilityModel?
) {
    constructor(abilityResponse: AbilityResponse?) : this(
        isHidden = abilityResponse?.isHidden ?: false,
        slot = abilityResponse?.slot ?: 0,
        ability = ItemAbilityModel(
            abilityResponse?.ability?.name.orEmpty(),
            abilityResponse?.ability?.url.orEmpty()
        )
    )
}