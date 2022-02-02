package com.example.cryptocoins.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocoins.adapter.HomeAdapter
import com.example.cryptocoins.data.Repository
import com.example.cryptocoins.data.modals.CoinLive
import com.example.cryptocoins.data.modals.CoinsResponseItem
import com.example.cryptocoins.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    private var job:Job? = null

    private val _postCoins: MutableStateFlow<Resource> =
        MutableStateFlow(Resource.Empty)
    val list:ArrayList<CoinLive> = ArrayList()
    val map:HashMap<String,Int> = HashMap()
    val adapter=HomeAdapter()
    var set=false;
    var temp:MutableLiveData<CoinsResponseItem> = MutableLiveData()
    fun getCoins(context: LifecycleOwner){
        adapter.context=context
        job = viewModelScope.launch {
            _postCoins.value = Resource.Loading
            repository.getCoins()
                .catch {
                    _postCoins.value = Resource.Error(it)
                }
                .collect {

                    for (index in it.indices){
                        val cur=it[index];
                        if (map.containsKey(cur.symbol)){
                            val j= map[cur.symbol]
                            if (j != null) {
                                list[j].cdata?.value?.lastPrice = cur.lastPrice
                            }
                            temp.value?.lastPrice=cur.lastPrice
                        }else {
                            map[cur.symbol] = list.size
                            list.add(CoinLive(MutableLiveData(it[index])))
                        }
                        temp.value=cur
                    }
//                    _postCoins.value = Resource.Success(it)
                    if (!set){
                        adapter.setList(list,this@HomeViewModel)
                        set=true
                    }
                    Log.d("aditya", "getCoins:${list} ")

                }
        }
    }

}