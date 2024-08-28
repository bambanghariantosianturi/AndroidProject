package com.example.myandroidproject.core.data.source.remote.network

import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listnews.ItemNewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{pokemonName}")
    suspend fun getDetailData(
        @Path("pokemonName") pokemonName: String?,
    ): Response<DetailPokemonResponse>

//    @GET("pokemon")
//    suspend fun getListData(
//    ): Response<ListPokemonResponse>

    @GET("carousell_news.json")
    suspend fun getListData(): Response<List<ItemNewsResponse>>
}