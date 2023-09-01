package com.example.falconfinder.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.PlanetResponseItem
import com.example.falconfinder.models.VehicleResponseItem
import com.example.falconfinder.repository.StarWarRepository
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * This viewmodel will be shared between four different fragments
 */
class StarWarViewModel: ViewModel(){

    private val repository = StarWarRepository()

    private var _planets: PlanetResponse? = null

    private val _planetsMLD = MutableLiveData<ArrayList<PlanetResponseItem>>()

    val planetsLD = _planetsMLD

    private val rocketMap = mutableMapOf<String, VehicleResponseItem?>()

    private var _vehicleResponse = mutableListOf<VehicleResponseItem>()

    private var selectedPlanetCount = 0
    fun getPlanets(){
        viewModelScope.launch {
            try{
                _planets = repository.getPlanets()

                _planetsMLD.value = _planets
            }catch (e: Exception){
                Log.e("viewModel", e.toString())
            }
        }
    }


   fun getAvailablePlanets(){

       _planetsMLD.value = _planets
   }

   fun selectPlanet(planetResponseItem: PlanetResponseItem, isSelected: Boolean){

       if(isSelected){
           selectedPlanetCount++
       }else{
           selectedPlanetCount--

           val vehicle = rocketMap[planetResponseItem.name]

           vehicle?.total_no = vehicle?.total_no!! + 1
           vehicle.isActive = true

           rocketMap[planetResponseItem.name!!] = null
       }

       planetResponseItem.isSelected = isSelected

       if(selectedPlanetCount < 4){
           _planets?.let {planetResponse ->
               planetResponse.forEach{
                   if(!it.isSelected){
                       it.isActive = true
                   }
               }
           }
       }else{
           _planets?.let {planetResponse ->
               planetResponse.forEach{
                   if(!it.isSelected){
                       it.isActive = false
                   }
               }
           }
       }

        _planets?.let {
            _planetsMLD.value = (it.toMutableList()) as ArrayList<PlanetResponseItem>
       }
   }

    //<editor-fold desc="Vehicle">
    fun getVehicles(){
        viewModelScope.launch {
            try{
                _vehicleResponse = repository.getVehicle().toMutableList()
            }catch (e: Exception){
                Log.e("viewModel", e.toString())
            }
        }
    }




    fun getVehicleList(): List<VehicleResponseItem>{
        return _vehicleResponse
    }



    fun getAvailableVehicles(planetName: String): List<VehicleResponseItem>{
        for(item in rocketMap){
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
    ){
        if(isSelected){
            vehicleResponseItem.total_no--
                for (item in _vehicleResponse) {
                    if (item != vehicleResponseItem && item.isSelected && item.isActive) {
                        item.isSelected = false
                        item.total_no++
                    }
                }
        }else{
            vehicleResponseItem.total_no++
        }
        vehicleResponseItem.isSelected = isSelected

        if(isSelected){
           rocketMap[planetName] = vehicleResponseItem
        }else{
           rocketMap[planetName]  = null
        }

    }
    //</editor-fold>

}