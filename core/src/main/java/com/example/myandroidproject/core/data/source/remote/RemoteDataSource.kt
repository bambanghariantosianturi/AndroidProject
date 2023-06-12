package com.example.myandroidproject.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myandroidproject.core.data.source.remote.network.ApiResponse
import com.example.myandroidproject.core.data.source.remote.network.ApiService
import com.example.myandroidproject.core.data.source.remote.response.detailmovieresponse.DetailMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreItemResponse
import com.example.myandroidproject.core.data.source.remote.response.genremovieresponse.GenreMovieResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.ListMoviesResponse
import com.example.myandroidproject.core.data.source.remote.response.listmovieresponse.MovieItemResponse
import com.example.myandroidproject.core.data.source.remote.response.movietrailerresponse.ListMovieTrailerResponse
//import com.example.myandroidproject.core.utils.JsonHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    var tempPage: Int = 0
    var totalPage: Int = 0

    fun getListMovies(page: Int, genreId: Int): LiveData<ApiResponse<List<MovieItemResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<MovieItemResponse>>>()
        val client = apiService.getMovieList(page = page, genreId = genreId)
        client.enqueue(object : Callback<ListMoviesResponse?> {
            override fun onResponse(
                call: Call<ListMoviesResponse?>,
                response: Response<ListMoviesResponse?>
            ) {
                tempPage = response.body()?.page ?: 0
                totalPage = response.body()?.total_pages ?: 0
                val dataResponse = response.body()?.movieItemResponses
                resultData.value =
                    if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("remote success", response.body().toString())
            }

            override fun onFailure(call: Call<ListMoviesResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("remote error", t.message.toString())
            }
        })
        return resultData
    }

    fun getDetailMovie(movieId: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        val resultData = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        val client = apiService.getDetailMovie(movieId = movieId)
        client.enqueue(object : Callback<DetailMovieResponse?> {
            override fun onResponse(
                call: Call<DetailMovieResponse?>,
                response: Response<DetailMovieResponse?>
            ) {
                val dataResponse = response.body()
                resultData.value =
                    if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("detail response success", response.body().toString())
            }

            override fun onFailure(call: Call<DetailMovieResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("detail response error", t.message.toString())
            }
        })
        return resultData
    }

    fun getGenreMovie(): LiveData<ApiResponse<List<GenreItemResponse>>> {
        val result = MutableLiveData<ApiResponse<List<GenreItemResponse>>>()

        val client = apiService.getGenreMovies()
        client.enqueue(object : Callback<GenreMovieResponse?> {
            override fun onResponse(
                call: Call<GenreMovieResponse?>,
                response: Response<GenreMovieResponse?>
            ) {
                val dataResponse = response.body()?.genreItemResponses
                result.value =
                    if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("genre response success", response.body().toString())
            }

            override fun onFailure(call: Call<GenreMovieResponse?>, t: Throwable) {
                result.value = ApiResponse.Error(t.message.toString())
                Log.d("genre response error", t.message.toString())
            }
        })
        return result
    }

    fun getMovieTrailer(movieId: Int): LiveData<ApiResponse<List<ListMovieTrailerResponse.MovieTrailerItem>>> {
        val resultData = MutableLiveData<ApiResponse<List<ListMovieTrailerResponse.MovieTrailerItem>>>()

        val client = apiService.getMovieTrailer(movieId = movieId)
        client.enqueue(object : Callback<ListMovieTrailerResponse?> {
            override fun onResponse(
                call: Call<ListMovieTrailerResponse?>,
                response: Response<ListMovieTrailerResponse?>
            ) {
                val dataResponse = response.body()?.movieTrailerItem
                resultData.value =
                    if (dataResponse != null) ApiResponse.Success(dataResponse) else ApiResponse.Empty
                Log.d("detail response success", response.body().toString())
            }

            override fun onFailure(call: Call<ListMovieTrailerResponse?>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("detail response error", t.message.toString())
            }
        })
        return resultData
    }
}



