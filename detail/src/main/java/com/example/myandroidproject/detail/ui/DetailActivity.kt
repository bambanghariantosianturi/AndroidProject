package com.example.myandroidproject.detail.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.data.Resource
import com.example.myandroidproject.detail.BuildConfig
import com.example.myandroidproject.detail.R
import com.example.myandroidproject.detail.databinding.ActivityDetailBinding
import com.example.myandroidproject.detail.viewmodel.DetailViewModel
import com.example.myandroidproject.kit.gone
import com.example.myandroidproject.kit.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(), IDetailActivity {

    companion object {
        fun startActivity(activity: Activity, bundle: Bundle) {
            activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
                putExtras(
                    bundle
                )
            })
        }
    }

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val movieIdExtras = intent.getIntExtra(CommonConstant.MOVIE_ID, 0) ?: 0
        observeData(binding, movieIdExtras)
    }

    override fun observeData(binding: ActivityDetailBinding?, movieId: Int) {
        detailViewModel.getDetailMovieData(movieId).observe(this, Observer {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {
                        binding?.pbDetailMovie?.visible()
                    }
                    is Resource.Success -> {
                        binding?.pbDetailMovie?.gone()
                        if (binding != null) {
                            Glide.with(binding.ivDetailMovie)
                                .load(BuildConfig.IMG_URL + it.data?.backdrop_path)
                                .placeholder(com.example.myandroidproject.core.R.drawable.ic_movies_24)
                                .into(binding.ivDetailMovie)
                        }
                        binding?.tvTitleMovie?.text = it.data?.title
                        binding?.tvDescMovie?.text = it.data?.overview
                        Toast.makeText(this, "Success get detail movie data", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is Resource.Error -> {
                        binding?.pbDetailMovie?.gone()
                        Toast.makeText(this, "Error get detail movie data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })

        detailViewModel.getMovieTrailer(movieId).observe(this, Observer {
            if (it.data != null) {
                when (it) {
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        //TODO
                        Toast.makeText(this, "Success get movie trailer", Toast.LENGTH_SHORT)
                            .show()
                    }
                    is Resource.Error -> {
                        Toast.makeText(this, "Error get detail movie data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }
}