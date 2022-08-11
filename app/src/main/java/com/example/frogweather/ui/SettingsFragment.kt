package com.example.frogweather.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.frogweather.R
import com.example.frogweather.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setSettingViews()
        return binding.root
    }

    private fun setSettingViews(){
        binding.apply {
            appBar.toolbarTitle.text = getString(R.string.settings_title)
            appBar.backButton.setOnClickListener { findNavController().navigateUp() }
            settingsSectorTitle.sectorTitle.text = getString(R.string.settings_title)
            detectLocationTab.tabTitle.text = getString(R.string.detect_location_tab)
            detectLocationTab.root.setOnClickListener { detectLocationTab.checkbox.isChecked = !detectLocationTab.checkbox.isChecked }
            unitsSectorTitle.sectorTitle.text = getString(R.string.units_sector_title)
            hoursFormatTab.tabTitle.text = getString(R.string.hour_format_tab)
            hoursFormatTab.root.setOnClickListener { hoursFormatTab.checkbox.isChecked = !hoursFormatTab.checkbox.isChecked }
            temperatureUnitsTab.tabTitle.text = getString(R.string.temperature_units_tab)
            temperatureUnitsTab.tabOption.text = getString(R.string.celsius)
            lengthUnitsTab.tabTitle.text = getString(R.string.length_units_tab)
            lengthUnitsTab.tabOption.text = getString(R.string.millimeters)
            speedUnitsTab.tabTitle.text = getString(R.string.speed_units_tab)
            speedUnitsTab.tabOption.text = getString(R.string.meters)
            distanceUnitsTab.tabTitle.text = getString(R.string.distance_units_tab)
            distanceUnitsTab.tabOption.text = getString(R.string.km)
            pressureUnitsTab.tabTitle.text = getString(R.string.pressure_units_tab)
            pressureUnitsTab.tabOption.text = getString(R.string.mm_hg)
            displaySectorTitle.sectorTitle.text = getString(R.string.display_sector_title)
            windDirectionFormatTab.tabTitle.text = getString(R.string.wind_direction_format_tab)
            windDirectionFormatTab.tabOption.text = getString(R.string.arrows)
            windDirectionFormatTab.lineView.visibility = View.GONE
            notificationSectorTitle.sectorTitle.text = getString(R.string.notification_sector_title)
            showNotificationsTab.tabTitle.text = getString(R.string.weather_notification_tab)
            showNotificationsTab.root.setOnClickListener { showNotificationsTab.checkbox.isChecked = !showNotificationsTab.checkbox.isChecked }
            notificationTypeTab.tabTitle.text = getString(R.string.notification_type_tab)
            notificationTypeTab.tabOption.text = getString(R.string.simple_notification)

            temperatureUnitsTab.root.setOnClickListener{ showSettingDialog() }
        }
    }

    private fun showSettingDialog(){
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("asd")
        val settingItems = arrayOf("asd1", "asd2", "asd3")
        alertDialog.setSingleChoiceItems(settingItems, 1, object: DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, position: Int) {
            }
        })
        val alert = alertDialog.create()
        alert.show()
    }

}