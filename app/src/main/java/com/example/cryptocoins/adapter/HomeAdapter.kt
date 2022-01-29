package com.example.cryptocoins.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocoins.data.modals.CoinsResponse
import com.example.cryptocoins.data.modals.CoinsResponseItem
import com.example.cryptocoins.databinding.ItemHomeBinding

class HomeAdapter(): RecyclerView.Adapter<HomeAdapter.CoinsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return (CoinsViewHolder(binding))
    }

    override fun onBindViewHolder(holder: CoinsViewHolder, position: Int) {
        val currentItem = list[position]
        holder.bind(currentItem)
    }
    private var list : CoinsResponse = CoinsResponse()

    fun setList(newList:CoinsResponse){
        list=newList
        notifyDataSetChanged()
    }



    class CoinsViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(coins: CoinsResponseItem) {
            binding.data=coins
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

