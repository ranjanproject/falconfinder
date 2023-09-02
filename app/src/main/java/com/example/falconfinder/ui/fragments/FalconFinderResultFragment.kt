package com.example.falconfinder.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.falconfinder.R
import com.example.falconfinder.databinding.FragmentFalconFinderResultBinding
import com.example.falconfinder.ui.FindFalconClickListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FalconFinderResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FalconFinderResultFragment(private val findFalconClickListener: FindFalconClickListener) : Fragment() {

    lateinit var binding: FragmentFalconFinderResultBinding

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
        binding.startAgainBtn.setOnClickListener {
            findFalconClickListener.onStartBtnClicked()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(findFalconClickListener: FindFalconClickListener) =
            FalconFinderResultFragment(findFalconClickListener).apply {
                arguments = Bundle().apply {

                }
            }
    }
}