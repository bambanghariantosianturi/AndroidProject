package com.example.myandroidproject.detail.viewmodel

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.detailmoviemodel.DetailMovie
import com.example.myandroidproject.core.domain.model.movietrailermodel.MovieTrailerItemModel

interface IDetailViewModel {

    fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>>

    fun getMovieTrailer(movieId: Int): LiveData<Resource<List<MovieTrailerItemModel>>>
}