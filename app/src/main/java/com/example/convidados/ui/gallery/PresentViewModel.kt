package com.example.convidados.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresentViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aqui é o fragmento dos convidados presentes"
    }
    val text: LiveData<String> = _text

    private val _titulo = MutableLiveData<String>().apply {
        value = "Lista de convidados presentes:"
    }
    val titulo: LiveData<String> = _titulo
}