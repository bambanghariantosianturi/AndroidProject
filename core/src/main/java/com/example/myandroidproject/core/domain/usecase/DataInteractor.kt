package com.example.myandroidproject.core.domain.usecase

import android.icu.text.RelativeDateTimeFormatter.RelativeDateTimeUnit
import androidx.lifecycle.LiveData
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.core.domain.model.detailmoviemodel.DetailMovie
import com.example.myandroidproject.core.domain.model.genremoviemodel.GenreItemModel
import com.example.myandroidproject.core.domain.model.listmoviesmodel.MovieItemModel
import com.example.myandroidproject.core.domain.model.movietrailermodel.MovieTrailerItemModel
import com.example.myandroidproject.core.domain.repository.IDataRepository
import javax.inject.Inject

class DataInteractor @Inject constructor(private val dataRepository: IDataRepository) :
    DataUseCase {

    override fun getMovieList(page: Int, genreId: Int): LiveData<Resource<List<MovieItemModel>>> {
        return dataRepository.getMovieListData(page = page, genreId = genreId)
    }

    override fun getDetailMovieData(movieId: Int): LiveData<Resource<DetailMovie>> {
        return dataRepository.getDetailMovieData(movieId)
    }

    override fun getGenreMovie(): LiveData<Resource<List<GenreItemModel>>> {
        return dataRepository.getGenreMovieData()
    }

    override fun getMovieTrailer(movieId: Int): LiveData<Resource<List<MovieTrailerItemModel>>> {
        return dataRepository.getMovieTrailer(movieId)
    }

    override fun getPage(): Int = dataRepository.getPage()

    override fun getTotalPage(): Int = dataRepository.getTotalPage()
}