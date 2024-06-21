package com.example.myandroidproject.core.data.source.remote.network

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ListPokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{pokemonName}")
    suspend fun getDetailData(
        @Path("pokemonName") pokemonName: String?,
    ): Response<DetailPokemonResponse>

    @GET("pokemon")
    suspend fun getListData(
    ): Response<ListPokemonResponse>
}