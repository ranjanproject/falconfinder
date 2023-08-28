package com.example.falconfinder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.falconfinder.R
import com.example.falconfinder.ui.viewmodel.StarWarViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetSelectionFragment : Fragment() {

    lateinit var viewModel: StarWarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planet_selection, container, false)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

   private fun initViews(){
       viewModel = ViewModelProvider(requireActivity())[StarWarViewModel::class.java]

       initObservers()
       viewModel.getPlanets()
    }


   private fun initObservers(){
        viewModel.planetsLD.observe(viewLifecycleOwner, Observer {
            Log.d("PlanetSelectionFragment", it.toString())
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            PlanetSelectionFragment().apply {

            }

    }
}