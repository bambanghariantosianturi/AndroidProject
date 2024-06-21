package com.example.myandroidproject.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import com.example.myandroidproject.kit.data.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel(),
    IDetailViewModel {

    private val detailPokemonLiveData: MutableLiveData<DetailPokemonModel> = MutableLiveData()
    override fun getDetailPokemonLiveData(): LiveData<DetailPokemonModel> = detailPokemonLiveData

    private val errorDetailPokemonLiveData: SingleLiveEvent<String> = SingleLiveEvent()
    override fun errorDetailPokemonLiveData(): LiveData<String> = errorDetailPokemonLiveData

    override fun getDetailPokemon(namePokemon: String): Job =
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                dataUseCase.getDetailPokemon(pokemonName = namePokemon)
            }

            when (result) {
                is Result.Success -> {
                    detailPokemonLiveData.value = result.data
                }

                is Result.Error -> {
                    errorDetailPokemonLiveData.value = result.error
                }

                else -> {
                    errorDetailPokemonLiveData.value = "Something Wrong"
                }
            }
        }
}