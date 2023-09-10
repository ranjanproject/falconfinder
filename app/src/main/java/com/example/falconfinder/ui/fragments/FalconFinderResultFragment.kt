package com.example.falconfinder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
      val status = viewModel.falconResult?.status
        if(status != null && status == "success") {
           setSuccessView()
        }else{
           setFailureView()
        }
    }

    private fun setSuccessView(){
        var planetName: String = ""
        viewModel.falconResult?.planetName?.apply {
            planetName = getString(R.string.found_on).replace("{planet}", this)
        }
        binding.planetTv.text = planetName

        binding.timeTakenTv.text = getString(R.string.time_taken_time)
            .replace("{time}", viewModel.timeTaken.toString())

        binding.timeTakenTv.visibility = View.VISIBLE
        binding.planetTv.visibility = View.VISIBLE
    }

    private fun setFailureView(){
        binding.titleTv.text  = getString(R.string.falcon_not_found)
        binding.timeTakenTv.visibility = View.GONE
        binding.planetTv.visibility = View.GONE
    }

    private fun setOnClickListener() {
        binding.startAgainBtn.setOnClickListener {
            viewModel.clearModels()
            findFalconClickListener.onStartBtnClicked()
        }
    }

}