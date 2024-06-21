package com.example.myandroidproject.core.domain.model.listpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ItemPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ListPokemonResponse

data class ItemPokemonModel (
    val name: String,
    val url: String,
) {
    constructor(itemPokemonResponse: ItemPokemonResponse?): this (
        name = itemPokemonResponse?.name.orEmpty(),
        url = itemPokemonResponse?.url.orEmpty()
    )
}