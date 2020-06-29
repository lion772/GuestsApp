package com.example.convidados.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.convidados.R
import com.example.convidados.R.color.*
import kotlinx.android.synthetic.main.fragment_home.*

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
                ViewModelProviders.of(this).get(AllGuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        /*fab.setOnClickListener {

        }*/
        allGuestsViewModel.text.observe(viewLifecycleOwner, Observer { Convidados ->
            textView.text = Convidados
        })

        allGuestsViewModel.titulo.observe(viewLifecycleOwner, Observer {
            titulo_todos_convidados.text = it
            titulo_todos_convidados?.run {
                titulo_todos_convidados.setTextColor(resources.getColor(white))
                titulo_todos_convidados.setBackgroundColor(resources.getColor(colorPrimary))

            }
        })

        return root


    }
}