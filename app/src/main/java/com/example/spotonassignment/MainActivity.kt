package com.example.spotonassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spotonassignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "JAUHAR_MainActivity"
    }

    @InternalCoroutinesApi
    lateinit var viewModel: MainViewModel
    lateinit var cryptoCurrencyAdapter: CryptoCurrencyAdapter
    private lateinit var binding: ActivityMainBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        cryptoCurrencyAdapter = CryptoCurrencyAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cryptoCurrencyAdapter

        }

    }

    @InternalCoroutinesApi
    private fun observeViewModel(){
        viewModel.getData().observe(this, Observer {
            cryptoCurrencyAdapter.updateData(it)
        })
    }
}