package com.example.falconfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import com.example.falconfinder.databinding.ActivityMainBinding
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.fragments.FalconFinderResultFragment
import com.example.falconfinder.ui.fragments.PlanetSelectionFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), FindFalconClickListener {

    private lateinit var binding: ActivityMainBinding

//    @Inject
//    lateinit var planetSelectionFragment: PlanetSelectionFragment
//
//    @Inject
//    lateinit var falconFinderResultFragment: FalconFinderResultFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        initViews()
    }

    private fun initViews() {

        addPlanetSelectionFragment()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onGameRuleSelected()
        return super.onOptionsItemSelected(item)
    }


    private fun onGameRuleSelected(){
        Toast.makeText(this, "Rule selected", Toast.LENGTH_LONG).show()
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