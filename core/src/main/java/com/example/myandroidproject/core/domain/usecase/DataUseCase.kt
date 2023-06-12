package com.example.myandroidproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.detailmoviemodel.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel
import com.example.myandroidproject.core.domain.model.movietrailermodel.MovieTrailerItemModel

interface DataUseCase {

    fun getMovieList(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>>

    fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>>

    fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>>

    fun getMovieTrailer(movieId: Int): LiveData<Resource<List<MovieTrailerItemModel>>>

    fun getPage(): Int

    fun getTotalPage(): Int
}