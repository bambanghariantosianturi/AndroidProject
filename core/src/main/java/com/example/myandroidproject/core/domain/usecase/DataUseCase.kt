package com.example.myandroidproject.core.domain.usecase

import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import com.example.myandroidproject.core.domain.model.listnewsmodel.ListNewsModel

interface DataUseCase {

    suspend fun getDetailPokemon(pokemonName: String): Result<DetailPokemonModel>

    //suspend fun getGenreMovie(): Result<ListPokemonModel>?

    suspend fun getListData(): Result<List<ListNewsModel>>?
}