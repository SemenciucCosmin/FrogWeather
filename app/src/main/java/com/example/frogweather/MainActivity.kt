package com.example.frogweather

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.frogweather.databinding.ActivityMainBinding
import com.example.frogweather.data.MyLocation
import com.example.frogweather.data.Sys
import com.example.frogweather.ui.FrogWeatherApplication
import com.example.frogweather.ui.SettingsViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModel.SettingsViewModelFactory(
            (application as FrogWeatherApplication)
        )
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                updateLocation()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED -> {
                updateLocation()
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    showRequestPermissionDialog()
                }
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    private fun showRequestPermissionDialog() {
        val alertDialog = AlertDialog.Builder(this, R.style.SettingsDialogTheme)
        alertDialog.setTitle(getString(R.string.lbl_location_settings_dialog_title))
        alertDialog.setMessage(getString(R.string.msg_location_permisson_dialog))
        alertDialog.setNegativeButton(getString(R.string.lbl_cancel_button)) { _, _ -> }
        alertDialog.setPositiveButton(getString(R.string.lbl_settings_button)) { _, _ ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.fromParts("package", packageName, null)
            startActivity(intent)
        }
        alertDialog.create().show()
    }

    private fun updateLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token
                override fun isCancellationRequested() = false
            }).addOnSuccessListener { location: Location? ->
                if (location != null) {
                    settingsViewModel.saveLocation(MyLocation(System.currentTimeMillis(), location.latitude, location.longitude), this)
                }
            }
        }
    }
}