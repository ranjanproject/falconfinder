package com.example.falconfinder.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.falconfinder.models.PlanetResponse
import com.example.falconfinder.models.PlanetResponseItem
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

    fun getVehicles(){

        viewModelScope.launch {

        }
    }
}