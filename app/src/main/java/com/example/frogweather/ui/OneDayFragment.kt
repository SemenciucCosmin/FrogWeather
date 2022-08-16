package com.example.frogweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frogweather.databinding.FragmentOneDayBinding

class OneDayFragment : Fragment() {
    private lateinit var binding: FragmentOneDayBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOneDayBinding.inflate(inflater, container, false)
        return binding.root
    }
}