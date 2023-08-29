package com.example.falconfinder.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.falconfinder.R
import com.example.falconfinder.databinding.FragmentPlanetSelectionBinding
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.ui.ItemClickListener
import com.example.falconfinder.ui.PlanetVehicleAdapter
import com.example.falconfinder.ui.PlanetVehicleItemDecorator
import com.example.falconfinder.ui.viewmodel.StarWarViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetSelectionFragment : Fragment(), ItemClickListener {

    lateinit var viewModel: StarWarViewModel
    lateinit var adapter: PlanetVehicleAdapter
    lateinit var binding: FragmentPlanetSelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlanetSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

   private fun initViews(){
       initRv()
       initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[StarWarViewModel::class.java]

        initObservers()

        viewModel.getPlanets()
    }

    private fun initRv() {
        adapter = PlanetVehicleAdapter(this)

        binding.planetsRv.addItemDecoration(PlanetVehicleItemDecorator())

        binding.planetsRv.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.planetsRv.adapter = adapter
    }


    private fun initObservers(){
        viewModel.planetsLD.observe(viewLifecycleOwner, Observer {
            Log.d("PlanetSelectionFragment", it.toString())
            adapter.submitList(it as List<Any>?)
        })
    }


    companion object {

        @JvmStatic
        fun newInstance() =
            PlanetSelectionFragment().apply {

            }

    }

    override fun onPlanetClicked(planetResponseItem: PlanetResponseItem, isSelected: Boolean) {

    }

    override fun onVehicleClickListener(vehicleResponseItem: VehicleResponseItem) {

    }
}