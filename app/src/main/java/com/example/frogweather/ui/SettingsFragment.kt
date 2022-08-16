package com.example.frogweather.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.frogweather.R
import com.example.frogweather.data.SettingType
import com.example.frogweather.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        setSettingViews()
        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.settings_status_bar)
        return binding.root
    }

    private fun setSettingViews() {
        binding.apply {
            appBar.toolbarTitle.text = getString(R.string.lbl_settings_title)
            appBar.backButton.setOnClickListener { findNavController().navigateUp() }
            settingsSectorTitle.sectorTitle.text = getString(R.string.lbl_settings_title)
            detectLocationTab.tabTitle.text = getString(R.string.lbl_detect_location_tab)
            detectLocationTab.root.setOnClickListener { detectLocationTab.checkbox.isChecked = !detectLocationTab.checkbox.isChecked }
            unitsSectorTitle.sectorTitle.text = getString(R.string.lbl_units_sector_title)
            hoursFormatTab.tabTitle.text = getString(R.string.lbl_hour_format_tab)
            hoursFormatTab.root.setOnClickListener { hoursFormatTab.checkbox.isChecked = !hoursFormatTab.checkbox.isChecked }
            temperatureUnitsTab.tabTitle.text = getString(R.string.lbl_temperature_units_tab)
            temperatureUnitsTab.tabOption.text = getString(R.string.lbl_celsius)
            lengthUnitsTab.tabTitle.text = getString(R.string.lbl_length_units_tab)
            lengthUnitsTab.tabOption.text = getString(R.string.lbl_millimeters)
            speedUnitsTab.tabTitle.text = getString(R.string.lbl_speed_units_tab)
            speedUnitsTab.tabOption.text = getString(R.string.lbl_meters)
            distanceUnitsTab.tabTitle.text = getString(R.string.lbl_distance_units_tab)
            distanceUnitsTab.tabOption.text = getString(R.string.lbl_km)
            pressureUnitsTab.tabTitle.text = getString(R.string.lbl_pressure_units_tab)
            pressureUnitsTab.tabOption.text = getString(R.string.lbl_mm_hg)
            displaySectorTitle.sectorTitle.text = getString(R.string.lbl_display_sector_title)
            windDirectionFormatTab.tabTitle.text = getString(R.string.lbl_wind_direction_format_tab)
            windDirectionFormatTab.tabOption.text = getString(R.string.lbl_arrows)
            windDirectionFormatTab.lineView.visibility = View.GONE
            notificationSectorTitle.sectorTitle.text = getString(R.string.lbl_notification_sector_title)
            showNotificationsTab.tabTitle.text = getString(R.string.lbl_weather_notification_tab)
            showNotificationsTab.root.setOnClickListener {
                showNotificationsTab.checkbox.isChecked = !showNotificationsTab.checkbox.isChecked
                if (showNotificationsTab.checkbox.isChecked) {
                    notificationTypeTab.root.isClickable = true
                    notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
                } else {
                    notificationTypeTab.root.isClickable = false
                    notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
                }
            }
            notificationTypeTab.tabTitle.text = getString(R.string.lbl_notification_type_tab)
            notificationTypeTab.tabOption.text = getString(R.string.lbl_simple_notification)

            temperatureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.TEMPERATURE) }
            lengthUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.LENGTH) }
            speedUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.SPEED) }
            distanceUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.DISTANCE) }
            pressureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.PRESSURE) }
            windDirectionFormatTab.root.setOnClickListener { showSettingDialog(SettingType.WIND_DIRECTION_FORMAT) }
            notificationTypeTab.root.setOnClickListener { showSettingDialog(SettingType.NOTIFICATION) }

            if (showNotificationsTab.checkbox.isChecked) {
                notificationTypeTab.root.isClickable = true
                notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
            } else {
                notificationTypeTab.root.isClickable = false
                notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
            }
        }
    }

    private fun showSettingDialog(settingType: SettingType) {
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.SettingsDialogTheme)
        alertDialog.setTitle(getSettingsDialogTitle(settingType))
        alertDialog.setNegativeButton(getString(R.string.lbl_cancel_button)) { _, _ -> }
        val settingItems = getSettingsDialogItems(settingType)
        alertDialog.setSingleChoiceItems(settingItems, 1) { _, _ -> }
        val alert = alertDialog.create()
        alert.show()
    }

    private fun getSettingsDialogTitle(settingType: SettingType): String {
        return when (settingType) {
            SettingType.TEMPERATURE -> {
                getString(R.string.lbl_temperature_units_tab)
            }
            SettingType.LENGTH -> {
                getString(R.string.lbl_length_units_tab)
            }
            SettingType.SPEED -> {
                getString(R.string.lbl_speed_units_tab)
            }
            SettingType.DISTANCE -> {
                getString(R.string.lbl_distance_units_tab)
            }
            SettingType.PRESSURE -> {
                getString(R.string.lbl_pressure_units_tab)
            }
            SettingType.WIND_DIRECTION_FORMAT -> {
                getString(R.string.lbl_wind_direction_format_tab)
            }
            SettingType.NOTIFICATION -> {
                getString(R.string.lbl_notification_type_tab)
            }
        }
    }

    private fun getSettingsDialogItems(settingType: SettingType): Array<String> {
        return when (settingType) {
            SettingType.TEMPERATURE -> {
                arrayOf(
                    getString(R.string.lbl_celsius),
                    getString(R.string.lbl_fahrenheit),
                    getString(R.string.lbl_kelvin)
                )
            }
            SettingType.LENGTH -> {
                arrayOf(
                    getString(R.string.lbl_millimeters),
                    getString(R.string.lbl_inches)
                )
            }
            SettingType.SPEED -> {
                arrayOf(
                    getString(R.string.lbl_meters),
                    getString(R.string.lbl_kilometers),
                    getString(R.string.lbl_miles),
                    getString(R.string.lbl_beaufort),
                    getString(R.string.lbl_knots)
                )
            }
            SettingType.DISTANCE -> {
                arrayOf(
                    getString(R.string.lbl_km),
                    getString(R.string.lbl_mi)
                )
            }
            SettingType.PRESSURE -> {
                arrayOf(
                    getString(R.string.lbl_hpa),
                    getString(R.string.lbl_kpa),
                    getString(R.string.lbl_mm_hg),
                    getString(R.string.lbl_in_hg)
                )
            }
            SettingType.WIND_DIRECTION_FORMAT -> {
                arrayOf(
                    getString(R.string.lbl_no_indications),
                    getString(R.string.lbl_arrows),
                    getString(R.string.lbl_abbreviations)
                )
            }
            SettingType.NOTIFICATION -> {
                arrayOf(
                    getString(R.string.lbl_default_android_view),
                    getString(R.string.lbl_simple_notification)
                )
            }
        }
    }

}