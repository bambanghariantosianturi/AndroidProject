package com.example.myandroidproject.core.data

import com.example.myandroidproject.core.data.source.local.LocalDataSource
import com.example.myandroidproject.core.data.source.remote.RemoteDataSource
import com.example.myandroidproject.core.data.source.remote.response.detailpokemonresponse.DetailPokemonResponse
import com.example.myandroidproject.core.data.source.remote.response.listpokemon.ListPokemonResponse
import com.example.myandroidproject.core.domain.repository.IDataRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IDataRepository {

    override suspend fun getListData(): Resource<ListPokemonResponse>? {
        val response = remoteDataSource.getListData()
        return try {
            response.body()?.let { Resource.Success(it) }
        } catch (e: Exception) {
            Resource.Error(response.errorBody().toString())
        }
    }

    override suspend fun getDetailPokemon(pokemonName: String): Resource<DetailPokemonResponse>? {
        val response = remoteDataSource.getDetailPokemon(pokemonName)

        return try {
            if (response != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Error Data")
            }
        } catch (e: Exception) {
            Resource.Error(response?.errorBody().toString())
        }
    }
}