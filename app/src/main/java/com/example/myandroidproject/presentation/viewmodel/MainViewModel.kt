package com.example.myandroidproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myandroidproject.core.data.Result
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ListPokemonModel
import com.example.myandroidproject.core.domain.usecase.DataUseCase
import com.example.myandroidproject.kit.data.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel(),
    IMainViewModel {

        private val getListLiveData: MutableLiveData<ListPokemonModel> = MutableLiveData()
    override fun getListLiveData(): LiveData<ListPokemonModel> = getListLiveData

    private val errorListLiveData: SingleLiveEvent<String> = SingleLiveEvent()
    override fun errorListLiveData(): LiveData<String> = errorListLiveData

     override fun getListData(): Job =
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                dataUseCase.getGenreMovie()
            }

            when(result) {
                is Result.Success -> {
                    getListLiveData.value = result.data
                }
                is Result.Error -> {
                    errorListLiveData.value = result.error
                }

                else -> {
                    errorListLiveData.value = "Something Wrong"
                }
            }
        }
}