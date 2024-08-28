package com.example.myandroidproject.core.domain.repository

import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listnews.ItemNewsResponse

interface IDataRepository {
    suspend fun getDetailPokemon(pokemonName: String): Resource<DetailPokemonResponse>?

    //suspend fun getListData(): Resource<ListPokemonResponse>?

    suspend fun getListData(): Resource<List<ItemNewsResponse>>?
}