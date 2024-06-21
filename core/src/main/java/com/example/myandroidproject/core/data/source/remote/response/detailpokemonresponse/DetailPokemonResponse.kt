package com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse

import com.google.gson.annotations.SerializedName

data class DetailPokemonResponse (
    val id: Int?,
    val name: String?,
    val height: String?,
    val weight: String?,
    val abilities: List<AbilityResponse>?,
    val types: List<TypeResponse>?
)

data class AbilityResponse(
    @SerializedName("is_hidden")
    val isHidden: Boolean?,
    val slot: Int?,
    val ability: ItemAbilityResponse?
)

data class ItemAbilityResponse(
    val name: String?,
    val url: String?
)

data class TypeResponse(
    val slot: Int?,
    val type: ItemTypeResponse?
)

data class ItemTypeResponse(
    val name: String?,
    val url: String?
)