package com.example.myandroidproject.core.data.source.remote

import com.example.myandroidproject.core.data.source.remote.network.ApiService
import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ListPokemonResponse
//import com.example.myandroidproject.core.utils.JsonHelper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getGenre(): Response<ListPokemonResponse> {
        return apiService.getListData()
    }

    suspend fun getDetailPokemon(pokemonName: String): Response<DetailPokemonResponse>? {
        return apiService.getDetailData(pokemonName)
    }
}



