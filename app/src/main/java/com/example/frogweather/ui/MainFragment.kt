package com.example.frogweather.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.frogweather.R
import com.example.frogweather.data.MyLocation
import com.example.frogweather.data.ViewPagerAdapter
import com.example.frogweather.databinding.FragmentMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.android.material.tabs.TabLayout

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
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
        binding = FragmentMainBinding.inflate(inflater, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_2)
        binding.appBar.drawerButton.setOnClickListener { binding.drawerLayout.open() }
        binding.navView.setNavigationItemSelectedListener { setDrawerItemAction(it) }

        val fragments = listOf(OneDayFragment(), OneDayFragment(), TenDaysFragment())
        val adapter = ViewPagerAdapter(fragments, requireActivity() as AppCompatActivity)
        binding.appBar.viewPager.adapter = adapter
        binding.appBar.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.appBar.tabLayout.getTabAt(position)?.select()
            }
        })
        binding.appBar.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.appBar.viewPager.currentItem = tab.position
                }
            }
        })

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.drawerLayout.close()
    }

    private fun setDrawerItemAction(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.weatherMapFragment -> {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToWeatherMapFragment())
            }
            R.id.nav_refresh -> {
                binding.drawerLayout.close()
            }
            R.id.nav_detect_location -> {
                updateLocation()
                binding.drawerLayout.close()
            }
            R.id.settingsFragment -> {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSettingsFragment())
            }
        }
        return true
    }

    private fun updateLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
                override fun isCancellationRequested() = false
            }).addOnSuccessListener { location: Location? ->
                if (location != null) {
                    settingsViewModel.saveLocation(MyLocation(System.currentTimeMillis(), location.latitude, location.longitude), requireContext())
                }
            }
        }
    }
}