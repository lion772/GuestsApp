package com.example.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.convidados.R
import com.example.convidados.viewmodel.AbsentViewModel
import kotlinx.android.synthetic.main.fragment_absent.*

class AbsentFragment : Fragment() {

    private lateinit var absentViewModel: AbsentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        absentViewModel =
                ViewModelProviders.of(this).get(AbsentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_absent, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        absentViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        absentViewModel.titulo.observe(viewLifecycleOwner, Observer {TituloViewModel ->
            titulo_ausentes.text = TituloViewModel
            titulo_ausentes?.run {
                titulo_ausentes.setTextColor(resources.getColor(R.color.white))
                titulo_ausentes.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            }
        })
        return root
    }
}