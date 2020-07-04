package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        viewModel.saveGuest.observe(this, Observer { isPresent ->
            radio_present.isChecked = isPresent
        })
    }

    private fun setListener() {

        button_save.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_save) {
            val nameCaptured = edit_name.text.toString()
            val context = MainActivity@ this

            if (radio_present.isChecked) {
                viewModel.save(nameCaptured, isPresent = true, context = context)
            } else if (radio_absent.isChecked) {
                viewModel.save(nameCaptured, isPresent = false, context = context)
            }

        }
    }
}