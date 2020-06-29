package com.example.convidados.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui é o fragmento de todos os convidados"
    }
    val text: LiveData<String> = _text

    private val _titulo = MutableLiveData<String>()
    val titulo:LiveData<String> = _titulo

    init {
        _titulo.value = "Nesse layout será exibido toda a lista de convidados para a minha festa!"
    }
}