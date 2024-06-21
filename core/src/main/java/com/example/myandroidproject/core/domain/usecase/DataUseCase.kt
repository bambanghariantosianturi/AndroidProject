package com.example.myandroidproject.core.domain.usecase

import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ListPokemonModel

interface DataUseCase {

    suspend fun getDetailPokemon(pokemonName: String): Result<DetailPokemonModel>

    suspend fun getGenreMovie(): Result<ListPokemonModel>?
}