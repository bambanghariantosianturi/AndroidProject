package com.example.myandroidproject.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.domain.model.listpokemonmodel.ItemPokemonModel
import com.example.myandroidproject.databinding.ActivityMainBinding
import com.example.myandroidproject.detail.ui.DetailActivity
import com.example.myandroidproject.kit.gone
import com.example.myandroidproject.kit.visible
import com.example.myandroidproject.presentation.adapter.MainAdapter
import com.example.myandroidproject.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setUpView()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.getListData()
    }

    override fun setUpView() {
        with(binding.rvMain) {
            this.layoutManager = LinearLayoutManager(this@MainActivity, VERTICAL, false)
            this.setHasFixedSize(true)
            this.adapter = mainAdapter
        }

        mainAdapter.onItemClick = { selectData ->
            moveToListMovie(selectData)
        }
    }

    override fun observeData() {
        mainViewModel.getListLiveData().observe(this, Observer {
            binding.pbMainActivity.gone()
            binding.rvMain.visible()
            mainAdapter.setDataItems(it.results)
            Toast.makeText(this, "Success get genre data", Toast.LENGTH_SHORT).show()
        })
    }

    private fun moveToListMovie(selectData: ItemPokemonModel) {
        val bundle = Bundle()
        bundle.putString(CommonConstant.POKEMON_NAME, selectData.name)
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}