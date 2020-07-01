package com.example.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AbsentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui é o fragmento dos convidados ausentes"
    }
    val text: LiveData<String> = _text

    private val _titulo = MutableLiveData<String>().apply {
        value = "Liste de convidados que não vieram: "
    }
    val titulo:LiveData<String> = _titulo
}