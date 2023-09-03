package com.example.falconfinder.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.falconfinder.models.FalconFinderRequestBody
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.network.Result
import com.example.falconfinder.repository.StarWarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

/**
 * This viewmodel will be shared between four different fragments
 */

@HiltViewModel
class StarWarViewModel @Inject constructor(private val repository: StarWarRepository) : ViewModel() {

//    private val repository = StarWarRepository()

    private var _planets: PlanetResponse? = null

    private val _planetsMLD = MutableLiveData<ArrayList<PlanetResponseItem>>()

    val planetsLD = _planetsMLD

    private var rocketMap = mutableMapOf<String, VehicleResponseItem?>()

    private var _vehicleResponse = mutableListOf<VehicleResponseItem>()

    private var selectedPlanetCount = 0

    private var token: String = ""

    val isFindFalconBtn = MutableLiveData<Boolean>()

    fun getToken() {
        viewModelScope.launch {
            try {
                token = repository.getToken()
            } catch (e: Exception) {
                Log.e("viewModel", e.toString())

            }
        }
    }

    fun clearModels() {
        selectedPlanetCount = 0
        rocketMap = mutableMapOf()
        token = ""
        _vehicleResponse = mutableListOf()
    }

    //<editor-fold desc = "Planet">
    fun getPlanets() {
        viewModelScope.launch {
            try {
                _planets = repository.getPlanets()

                _planetsMLD.value = _planets
            } catch (e: Exception) {
                Log.e("viewModel", e.toString())
            }
        }
    }

    fun findFalcon() {
        viewModelScope.launch {
            try {
                val x = repository.findFalcon(getFalconRequestBody())
                val y = 0
            } catch (e: Exception) {
                Log.e("viewModel", e.toString())

            }
        }
    }

    private fun getFalconRequestBody(): FalconFinderRequestBody {
        val planetList = mutableListOf<String>()
        val vehicleList = mutableListOf<String>()
        rocketMap.forEach { entry ->
            planetList.add(entry.key)
            entry.value?.let {
                vehicleList.add(it.name!!)
            }
        }
        return FalconFinderRequestBody(token, planetList, vehicleList)
    }


    fun selectPlanet(planetResponseItem: PlanetResponseItem, isSelected: Boolean) {

        updateSelectedPlanetCount(planetResponseItem, isSelected)

        planetResponseItem.isSelected = isSelected

        enableFindFalconBtn()

        _planets?.let {
            _planetsMLD.value = (it.toMutableList()) as ArrayList<PlanetResponseItem>
        }
    }

    private fun updateSelectedPlanetCount(
        planetResponseItem: PlanetResponseItem,
        isSelected: Boolean
    ) {
        if (isSelected) {
            selectedPlanetCount++
        } else {
            selectedPlanetCount--

            val vehicle = rocketMap[planetResponseItem.name]

            vehicle?.total_no = vehicle?.total_no!! + 1
            vehicle.isActive = true

            rocketMap[planetResponseItem.name!!] = null
        }
    }

    private fun enableFindFalconBtn() {
        if (selectedPlanetCount < 4) {
            _planets?.let { planetResponse ->
                planetResponse.forEach {
                    if (!it.isSelected) {
                        it.isActive = true
                    }
                }
            }
            isFindFalconBtn.value = false
        } else {
            _planets?.let { planetResponse ->
                planetResponse.forEach {
                    if (!it.isSelected) {
                        it.isActive = false
                    }
                }
            }
            isFindFalconBtn.value = true
        }
    }
    //</editor-fold>


    //<editor-fold desc="Vehicle">
    fun getVehicles() {
        viewModelScope.launch {
            try {
                _vehicleResponse = repository.getVehicle().toMutableList()
            } catch (e: Exception) {
                Log.e("viewModel", e.toString())
            }
        }
    }


    fun getVehicleList(): List<VehicleResponseItem> {
        return _vehicleResponse
    }


    fun getAvailableVehicles(planetName: String): List<VehicleResponseItem> {
        for (item in rocketMap) {
            item.value?.let {
                it.isActive = it.total_no > 0
                it.isSelected = false
            }
        }
        return _vehicleResponse
    }


    fun selectRocket(
        planetName: String,
        planetDistance: String,
        vehicleResponseItem: VehicleResponseItem,
        isSelected: Boolean
    ) {

        if (isSelected) {
            vehicleResponseItem.total_no--
            for (item in _vehicleResponse) {
                if (item != vehicleResponseItem && item.isSelected && item.isActive) {
                    item.isSelected = false
                    item.total_no++
                }
            }
        } else {
            vehicleResponseItem.total_no++
        }
        vehicleResponseItem.isSelected = isSelected

        updateRocketMap(planetName, isSelected, vehicleResponseItem)
    }

    private fun updateRocketMap(
        planetName: String,
        isSelected: Boolean,
        vehicleResponseItem: VehicleResponseItem
    ) {
        if (isSelected) {
            rocketMap[planetName] = vehicleResponseItem
        } else {
            rocketMap[planetName] = null
        }
    }
    //</editor-fold>

}