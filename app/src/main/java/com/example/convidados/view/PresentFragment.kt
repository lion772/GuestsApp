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
import com.example.convidados.R.color.colorPrimary
import com.example.convidados.viewmodel.PresentViewModel
import kotlinx.android.synthetic.main.fragment_present.*

class PresentFragment : Fragment() {

    private lateinit var presentViewModel: PresentViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        presentViewModel =
                ViewModelProviders.of(this).get(PresentViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_present, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        presentViewModel.text.observe(viewLifecycleOwner, Observer {TextoViewModel ->
            textView.text = TextoViewModel
        })
        presentViewModel.titulo.observe(viewLifecycleOwner, Observer { TituloViewModel ->
            titulo_presentes.text = TituloViewModel
            titulo_presentes?.run {
                titulo_presentes.setTextColor(resources.getColor(R.color.white))
                titulo_presentes.setBackgroundColor(resources.getColor(colorPrimary))
            }
        })
        return root
    }
}