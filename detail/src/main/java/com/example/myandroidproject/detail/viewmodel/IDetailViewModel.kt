package com.example.myandroidproject.detail.viewmodel

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import kotlinx.coroutines.Job

interface IDetailViewModel {
    fun getDetailPokemon(namePokemon: String): Job

    fun getDetailPokemonLiveData(): LiveData<DetailPokemonModel>

    fun errorDetailPokemonLiveData(): LiveData<String>
}