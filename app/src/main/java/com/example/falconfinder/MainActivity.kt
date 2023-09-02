package com.example.falconfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.falconfinder.databinding.ActivityMainBinding
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.fragments.FalconFinderResultFragment
import com.example.falconfinder.ui.fragments.PlanetSelectionFragment

class MainActivity : AppCompatActivity(), FindFalconClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        addPlanetSelectionFragment()

    }

    private fun addPlanetSelectionFragment() {
        val ft = supportFragmentManager.beginTransaction()

        val fragment = PlanetSelectionFragment(this)

        ft.replace(R.id.activity_fl, fragment)

        ft.commit()
    }

    private fun addFindFalconFinderFragment(){
        val ft = supportFragmentManager.beginTransaction()

        val fragment = FalconFinderResultFragment(this)

        ft.replace(R.id.activity_fl, fragment)

        ft.commit()
    }
    override fun onFindFalconBtnClicked() {
        addFindFalconFinderFragment()
    }

    override fun onStartBtnClicked() {
        addPlanetSelectionFragment()
    }
}