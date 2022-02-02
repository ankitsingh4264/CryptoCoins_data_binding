package com.example.cryptocoins.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoins.data.modals.CoinLive
import com.example.cryptocoins.databinding.ItemHomeBinding
import com.example.cryptocoins.ui.home.HomeViewModel

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.CoinsViewHolder>(){
    lateinit var context:LifecycleOwner
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        context= parent.findViewTreeLifecycleOwner()!!

        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return (CoinsViewHolder(binding))
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }
    private var list : ArrayList<CoinLive> = ArrayList()
    private var hm:HomeViewModel?=null

    fun setList(newList: ArrayList<CoinLive>,vm:HomeViewModel){
        list=newList
        hm=vm
        notifyDataSetChanged()
    }



    inner class CoinsViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder
    (binding.root) {
        fun bind(coins: CoinLive) {
            binding.data=coins
            binding.emp=hm
            binding.lifecycleOwner=context

            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

