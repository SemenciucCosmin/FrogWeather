package com.example.frogweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.frogweather.databinding.FragmentOneDayBinding

class OneDayFragment : Fragment() {
    private lateinit var binding: FragmentOneDayBinding
    private val networkViewModel = NetworkViewModel()
    private val settingsViewModel: SettingsViewModel by activityViewModels {
        SettingsViewModel.SettingsViewModelFactory(
            (requireActivity().application as FrogWeatherApplication)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneDayBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {
        fun newInstance(millis: Long) : Fragment {
            return OneDayFragment().apply {
                arguments = Bundle().apply {
                    putLong("key", millis)
                }
            }
        }
    }
}