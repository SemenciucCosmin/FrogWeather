package com.example.frogweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.frogweather.databinding.FragmentTenDaysBinding

class TenDaysFragment : Fragment() {
    private lateinit var binding: FragmentTenDaysBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTenDaysBinding.inflate(inflater, container, false)

        return binding.root
    }
}