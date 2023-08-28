package com.example.falconfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.falconfinder.databinding.ActivityMainBinding
import com.example.falconfinder.ui.fragments.PlanetSelectionFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        val ft = supportFragmentManager.beginTransaction()

        val fragment = PlanetSelectionFragment()

        ft.add(R.id.activity_fl, fragment)

        ft.commit()
    }
}