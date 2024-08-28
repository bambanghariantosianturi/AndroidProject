package com.example.myandroidproject.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.myandroidproject.R
import com.example.myandroidproject.core.commonconstant.CommonConstant
import com.example.myandroidproject.core.domain.model.listnewsmodel.ListNewsModel
import com.example.myandroidproject.databinding.ActivityMainBinding
import com.example.myandroidproject.detail.ui.DetailActivity
import com.example.myandroidproject.kit.gone
import com.example.myandroidproject.kit.visible
import com.example.myandroidproject.presentation.adapter.MainAdapter
import com.example.myandroidproject.presentation.viewmodel.IMainViewModel
import com.example.myandroidproject.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: IMainViewModel by viewModels<MainViewModel>()

    private val mainAdapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //supportActionBar?.hide()
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
            mainAdapter.setDataItems(it)
            Toast.makeText(this, "Success get list data", Toast.LENGTH_SHORT).show()
        })

        mainViewModel.errorListLiveData().observe(this, Observer {
            binding.pbMainActivity.gone()
            binding.rvMain.visible()
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.recent -> {
                mainAdapter.filter(item.title)
                Toast.makeText(this, "recent news", Toast.LENGTH_SHORT).show()
            }

            R.id.popular -> {
                mainAdapter.filter(item.title)
                Toast.makeText(this, "popular news", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun moveToListMovie(selectData: ListNewsModel) {
        val bundle = Bundle()
        bundle.putParcelable(CommonConstant.DETAIL_NEWS, selectData)
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}