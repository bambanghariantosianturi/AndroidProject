package com.example.myandroidproject.core.data.source.remote.response.listpokemon

data class ListPokemonResponse(
    val count: Long?,
    val next: String?,
    val previous: Any?,
    val results: List<ItemPokemonResponse>?,
)

data class ItemPokemonResponse(
    val name: String?,
    val url: String?,
)