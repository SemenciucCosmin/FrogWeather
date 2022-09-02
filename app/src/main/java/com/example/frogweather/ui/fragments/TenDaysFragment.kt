package com.example.frogweather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frogweather.R
import com.example.frogweather.data.classes.TenDaysListItem
import com.example.frogweather.data.classes.TenDaysListItemState
import com.example.frogweather.databinding.FragmentTenDaysBinding
import com.example.frogweather.ui.adapters.TenDaysAdapter
import com.example.frogweather.ui.application.FrogWeatherApplication
import com.example.frogweather.ui.models.NetworkViewModel
import com.example.frogweather.ui.models.SettingsViewModel

class TenDaysFragment : Fragment(), TenDaysListItemState {
    private lateinit var binding: FragmentTenDaysBinding
    private val networkViewModel = NetworkViewModel()
    private val settingsViewModel: SettingsViewModel by activityViewModels {
        SettingsViewModel.SettingsViewModelFactory(
            (requireActivity().application as FrogWeatherApplication)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTenDaysBinding.inflate(inflater, container, false)

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)?.let { itemDecorator.setDrawable(it) }

        val tenDaysAdapter = TenDaysAdapter(this)
        binding.tenDaysRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tenDaysAdapter
            setHasFixedSize(true)
            addItemDecoration(itemDecorator)
        }

        settingsViewModel.getSettingsAndLocation().observe(viewLifecycleOwner) { pair ->
            networkViewModel.requestUiState(pair.second.latitude, pair.second.longitude, pair.first)
        }

        networkViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            tenDaysAdapter.submitList(uiState.tenDaysItems)
        }

        return binding.root
    }

    override fun onItemClicked(tenDaysListItem: TenDaysListItem) {
        networkViewModel.changeTenDaysListItemState(tenDaysListItem)
    }
}
