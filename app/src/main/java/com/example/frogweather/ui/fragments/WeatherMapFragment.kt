package com.example.frogweather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frogweather.R
import com.example.frogweather.data.database.SavedWeather
import com.example.frogweather.databinding.FragmentWeatherMapBinding
import com.example.frogweather.ui.adapters.SavedWeathersAdapter
import com.example.frogweather.ui.application.FrogWeatherApplication
import com.example.frogweather.ui.models.DatabaseViewModel
import com.example.frogweather.ui.models.NetworkViewModel
import com.example.frogweather.ui.models.SettingsViewModel

class WeatherMapFragment : Fragment() {
    private lateinit var binding: FragmentWeatherMapBinding
    private var currentWeather: SavedWeather? = null
    private val networkViewModel = NetworkViewModel()
    private val databaseViewModel: DatabaseViewModel by activityViewModels {
        DatabaseViewModel.DatabaseViewModelFactory(
            (requireActivity().application as FrogWeatherApplication)
        )
    }
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
        binding = FragmentWeatherMapBinding.inflate(inflater, container, false)

        binding.fragmentWeatherMapAppBar.toolbarTitle.text = getString(R.string.lbl_weather_map_fragment_title)
        binding.fragmentWeatherMapAppBar.backButton.setOnClickListener { findNavController().navigateUp() }

        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_7)

        settingsViewModel.getSettingsAndLocation().observe(viewLifecycleOwner){ pair ->
            networkViewModel.requestUiState(pair.second.latitude, pair.second.longitude, pair.first)
        }

        networkViewModel.uiState.observe(viewLifecycleOwner){ uiState ->
            if (uiState.errorMessageTenDays == null){
                if (uiState.city != null){
                    binding.fragmentWeatherMapLocation.text = getString(R.string.lbl_weather_map_current_location).format(uiState.city.name)
                    currentWeather = SavedWeather(
                        uiState.city.name,
                        uiState.tenDaysItems.first().dayOfWeek + " " + uiState.tenDaysItems.first().dayOfMonth,
                        uiState.tenDaysItems.first().forecastState,
                        uiState.tenDaysItems.first().hourlyForecast.first().temperature,
                        uiState.tenDaysItems.first().hourlyForecast.first().temperature,
                        uiState.tenDaysItems.first().minTemperature,
                        uiState.tenDaysItems.first().maxTemperature
                    )
                }
            }
        }

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)?.let { itemDecorator.setDrawable(it) }

        val savedWeathersAdapter = SavedWeathersAdapter()
        binding.apply {
            savedWeathersRecyclerView.adapter = savedWeathersAdapter
            savedWeathersRecyclerView.layoutManager = LinearLayoutManager(context)
            savedWeathersRecyclerView.addItemDecoration(itemDecorator)
        }


        databaseViewModel.getSavedWeathers().observe(viewLifecycleOwner){ savedWeathers ->
            savedWeathersAdapter.submitList(savedWeathers)
        }

        setClickListeners()

        return binding.root
    }

    private fun setClickListeners(){
        binding.fragmentWeatherMapAdd.setOnClickListener{
            currentWeather?.let {
                databaseViewModel.insertWeather(it)
            }
        }

        binding.fragmentWeatherMapUpdate.setOnClickListener{
            currentWeather?.let {
                databaseViewModel.updateWeather(it)
            }
        }

        binding.fragmentWeatherMapDelete.setOnClickListener{
            currentWeather?.location?.let {
                databaseViewModel.deleteWeather(it)
            }
        }
    }
}
