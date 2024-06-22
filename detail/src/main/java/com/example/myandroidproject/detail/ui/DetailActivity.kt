package com.example.myandroidproject.detail.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.domain.model.detailpokemonmodel.DetailPokemonModel
import com.example.myandroidproject.detail.adapter.AbilityAdapter
import com.example.myandroidproject.detail.adapter.TypeAdapter
import com.example.myandroidproject.detail.databinding.ActivityDetailBinding
import com.example.myandroidproject.detail.viewmodel.DetailViewModel
import com.example.myandroidproject.detail.viewmodel.IDetailViewModel
import com.example.myandroidproject.kit.gone
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
    private val detailViewModel: IDetailViewModel by viewModels<DetailViewModel>()
    private val abilityAdapter by lazy { AbilityAdapter() }
    private val typeAdapter by lazy { TypeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val movieIdExtras = intent.getStringExtra(CommonConstant.POKEMON_NAME).orEmpty()
        val pokemonId = intent.getIntExtra(CommonConstant.POKEMON_ID, 0)
        detailViewModel.getDetailPokemon(movieIdExtras)
        observeData(binding, 0)
    }

    override fun observeData(binding: ActivityDetailBinding?, movieId: Int) {
        detailViewModel.getDetailPokemonLiveData().observe(this, Observer {
            if (it != null) {
                setUpDetailView(binding, it)
            }
        })
    }

    private fun setUpDetailView(binding: ActivityDetailBinding?, it: DetailPokemonModel) {
        binding?.pbDetailMovie?.gone()
        if (binding != null) {
            Glide.with(applicationContext)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png")
                .placeholder(com.example.myandroidproject.core.R.drawable.ic_movies_24)
                .into(binding.ivDetailActivity)
        }
        binding?.tvTitleActivity?.text = it.name
        binding?.tvHeight?.text = "Height : ${it.height}"
        binding?.tvWeight?.text = "Weight : ${it.weight}"
        Toast.makeText(this, "Success get detail data", Toast.LENGTH_SHORT)
            .show()

        showAbilityView(it)
    }

    private fun showAbilityView(it: DetailPokemonModel) {
        with(binding.rvAbility) {
            this.layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            this.setHasFixedSize(true)
            this.adapter = abilityAdapter
        }

        with(binding.rvType) {
            this.layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            this.setHasFixedSize(true)
            this.adapter = typeAdapter
        }

        abilityAdapter.setDataItems(it.abilities)
        typeAdapter.setDataItems(it.typeResponses)
    }
}