package com.example.falconfinder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.falconfinder.R
import com.example.falconfinder.databinding.FragmentFalconFinderResultBinding
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.viewmodel.StarWarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FalconFinderResultFragment @Inject constructor(private val findFalconClickListener: FindFalconClickListener) : Fragment() {

    lateinit var binding: FragmentFalconFinderResultBinding

    private val viewModel by activityViewModels<StarWarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFalconFinderResultBinding.inflate(inflater, container, false)

        initViews()
        return binding.root
    }

    private fun initViews() {
         setViews()
        setOnClickListener()

    }

    private fun setViews() {
        var planetName: String = ""
         viewModel.falconResult?.planetName?.apply {
           planetName = getString(R.string.found_on).replace("{planet}", this)
        }
        binding.planetTv.text = planetName

        binding.timeTakenTv.text = getString(R.string.time_taken_time)
            .replace("{time}",  viewModel.timeTaken.toString())
    }

    private fun setOnClickListener() {
        binding.startAgainBtn.setOnClickListener {
            viewModel.clearModels()
            findFalconClickListener.onStartBtnClicked()
        }
    }

}