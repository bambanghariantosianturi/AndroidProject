package com.example.myandroidproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.domain.model.listnewsmodel.ListNewsModel
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ListPokemonModel
import kotlinx.coroutines.Job

interface IMainViewModel {

    fun getListData(): Job

    fun getListLiveData(): LiveData<List<ListNewsModel>>

    fun errorListLiveData(): LiveData<String>
}