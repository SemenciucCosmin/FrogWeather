package com.example.frogweather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.frogweather.R
import com.example.frogweather.databinding.FragmentWeatherMapBinding

class WeatherMapFragment : Fragment() {
    private lateinit var binding: FragmentWeatherMapBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherMapBinding.inflate(inflater, container, false)

        binding.fragmentWeatherMapAppBar.toolbarTitle.text = getString(R.string.lbl_weather_map_fragment_title)
        binding.fragmentWeatherMapAppBar.backButton.setOnClickListener { findNavController().navigateUp() }

        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_7)

        return binding.root
    }
}
