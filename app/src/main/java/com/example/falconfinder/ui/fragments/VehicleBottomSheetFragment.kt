package com.example.falconfinder.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.falconfinder.databinding.BottomSheetVehicleFragmentBinding
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.ui.DialogEventListeners
import com.example.falconfinder.ui.ItemClickListener
import com.example.falconfinder.ui.PlanetVehicleAdapter
import com.example.falconfinder.ui.PlanetVehicleItemDecorator
import com.example.falconfinder.ui.viewmodel.StarWarViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class VehicleBottomSheetFragment @Inject constructor(private  val dialogInterface: DialogEventListeners) : BottomSheetDialogFragment(), ItemClickListener {

    private lateinit var binding: BottomSheetVehicleFragmentBinding
    private var planetName = ""
    private var planetDistance = ""

    private val viewModel by activityViewModels<StarWarViewModel>()

    @Inject lateinit var adapter: PlanetVehicleAdapter
    companion object {

        const val PLANET_NAME = "planetName"
        const val PLANET_DISTANCE = "planetDistance"

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

        binding.rocketRv.layoutManager = LinearLayoutManager(requireContext())

        binding.rocketRv.addItemDecoration(PlanetVehicleItemDecorator())

        binding.rocketRv.adapter = adapter

        submitList(viewModel.getAvailableVehicles(planetName, planetDistance))

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

    override fun dismiss() {
        super.dismiss()
        dialogInterface.onDismiss(planetName)
        clearDialogData()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialogInterface.onCancel(planetName)
        clearDialogData()
    }

    private fun clearDialogData(){
        planetName = ""
        planetDistance = ""
    }
}