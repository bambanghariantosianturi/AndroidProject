package com.example.myandroidproject.core.domain.model.listpokemonmodel

import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ListPokemonResponse

data class ListPokemonModel(
    val count: Long,
    val next: String,
    val previous: Any,
    val results: List<ItemPokemonModel>,
) {
    constructor(entity: ListPokemonResponse?) : this(
        count = entity?.count ?: 0,
        next = entity?.next.orEmpty(),
        previous = entity?.previous ?: Any(),
        results = entity?.results?.map {
            ItemPokemonModel(it)
        }.orEmpty()
    )
}