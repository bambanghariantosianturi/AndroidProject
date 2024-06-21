package com.example.myandroidproject.core.domain.usecase

import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ListPokemonModel
import com.example.myandroidproject.core.domain.repository.IDataRepository
import javax.inject.Inject

class DataInteractor @Inject constructor(private val dataRepository: IDataRepository) :
    DataUseCase {

    override suspend fun getDetailPokemon(pokemonName: String): Result<DetailPokemonModel> {
        return try {
            val result = dataRepository.getDetailPokemon(pokemonName)
            if (result?.data != null) {
                Result.Success(DetailPokemonModel(result.data))
            } else {
                Result.Error(result?.message.orEmpty())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }

    override suspend fun getGenreMovie(): Result<ListPokemonModel> {
        return try {
            val result = dataRepository.getListData()
            if (result?.data != null) {
                Result.Success(ListPokemonModel(result.data))
            } else {
                Result.Error(result?.message.orEmpty())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}