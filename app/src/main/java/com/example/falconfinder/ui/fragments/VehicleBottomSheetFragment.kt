package com.example.falconfinder.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.falconfinder.databinding.BottomSheetVehicleFragmentBinding
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.ui.ItemClickListener
import com.example.falconfinder.ui.PlanetVehicleAdapter
import com.example.falconfinder.ui.PlanetVehicleItemDecorator
import com.example.falconfinder.ui.viewmodel.StarWarViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VehicleBottomSheetFragment : BottomSheetDialogFragment(), ItemClickListener {

    private lateinit var binding: BottomSheetVehicleFragmentBinding
    private var planetName = ""
    private var planetDistance = ""
    private lateinit var viewModel: StarWarViewModel
    private lateinit var adapter: PlanetVehicleAdapter
    companion object {

        const val PLANET_NAME = "planetName"
        const val PLANET_DISTANCE = "planetDistance"

        @JvmStatic
        fun newInstance(planetName: String, distance: String) =
            VehicleBottomSheetFragment().apply {
                val bundle = Bundle()
                bundle.putString(PLANET_NAME, planetName)
                bundle.putString(PLANET_DISTANCE, distance)
                arguments = bundle
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = BottomSheetVehicleFragmentBinding.inflate(inflater, container, false)
        getData()
        initViews()
        return binding.root
    }

    private fun getData() {
        arguments?.let{
            planetName = it.getString(PLANET_NAME, "")
            planetDistance = it.getString(PLANET_DISTANCE, "")
        }
    }

    private fun initViews() {
        setNames()

        viewModel = ViewModelProvider(requireActivity())[StarWarViewModel::class.java]

        adapter = PlanetVehicleAdapter(this)

        binding.rocketRv.layoutManager = LinearLayoutManager(requireContext())

        binding.rocketRv.addItemDecoration(PlanetVehicleItemDecorator())

        binding.rocketRv.adapter = adapter

        submitList(viewModel.getAvailableVehicles(planetName))

    }

   private fun submitList(list: List<VehicleResponseItem>){
        adapter.submitList(list.toMutableList<Any>())
    }

    private fun setNames() {
        binding.planetNameTv.text = planetName
        binding.planetDistanceTv.text = planetDistance
    }

    override fun onPlanetClicked(planetResponseItem: PlanetResponseItem, isSelected: Boolean) {

    }

    override fun onVehicleClickListener(
        vehicleResponseItem: VehicleResponseItem,
        isSelected: Boolean
    ) {
        viewModel.selectRocket(planetName, planetDistance, vehicleResponseItem, isSelected)
        submitList(viewModel.getVehicleList())
    }
}