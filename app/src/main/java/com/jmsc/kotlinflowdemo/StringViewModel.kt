package com.jmsc.kotlinflowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StringViewModel: ViewModel() {

    private val _custom = MutableStateFlow(emptyList<String>())
    val custom: StateFlow<List<String>> = _custom

    fun addString(string: String) {
        val tempList = _custom.value.toMutableList()
        tempList.add(string)
        _custom.value = tempList
    }

    fun updateString(string: String) {

        if(_custom.value.size > 2){
            val tempList = _custom.value.toMutableList()
            tempList[1] = string
            _custom.value = tempList
        }

    }

    fun deleteString(string: String) {

        if(_custom.value.size > 4){
            val tempList = _custom.value.toMutableList()
            tempList.removeAt(2)
            _custom.value = tempList
        }
    }
}