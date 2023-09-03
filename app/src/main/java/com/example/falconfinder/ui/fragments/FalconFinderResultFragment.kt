package com.example.falconfinder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.falconfinder.R
import com.example.falconfinder.databinding.FragmentFalconFinderResultBinding
import com.example.falconfinder.ui.FindFalconClickListener
import com.example.falconfinder.ui.viewmodel.StarWarViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FalconFinderResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FalconFinderResultFragment @Inject constructor(private val findFalconClickListener: FindFalconClickListener) : Fragment() {

    lateinit var binding: FragmentFalconFinderResultBinding
    lateinit var viewModel: StarWarViewModel

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

        viewModel = ViewModelProvider(requireActivity())[StarWarViewModel::class.java]

        setOnClickListener()

    }

    private fun setOnClickListener() {
        binding.startAgainBtn.setOnClickListener {
            viewModel.clearModels()
            findFalconClickListener.onStartBtnClicked()
        }
    }

}