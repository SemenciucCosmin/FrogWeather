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
import com.example.frogweather.data.DistanceType
import com.example.frogweather.data.LengthType
import com.example.frogweather.data.NotificationType
import com.example.frogweather.data.PressureType
import com.example.frogweather.data.SettingType
import com.example.frogweather.data.SpeedType
import com.example.frogweather.data.TemperatureType
import com.example.frogweather.data.WindDirectionType
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
        alertDialog.setTitle(settingType.settingType)
        alertDialog.setNegativeButton("Cancel") { _, _ -> }
        val settingItems = getSettingsItems(settingType)
        alertDialog.setSingleChoiceItems(settingItems, 1) { _, _ -> }
        val alert = alertDialog.create()
        alert.show()
    }

    private fun getSettingsItems(settingType: SettingType): Array<String> {
        return when (settingType) {
            SettingType.TEMPERATURE -> {
                arrayOf(
                    TemperatureType.CELSIUS.temperatureType,
                    TemperatureType.FAHRENHEIT.temperatureType, TemperatureType.KELVIN.temperatureType
                )
            }
            SettingType.LENGTH -> {
                arrayOf(
                    LengthType.MILLIMETERS.lengthType,
                    LengthType.INCHES.lengthType
                )
            }
            SettingType.SPEED -> {
                arrayOf(
                    SpeedType.METERS_PER_SECOND.speedType,
                    SpeedType.KILOMETERS_PER_HOUR.speedType,
                    SpeedType.MILES_PER_HOUR.speedType,
                    SpeedType.BEAUFORT_WIND_SCALE.speedType,
                    SpeedType.KNOTS.speedType
                )
            }
            SettingType.DISTANCE -> {
                arrayOf(
                    DistanceType.KILOMETERS.distanceType,
                    DistanceType.MILES.distanceType
                )
            }
            SettingType.PRESSURE -> {
                arrayOf(
                    PressureType.HPA.pressureType,
                    PressureType.KPA.pressureType,
                    PressureType.MMHG.pressureType,
                    PressureType.INHG.pressureType
                )
            }
            SettingType.WIND_DIRECTION_FORMAT -> {
                arrayOf(
                    WindDirectionType.NO_INDICATION.windDirectionType,
                    WindDirectionType.ARROWS.windDirectionType,
                    WindDirectionType.ABBREVIATIONS.windDirectionType
                )
            }
            SettingType.NOTIFICATION -> {
                arrayOf(
                    NotificationType.DEFAULT.notificationType,
                    NotificationType.SIMPLE.notificationType
                )
            }
        }
    }

}