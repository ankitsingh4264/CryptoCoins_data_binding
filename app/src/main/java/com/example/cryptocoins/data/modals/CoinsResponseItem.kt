package com.example.cryptocoins.data.modals

import androidx.lifecycle.MutableLiveData

data class CoinsResponseItem(
    val askPrice: String,
    val at: Long,
    val baseAsset: String,
    val bidPrice: String,
    val highPrice: String,
    var lastPrice: String,
    val lowPrice: String,
    val openPrice: String,
    val quoteAsset: String,
    val symbol: String,
    val volume: String
)
data class CoinLive(
    val cdata:MutableLiveData<CoinsResponseItem>?=null
)
