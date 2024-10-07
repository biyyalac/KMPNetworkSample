package com.example.myapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6.Greeting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _greetingList = MutableStateFlow<List<String>>(listOf())
    private val _hasToShow = MutableStateFlow<Boolean>(false)
    val greetingList: StateFlow<List<String>> get() = _greetingList
    val hasToShow: StateFlow<Boolean> get() = _hasToShow

    init {
        /*viewModelScope.launch {
            Greeting().greet().collect { phrase ->
                _greetingList.update { list -> list + phrase }

            }
        }*/
    }
    fun callAPI()
    {
        _hasToShow.update {  true}
        viewModelScope.launch {
            Greeting().greet().collect { phrase ->

                if(phrase.contains("1"))
                    _hasToShow.update {  false}
                else  if(!phrase.contains("0"))
                _greetingList.update { list -> list + phrase }

            }
        }
    }
}