package com.jmsc.kotlinflowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {

    private val _custom = MutableStateFlow(emptyList<Item>())
    val custom: StateFlow<List<Item>> = _custom

    fun addString(string: Item) {
        val tempList = _custom.value.toMutableList()
        tempList.add(string)
        _custom.value = tempList
    }

    fun updateString(position: Int,string: Item) {

        if(_custom.value.size > 2){
            val tempList = _custom.value.toMutableList()
            tempList[position] = string
            _custom.value = tempList
        }

    }

    fun deleteString(position: Int) {

        if(_custom.value.size >= position){
            val tempList = _custom.value.toMutableList()
            tempList.removeAt(position)
            _custom.value = tempList
        }
    }
}