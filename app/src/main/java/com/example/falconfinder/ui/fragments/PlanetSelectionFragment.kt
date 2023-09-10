package com.example.falconfinder.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.falconfinder.R
import com.example.falconfinder.databinding.FragmentPlanetSelectionBinding
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.ui.DialogEventListeners
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.ItemClickListener
import com.example.falconfinder.ui.PlanetVehicleAdapter
import com.example.falconfinder.ui.PlanetVehicleItemDecorator
import com.example.falconfinder.ui.viewmodel.StarWarViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Objects
import javax.inject.Inject

@AndroidEntryPoint
class PlanetSelectionFragment @Inject constructor(private val findFalconClickListener: FindFalconClickListener) :
    Fragment(), ItemClickListener, DialogEventListeners {

    private val viewModel by activityViewModels<StarWarViewModel>()

    @Inject lateinit var adapter: PlanetVehicleAdapter
    @Inject lateinit var vehicleBottomSheetFragment: VehicleBottomSheetFragment
    private lateinit var binding: FragmentPlanetSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPlanetSelectionBinding.inflate(inflater, container, false)

        initViews()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

   private fun initViews(){
       initRv()
       initViewModel()

       initListeners()

    }

    private fun initListeners() {

        binding.findFalconBtn.setOnClickListener {


            viewModel.findFalcon()


        }
    }

    private fun initViewModel() {

        initObservers()

        viewModel.getPlanets()

        viewModel.getVehicles()

        viewModel.getToken()
    }

    private fun initRv() {
//        adapter = PlanetVehicleAdapter(this)

        binding.planetsRv.addItemDecoration(PlanetVehicleItemDecorator())

        binding.planetsRv.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.planetsRv.adapter = adapter
    }


    private fun initObservers(){
        viewModel.planetsLD.observe(viewLifecycleOwner, Observer {
            Log.d("PlanetSelectionFragment", it.toString())
            adapter.submitList(it as List<Any>?)
        })

        viewModel.isFindFalconBtn.observe(viewLifecycleOwner) {
            binding.findFalconBtn.isClickable = it
        }

        viewModel.falconResultLD.observe(viewLifecycleOwner) {
            findFalconClickListener.onFindFalconBtnClicked()
        }
    }

    override fun onPlanetClicked(planetResponseItem: PlanetResponseItem, isSelected: Boolean) {

        viewModel.selectPlanet(planetResponseItem, isSelected)

        if(isSelected && !vehicleBottomSheetFragment.isAdded) {
            val bundle = Bundle()
            bundle.putString(VehicleBottomSheetFragment.PLANET_NAME, planetResponseItem.name)
            bundle.putString(
                VehicleBottomSheetFragment.PLANET_DISTANCE,
                planetResponseItem.distance.toString()
            )
            vehicleBottomSheetFragment.arguments = bundle
            vehicleBottomSheetFragment.show(childFragmentManager, "BottomSheet")
        }else{
            vehicleBottomSheetFragment.dismiss()
        }

    }

    override fun onVehicleClickListener(vehicleResponseItem: VehicleResponseItem, isSelected: Boolean) {

    }

    override fun onCancel(planetName: String) {
        viewModel.checkVehicleSelectedForPlanet(planetName)
    }

    override fun onDismiss(planetName: String) {
        val y = 0
    }
}