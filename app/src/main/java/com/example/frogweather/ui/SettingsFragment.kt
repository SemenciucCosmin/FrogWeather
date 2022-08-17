package com.example.frogweather.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.frogweather.R
import com.example.frogweather.data.DISTANCE_KM
import com.example.frogweather.data.DISTANCE_MI
import com.example.frogweather.data.DistanceType
import com.example.frogweather.data.LENGTH_INCHES
import com.example.frogweather.data.LENGTH_MILLIMETERS
import com.example.frogweather.data.LengthType
import com.example.frogweather.data.NOTIFICATION_TYPE_DEFAULT
import com.example.frogweather.data.NOTIFICATION_TYPE_SIMPLE
import com.example.frogweather.data.NotificationType
import com.example.frogweather.data.PRESSURE_HPA
import com.example.frogweather.data.PRESSURE_INHG
import com.example.frogweather.data.PRESSURE_KPA
import com.example.frogweather.data.PRESSURE_MMHG
import com.example.frogweather.data.PressureType
import com.example.frogweather.data.SPEED_BEAUFORT
import com.example.frogweather.data.SPEED_KILOMETERS
import com.example.frogweather.data.SPEED_KNOTS
import com.example.frogweather.data.SPEED_METERS
import com.example.frogweather.data.SPEED_MILES
import com.example.frogweather.data.SettingType
import com.example.frogweather.data.Settings
import com.example.frogweather.data.SpeedType
import com.example.frogweather.data.TEMPERATURE_CELSIUS
import com.example.frogweather.data.TEMPERATURE_FAHRENHEIT
import com.example.frogweather.data.TEMPERATURE_KELVIN
import com.example.frogweather.data.TemperatureType
import com.example.frogweather.data.WIND_DIRECTION_ABBREVIATIONS
import com.example.frogweather.data.WIND_DIRECTION_ARROWS
import com.example.frogweather.data.WIND_DIRECTION_NO_INDICATION
import com.example.frogweather.data.WindDirectionType
import com.example.frogweather.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val settingsViewModel: SettingsViewModel by activityViewModels {
        SettingsViewModel.SettingsViewModelFactory(
            (activity?.application as FrogWeatherApplication)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.settings_status_bar)

        settingsViewModel.getSettings().observe(viewLifecycleOwner) {
            setSettingTabsTitle(it)
            setSettingTabsOption(it)
            setSettingTabsClickListener(it)
        }

        return binding.root
    }

    private fun setSettingTabsOption(settings: Settings) {
        binding.apply {
            detectLocationTab.checkbox.isChecked = settings.detectLocation
            hoursFormatTab.checkbox.isChecked = settings.hourFormatUnit
            temperatureUnitsTab.tabOption.text = getString(TemperatureType.getByTemperatureType(settings.temperatureUnit).resourceId)
            lengthUnitsTab.tabOption.text = getString(LengthType.getByLengthType(settings.lengthUnit).resourceId)
            speedUnitsTab.tabOption.text = getString(SpeedType.getBySpeedType(settings.speedUnit).resourceId)
            distanceUnitsTab.tabOption.text = getString(DistanceType.getByDistanceType(settings.distanceUnit).resourceId)
            pressureUnitsTab.tabOption.text = getString(PressureType.getByPressureType(settings.pressureUnit).resourceId)
            windDirectionFormatTab.tabOption.text = getString(WindDirectionType.getByWindDirectionType(settings.windDirection).resourceId)
            showNotificationsTab.checkbox.isChecked = settings.showNotification
            notificationTypeTab.tabOption.text = getString(NotificationType.getByNotificationType(settings.notificationType).resourceId)
        }
    }

    private fun setSettingTabsTitle(settings: Settings) {
        binding.apply {
            appBar.toolbarTitle.text = getString(R.string.lbl_settings_title)
            settingsSectorTitle.sectorTitle.text = getString(R.string.lbl_settings_title)
            detectLocationTab.tabTitle.text = getString(SettingType.DETECT_LOCATION.resourceId)
            unitsSectorTitle.sectorTitle.text = getString(R.string.lbl_units_sector_title)
            hoursFormatTab.tabTitle.text = getString(SettingType.HOUR_FORMAT.resourceId)
            temperatureUnitsTab.tabTitle.text = getString(SettingType.TEMPERATURE.resourceId)
            lengthUnitsTab.tabTitle.text = getString(SettingType.LENGTH.resourceId)
            speedUnitsTab.tabTitle.text = getString(SettingType.SPEED.resourceId)
            distanceUnitsTab.tabTitle.text = getString(SettingType.DISTANCE.resourceId)
            pressureUnitsTab.tabTitle.text = getString(SettingType.PRESSURE.resourceId)
            displaySectorTitle.sectorTitle.text = getString(R.string.lbl_display_sector_title)
            windDirectionFormatTab.tabTitle.text = getString(SettingType.WIND_DIRECTION_FORMAT.resourceId)
            windDirectionFormatTab.lineView.visibility = View.GONE
            notificationSectorTitle.sectorTitle.text = getString(R.string.lbl_notification_sector_title)
            showNotificationsTab.tabTitle.text = getString(SettingType.SHOW_NOTIFICATION.resourceId)
            notificationTypeTab.tabTitle.text = getString(SettingType.NOTIFICATION.resourceId)
            if (settings.showNotification) {
                notificationTypeTab.root.isEnabled = true
                notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
            } else {
                notificationTypeTab.root.isEnabled = false
                notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
            }
        }
    }

    private fun setSettingTabsClickListener(settings: Settings) {
        binding.apply {
            appBar.backButton.setOnClickListener { findNavController().navigateUp() }
            detectLocationTab.root.setOnClickListener {
                detectLocationTab.checkbox.isChecked = !detectLocationTab.checkbox.isChecked
                settingsViewModel.saveDetectLocation(detectLocationTab.checkbox.isChecked, requireContext())
            }
            hoursFormatTab.root.setOnClickListener {
                hoursFormatTab.checkbox.isChecked = !hoursFormatTab.checkbox.isChecked
                settingsViewModel.saveHourFormat(hoursFormatTab.checkbox.isChecked, requireContext())
            }
            showNotificationsTab.root.setOnClickListener {
                showNotificationsTab.checkbox.isChecked = !showNotificationsTab.checkbox.isChecked
                settingsViewModel.saveShowNotification(showNotificationsTab.checkbox.isChecked, requireContext())
                if (showNotificationsTab.checkbox.isChecked) {
                    notificationTypeTab.root.isEnabled = true
                    notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
                } else {
                    notificationTypeTab.root.isEnabled = false
                    notificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
                }
            }
            temperatureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.TEMPERATURE, settings) }
            lengthUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.LENGTH, settings) }
            speedUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.SPEED, settings) }
            distanceUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.DISTANCE, settings) }
            pressureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.PRESSURE, settings) }
            windDirectionFormatTab.root.setOnClickListener { showSettingDialog(SettingType.WIND_DIRECTION_FORMAT, settings) }
            notificationTypeTab.root.setOnClickListener { showSettingDialog(SettingType.NOTIFICATION, settings) }
        }
    }

    private fun showSettingDialog(settingType: SettingType, settings: Settings) {
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.SettingsDialogTheme)
        alertDialog.setTitle(getString(settingType.resourceId))
        alertDialog.setNegativeButton(getString(R.string.lbl_cancel_button)) { _, _ -> }

        val settingItems = getSettingsDialogItems(settingType)
        val checkedPosition = getSettingsDialogCheckedPosition(settingType, settings, settingItems)

        alertDialog.setSingleChoiceItems(settingItems, checkedPosition) { dialog, position ->
            setSettingsDialogClickListener(dialog, settingItems[position])
        }

        val alert = alertDialog.create()
        alert.show()
    }

    private fun setSettingsDialogClickListener(dialog: DialogInterface, option: String) {
        when (option) {
            getString(TemperatureType.CELSIUS.resourceId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_CELSIUS, requireContext())
            }
            getString(TemperatureType.FAHRENHEIT.resourceId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_FAHRENHEIT, requireContext())
            }
            getString(TemperatureType.KELVIN.resourceId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_KELVIN, requireContext())
            }
            getString(LengthType.MILLIMETERS.resourceId) -> {
                settingsViewModel.saveLengthUnit(LENGTH_MILLIMETERS, requireContext())
            }
            getString(LengthType.INCHES.resourceId) -> {
                settingsViewModel.saveLengthUnit(LENGTH_INCHES, requireContext())
            }
            getString(SpeedType.METERS_PER_SECOND.resourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_METERS, requireContext())
            }
            getString(SpeedType.KILOMETERS_PER_HOUR.resourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_KILOMETERS, requireContext())
            }
            getString(SpeedType.MILES_PER_HOUR.resourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_MILES, requireContext())
            }
            getString(SpeedType.BEAUFORT_WIND_SCALE.resourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_BEAUFORT, requireContext())
            }
            getString(SpeedType.KNOTS.resourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_KNOTS, requireContext())
            }
            getString(DistanceType.KILOMETERS.resourceId) -> {
                settingsViewModel.saveDistanceUnit(DISTANCE_KM, requireContext())
            }
            getString(DistanceType.MILES.resourceId) -> {
                settingsViewModel.saveDistanceUnit(DISTANCE_MI, requireContext())
            }
            getString(PressureType.HPA.resourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_HPA, requireContext())
            }
            getString(PressureType.KPA.resourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_KPA, requireContext())
            }
            getString(PressureType.MMHG.resourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_MMHG, requireContext())
            }
            getString(PressureType.INHG.resourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_INHG, requireContext())
            }
            getString(WindDirectionType.NO_INDICATION.resourceId) -> {
                settingsViewModel.saveWindDirection(WIND_DIRECTION_NO_INDICATION, requireContext())
            }
            getString(WindDirectionType.ARROWS.resourceId) -> {
                settingsViewModel.saveWindDirection(WIND_DIRECTION_ARROWS, requireContext())
            }
            getString(WindDirectionType.ABBREVIATIONS.resourceId) -> {
                settingsViewModel.saveWindDirection(WIND_DIRECTION_ABBREVIATIONS, requireContext())
            }
            getString(NotificationType.DEFAULT.resourceId) -> {
                settingsViewModel.saveNotificationType(NOTIFICATION_TYPE_DEFAULT, requireContext())
            }
            getString(NotificationType.SIMPLE.resourceId) -> {
                settingsViewModel.saveNotificationType(NOTIFICATION_TYPE_SIMPLE, requireContext())
            }
        }
        dialog.cancel()
    }

    private fun getSettingsDialogCheckedPosition(settingType: SettingType, settings: Settings, settingItems: Array<String>): Int {
        return when (settingType) {
            SettingType.TEMPERATURE -> {
                settingItems.indexOf(getString(TemperatureType.getByTemperatureType(settings.temperatureUnit).resourceId))
            }
            SettingType.LENGTH -> {
                settingItems.indexOf(getString(LengthType.getByLengthType(settings.lengthUnit).resourceId))
            }
            SettingType.SPEED -> {
                settingItems.indexOf(getString(SpeedType.getBySpeedType(settings.speedUnit).resourceId))
            }
            SettingType.DISTANCE -> {
                settingItems.indexOf(getString(DistanceType.getByDistanceType(settings.distanceUnit).resourceId))
            }
            SettingType.PRESSURE -> {
                settingItems.indexOf(getString(PressureType.getByPressureType(settings.pressureUnit).resourceId))
            }
            SettingType.WIND_DIRECTION_FORMAT -> {
                settingItems.indexOf(getString(WindDirectionType.getByWindDirectionType(settings.windDirection).resourceId))
            }
            SettingType.NOTIFICATION -> {
                settingItems.indexOf(getString(NotificationType.getByNotificationType(settings.notificationType).resourceId))
            }
            SettingType.DETECT_LOCATION -> {
                settingItems.lastIndex
            }
            SettingType.HOUR_FORMAT -> {
                settingItems.lastIndex
            }
            SettingType.SHOW_NOTIFICATION -> {
                settingItems.lastIndex
            }
        }
    }

    private fun getSettingsDialogItems(settingType: SettingType): Array<String> {
        return when (settingType) {
            SettingType.TEMPERATURE -> {
                arrayOf(
                    getString(TemperatureType.CELSIUS.resourceId),
                    getString(TemperatureType.FAHRENHEIT.resourceId),
                    getString(TemperatureType.KELVIN.resourceId)
                )
            }
            SettingType.LENGTH -> {
                arrayOf(
                    getString(LengthType.MILLIMETERS.resourceId),
                    getString(LengthType.INCHES.resourceId)
                )
            }
            SettingType.SPEED -> {
                arrayOf(
                    getString(SpeedType.METERS_PER_SECOND.resourceId),
                    getString(SpeedType.KILOMETERS_PER_HOUR.resourceId),
                    getString(SpeedType.MILES_PER_HOUR.resourceId),
                    getString(SpeedType.BEAUFORT_WIND_SCALE.resourceId),
                    getString(SpeedType.KNOTS.resourceId)
                )
            }
            SettingType.DISTANCE -> {
                arrayOf(
                    getString(DistanceType.KILOMETERS.resourceId),
                    getString(DistanceType.MILES.resourceId)
                )
            }
            SettingType.PRESSURE -> {
                arrayOf(
                    getString(PressureType.HPA.resourceId),
                    getString(PressureType.KPA.resourceId),
                    getString(PressureType.MMHG.resourceId),
                    getString(PressureType.INHG.resourceId)
                )
            }
            SettingType.WIND_DIRECTION_FORMAT -> {
                arrayOf(
                    getString(WindDirectionType.NO_INDICATION.resourceId),
                    getString(WindDirectionType.ARROWS.resourceId),
                    getString(WindDirectionType.ABBREVIATIONS.resourceId)
                )
            }
            SettingType.NOTIFICATION -> {
                arrayOf(
                    getString(NotificationType.DEFAULT.resourceId),
                    getString(NotificationType.SIMPLE.resourceId)
                )
            }
            SettingType.DETECT_LOCATION -> arrayOf()
            SettingType.HOUR_FORMAT -> arrayOf()
            SettingType.SHOW_NOTIFICATION -> arrayOf()
        }
    }
}