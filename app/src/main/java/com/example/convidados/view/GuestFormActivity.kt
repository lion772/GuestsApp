package com.example.convidados.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private val viewModel: GuestFormViewModel by lazy {
        ViewModelProvider(this).get(GuestFormViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        supportActionBar?.hide()
        setListener()
        guestObserve()
    }

    private fun guestObserve() {
        viewModel.saveGuest.observe(this, Observer {
            if (it){
                Toast.makeText(applicationContext, "sucesso!", Toast.LENGTH_SHORT).show()
            } else{
                Toast.makeText(applicationContext, "falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
    }

    private fun setListener() {

        button_save.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_save) {

            val nameCaptured = edit_name.text.toString()
            val presence = radio_present.isChecked

            viewModel.save(nameCaptured, presence)
        }
    }
}