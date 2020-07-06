package com.example.convidados.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.convidados.service.model.GuestModel
import com.example.convidados.service.repository.GuestRepository

/* O ViewModel não tem contexto do Android, então usamos o AndroidViewModel. Só que este não aceita contexto, então
*  usamos application, passada pela GuestFormViewModel, adquirida da GuestFormActivity */

class GuestFormViewModel(application: Application) :
    AndroidViewModel(application) { //GuestFormViewModel(context: Context) : AndroidViewModel(context as Application)

    private val mContext = application.applicationContext
    private val mGuestRepository = GuestRepository.getInstance(mContext)

    private val _saveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = _saveGuest

    fun save(name: String, isPresent: Boolean) {

        val guest = GuestModel(name = name, presence = isPresent)

        _saveGuest.value = mGuestRepository.save(guest)

    }
}