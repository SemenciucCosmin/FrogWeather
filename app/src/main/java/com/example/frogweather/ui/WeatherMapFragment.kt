package com.example.frogweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.frogweather.databinding.FragmentWeatherMapBinding

class WeatherMapFragment : Fragment() {
    private lateinit var binding: FragmentWeatherMapBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherMapBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener{findNavController().navigateUp()}

        return binding.root
    }

}