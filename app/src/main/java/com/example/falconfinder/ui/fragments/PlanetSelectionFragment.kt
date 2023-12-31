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
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.ItemClickListener
import com.example.falconfinder.ui.PlanetVehicleAdapter
import com.example.falconfinder.ui.PlanetVehicleItemDecorator
import com.example.falconfinder.ui.viewmodel.StarWarViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetSelectionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetSelectionFragment(private val findFalconClickListener: FindFalconClickListener) : Fragment(), ItemClickListener {

    private lateinit var viewModel: StarWarViewModel
    private lateinit var adapter: PlanetVehicleAdapter
    private lateinit var binding: FragmentPlanetSelectionBinding
    private lateinit var vehicleBottomSheetFragment: VehicleBottomSheetFragment
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

       vehicleBottomSheetFragment = VehicleBottomSheetFragment.newInstance("", "")

       binding.findFalconBtn.setOnClickListener {

           findFalconClickListener.onFindFalconBtnClicked()

           viewModel.findFalcon()


       }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(requireActivity())[StarWarViewModel::class.java]

        initObservers()

        viewModel.getPlanets()

        viewModel.getVehicles()

        viewModel.getToken()
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
        viewModel.isFindFalconBtn.observe(viewLifecycleOwner) {
            findFalconClickListener.onFindFalconBtnClicked()
            binding.findFalconBtn.isClickable = it
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(findFalconClickListener: FindFalconClickListener) =
            PlanetSelectionFragment(findFalconClickListener).apply {

            }

    }

    override fun onPlanetClicked(planetResponseItem: PlanetResponseItem, isSelected: Boolean) {
        viewModel.selectPlanet(planetResponseItem, isSelected)
        if(isSelected) {
            val bundle = Bundle()
            bundle.putString(VehicleBottomSheetFragment.PLANET_NAME, planetResponseItem.name)
            bundle.putString(
                VehicleBottomSheetFragment.PLANET_DISTANCE,
                planetResponseItem.distance.toString()
            )
            vehicleBottomSheetFragment.arguments = bundle
            vehicleBottomSheetFragment.show(childFragmentManager, "BottomSheet")
        }

    }

    override fun onVehicleClickListener(vehicleResponseItem: VehicleResponseItem, isSelected: Boolean) {

    }
}