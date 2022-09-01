package com.example.frogweather.ui.fragments

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
import com.example.frogweather.data.utils.DISTANCE_KM
import com.example.frogweather.data.utils.DISTANCE_MI
import com.example.frogweather.data.enums.DistanceType
import com.example.frogweather.data.utils.LENGTH_INCHES
import com.example.frogweather.data.utils.LENGTH_MILLIMETERS
import com.example.frogweather.data.enums.LengthType
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_DEFAULT
import com.example.frogweather.data.utils.NOTIFICATION_TYPE_SIMPLE
import com.example.frogweather.data.enums.NotificationType
import com.example.frogweather.data.utils.PRESSURE_HPA
import com.example.frogweather.data.utils.PRESSURE_INHG
import com.example.frogweather.data.utils.PRESSURE_KPA
import com.example.frogweather.data.utils.PRESSURE_MMHG
import com.example.frogweather.data.enums.PressureType
import com.example.frogweather.data.utils.SPEED_BEAUFORT
import com.example.frogweather.data.utils.SPEED_KILOMETERS
import com.example.frogweather.data.utils.SPEED_KNOTS
import com.example.frogweather.data.utils.SPEED_METERS
import com.example.frogweather.data.utils.SPEED_MILES
import com.example.frogweather.data.enums.SettingType
import com.example.frogweather.data.classes.Settings
import com.example.frogweather.data.enums.SpeedType
import com.example.frogweather.data.utils.TEMPERATURE_CELSIUS
import com.example.frogweather.data.utils.TEMPERATURE_FAHRENHEIT
import com.example.frogweather.data.utils.TEMPERATURE_KELVIN
import com.example.frogweather.data.enums.TemperatureType
import com.example.frogweather.data.utils.WIND_DIRECTION_ABBREVIATIONS
import com.example.frogweather.data.utils.WIND_DIRECTION_ARROWS
import com.example.frogweather.data.utils.WIND_DIRECTION_NO_INDICATION
import com.example.frogweather.data.enums.WindDirectionType
import com.example.frogweather.databinding.FragmentSettingsBinding
import com.example.frogweather.ui.application.FrogWeatherApplication
import com.example.frogweather.ui.models.SettingsViewModel

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
        (activity as AppCompatActivity).window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue_7)

        settingsViewModel.getSettings().observe(viewLifecycleOwner) {
            setSettingTabsTitle(it)
            setSettingTabsOption(it)
            setSettingTabsClickListener(it)
        }

        return binding.root
    }

    private fun setSettingTabsOption(settings: Settings) {
        binding.apply {
            fragmentSettingsDetectLocationTab.checkbox.isChecked = settings.detectLocation
            fragmentSettingsHoursFormatTab.checkbox.isChecked = settings.hourFormatUnit
            fragmentSettingsTemperatureUnitsTab.tabOption.text = getString(TemperatureType.getByTemperatureType(settings.temperatureUnit).nameId)
            fragmentSettingsLengthUnitsTab.tabOption.text = getString(LengthType.getByLengthType(settings.lengthUnit).longResourceId)
            fragmentSettingsSpeedUnitsTab.tabOption.text = getString(SpeedType.getBySpeedType(settings.speedUnit).longResourceId)
            fragmentSettingsDistanceUnitsTab.tabOption.text = getString(DistanceType.getByDistanceType(settings.distanceUnit).labelResourceId)
            fragmentSettingsPressureUnitsTab.tabOption.text = getString(PressureType.getByPressureType(settings.pressureUnit).labelResourceId)
            fragmentSettingsWindDirectionFormatTab.tabOption.text =
                getString(WindDirectionType.getByWindDirectionType(settings.windDirection).resourceId)
            fragmentSettingsShowNotificationsTab.checkbox.isChecked = settings.showNotification
            fragmentSettingsNotificationTypeTab.tabOption.text =
                getString(NotificationType.getByNotificationType(settings.notificationType).resourceId)
        }
    }

    private fun setSettingTabsTitle(settings: Settings) {
        binding.apply {
            fragmentSettingsAppBar.toolbarTitle.text = getString(R.string.lbl_settings_fragment_title)
            fragmentSettingsUnitsSectorTitle.sectorTitle.text = getString(R.string.lbl_settings_fragment_title)
            fragmentSettingsDetectLocationTab.tabTitle.text = getString(SettingType.DETECT_LOCATION.resourceId)
            fragmentSettingsUnitsSectorTitle.sectorTitle.text = getString(R.string.lbl_units_sector_title)
            fragmentSettingsHoursFormatTab.tabTitle.text = getString(SettingType.HOUR_FORMAT.resourceId)
            fragmentSettingsTemperatureUnitsTab.tabTitle.text = getString(SettingType.TEMPERATURE.resourceId)
            fragmentSettingsLengthUnitsTab.tabTitle.text = getString(SettingType.LENGTH.resourceId)
            fragmentSettingsSpeedUnitsTab.tabTitle.text = getString(SettingType.SPEED.resourceId)
            fragmentSettingsDistanceUnitsTab.tabTitle.text = getString(SettingType.DISTANCE.resourceId)
            fragmentSettingsPressureUnitsTab.tabTitle.text = getString(SettingType.PRESSURE.resourceId)
            fragmentSettingsDisplaySectorTitle.sectorTitle.text = getString(R.string.lbl_display_sector_title)
            fragmentSettingsWindDirectionFormatTab.tabTitle.text = getString(SettingType.WIND_DIRECTION_FORMAT.resourceId)
            fragmentSettingsWindDirectionFormatTab.lineView.visibility = View.GONE
            fragmentSettingsNotificationSectorTitle.sectorTitle.text = getString(R.string.lbl_notification_sector_title)
            fragmentSettingsShowNotificationsTab.tabTitle.text = getString(SettingType.SHOW_NOTIFICATION.resourceId)
            fragmentSettingsNotificationTypeTab.tabTitle.text = getString(SettingType.NOTIFICATION.resourceId)
            if (settings.showNotification) {
                fragmentSettingsNotificationTypeTab.root.isEnabled = true
                fragmentSettingsNotificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
            } else {
                fragmentSettingsNotificationTypeTab.root.isEnabled = false
                fragmentSettingsNotificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
            }
        }
    }

    private fun setSettingTabsClickListener(settings: Settings) {
        binding.apply {
            fragmentSettingsAppBar.backButton.setOnClickListener { findNavController().navigateUp() }
            fragmentSettingsDetectLocationTab.root.setOnClickListener {
                fragmentSettingsDetectLocationTab.checkbox.isChecked = !fragmentSettingsDetectLocationTab.checkbox.isChecked
                settingsViewModel.saveDetectLocation(fragmentSettingsDetectLocationTab.checkbox.isChecked, requireContext())
            }
            fragmentSettingsHoursFormatTab.root.setOnClickListener {
                fragmentSettingsHoursFormatTab.checkbox.isChecked = !fragmentSettingsHoursFormatTab.checkbox.isChecked
                settingsViewModel.saveHourFormat(fragmentSettingsHoursFormatTab.checkbox.isChecked, requireContext())
            }
            fragmentSettingsShowNotificationsTab.root.setOnClickListener {
                fragmentSettingsShowNotificationsTab.checkbox.isChecked = !fragmentSettingsShowNotificationsTab.checkbox.isChecked
                settingsViewModel.saveShowNotification(fragmentSettingsShowNotificationsTab.checkbox.isChecked, requireContext())
                if (fragmentSettingsShowNotificationsTab.checkbox.isChecked) {
                    fragmentSettingsNotificationTypeTab.root.isEnabled = true
                    fragmentSettingsNotificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_3))
                } else {
                    fragmentSettingsNotificationTypeTab.root.isEnabled = false
                    fragmentSettingsNotificationTypeTab.tabOption.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_2))
                }
            }
            fragmentSettingsTemperatureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.TEMPERATURE, settings) }
            fragmentSettingsLengthUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.LENGTH, settings) }
            fragmentSettingsSpeedUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.SPEED, settings) }
            fragmentSettingsDistanceUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.DISTANCE, settings) }
            fragmentSettingsPressureUnitsTab.root.setOnClickListener { showSettingDialog(SettingType.PRESSURE, settings) }
            fragmentSettingsWindDirectionFormatTab.root.setOnClickListener { showSettingDialog(SettingType.WIND_DIRECTION_FORMAT, settings) }
            fragmentSettingsShowNotificationsTab.root.setOnClickListener { showSettingDialog(SettingType.NOTIFICATION, settings) }
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
            getString(TemperatureType.CELSIUS.nameId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_CELSIUS, requireContext())
            }
            getString(TemperatureType.FAHRENHEIT.nameId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_FAHRENHEIT, requireContext())
            }
            getString(TemperatureType.KELVIN.nameId) -> {
                settingsViewModel.saveTemperatureUnit(TEMPERATURE_KELVIN, requireContext())
            }
            getString(LengthType.MILLIMETERS.longResourceId) -> {
                settingsViewModel.saveLengthUnit(LENGTH_MILLIMETERS, requireContext())
            }
            getString(LengthType.INCHES.longResourceId) -> {
                settingsViewModel.saveLengthUnit(LENGTH_INCHES, requireContext())
            }
            getString(SpeedType.METERS_PER_SECOND.longResourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_METERS, requireContext())
            }
            getString(SpeedType.KILOMETERS_PER_HOUR.longResourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_KILOMETERS, requireContext())
            }
            getString(SpeedType.MILES_PER_HOUR.longResourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_MILES, requireContext())
            }
            getString(SpeedType.BEAUFORT_WIND_SCALE.longResourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_BEAUFORT, requireContext())
            }
            getString(SpeedType.KNOTS.longResourceId) -> {
                settingsViewModel.saveSpeedUnit(SPEED_KNOTS, requireContext())
            }
            getString(DistanceType.KILOMETERS.labelResourceId) -> {
                settingsViewModel.saveDistanceUnit(DISTANCE_KM, requireContext())
            }
            getString(DistanceType.MILES.labelResourceId) -> {
                settingsViewModel.saveDistanceUnit(DISTANCE_MI, requireContext())
            }
            getString(PressureType.HPA.labelResourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_HPA, requireContext())
            }
            getString(PressureType.KPA.labelResourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_KPA, requireContext())
            }
            getString(PressureType.MMHG.labelResourceId) -> {
                settingsViewModel.savePressureUnit(PRESSURE_MMHG, requireContext())
            }
            getString(PressureType.INHG.labelResourceId) -> {
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
                settingItems.indexOf(getString(TemperatureType.getByTemperatureType(settings.temperatureUnit).nameId))
            }
            SettingType.LENGTH -> {
                settingItems.indexOf(getString(LengthType.getByLengthType(settings.lengthUnit).longResourceId))
            }
            SettingType.SPEED -> {
                settingItems.indexOf(getString(SpeedType.getBySpeedType(settings.speedUnit).longResourceId))
            }
            SettingType.DISTANCE -> {
                settingItems.indexOf(getString(DistanceType.getByDistanceType(settings.distanceUnit).labelResourceId))
            }
            SettingType.PRESSURE -> {
                settingItems.indexOf(getString(PressureType.getByPressureType(settings.pressureUnit).labelResourceId))
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
                    getString(TemperatureType.CELSIUS.nameId),
                    getString(TemperatureType.FAHRENHEIT.nameId),
                    getString(TemperatureType.KELVIN.nameId)
                )
            }
            SettingType.LENGTH -> {
                arrayOf(
                    getString(LengthType.MILLIMETERS.longResourceId),
                    getString(LengthType.INCHES.longResourceId)
                )
            }
            SettingType.SPEED -> {
                arrayOf(
                    getString(SpeedType.METERS_PER_SECOND.longResourceId),
                    getString(SpeedType.KILOMETERS_PER_HOUR.longResourceId),
                    getString(SpeedType.MILES_PER_HOUR.longResourceId),
                    getString(SpeedType.BEAUFORT_WIND_SCALE.longResourceId),
                    getString(SpeedType.KNOTS.longResourceId)
                )
            }
            SettingType.DISTANCE -> {
                arrayOf(
                    getString(DistanceType.KILOMETERS.labelResourceId),
                    getString(DistanceType.MILES.labelResourceId)
                )
            }
            SettingType.PRESSURE -> {
                arrayOf(
                    getString(PressureType.HPA.labelResourceId),
                    getString(PressureType.KPA.labelResourceId),
                    getString(PressureType.MMHG.labelResourceId),
                    getString(PressureType.INHG.labelResourceId)
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