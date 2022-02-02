package com.example.cryptocoins.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocoins.R
import com.example.cryptocoins.adapter.HomeAdapter
import com.example.cryptocoins.data.modals.CoinsResponse
import com.example.cryptocoins.databinding.ActivityMainBinding
import com.example.cryptocoins.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel:HomeViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        binding.executePendingBindings()
        viewModel.getCoins(this)


    }
}