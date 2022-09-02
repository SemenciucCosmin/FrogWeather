package com.example.frogweather.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frogweather.R
import com.example.frogweather.data.utils.DAY_TYPE_ARGUMENT_KEY
import com.example.frogweather.databinding.FragmentOneDayBinding
import com.example.frogweather.ui.adapters.OneDayItemViewAdapter
import com.example.frogweather.ui.application.FrogWeatherApplication
import com.example.frogweather.ui.models.NetworkViewModel
import com.example.frogweather.ui.models.SettingsViewModel
import com.google.android.material.snackbar.Snackbar

class OneDayFragment : Fragment() {
    private lateinit var binding: FragmentOneDayBinding
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
        binding = FragmentOneDayBinding.inflate(inflater, container, false)

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.line_divider)?.let { itemDecorator.setDrawable(it) }

        val oneDayAdapter = OneDayItemViewAdapter()
        binding.oneDayRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = oneDayAdapter
            setHasFixedSize(true)
            addItemDecoration(itemDecorator)
        }

        val dayType = arguments?.getInt(DAY_TYPE_ARGUMENT_KEY)

        settingsViewModel.getSettingsAndLocation().observe(viewLifecycleOwner) { pair ->
            networkViewModel.requestUiState(pair.second.latitude, pair.second.longitude, pair.first, dayType)
        }

        networkViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            if (uiState.errorMessageOneDay == null) {
                if (uiState.errorMessageTenDays == null) {
                    oneDayAdapter.submitList(uiState.oneDayItems)
                } else {
                    showErrorSnackBar(uiState.errorMessageTenDays)
                }
            } else {
                showErrorSnackBar(uiState.errorMessageOneDay)
            }
        }

        return binding.root
    }

    private fun showErrorSnackBar(message: String) {
        val snackBar = Snackbar.make(requireActivity().window.decorView.rootView, message, Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view
        val snackBarTextView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        val params = snackBarView.layoutParams as FrameLayout.LayoutParams
        params.setMargins(params.leftMargin + 150, params.topMargin, params.rightMargin + 150, params.bottomMargin + 200)
        snackBarView.layoutParams = params
        snackBarView.background = ContextCompat.getDrawable(requireContext(), R.drawable.snackbar)
        snackBar.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        snackBarTextView.textSize = 15F
        snackBarTextView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        snackBar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        snackBar.setAction("X") {
            snackBar.dismiss()
        }
        snackBar.show()
    }

    companion object {
        fun newInstance(dayType: Int): Fragment {
            return OneDayFragment().apply {
                arguments = Bundle().apply {
                    putInt(DAY_TYPE_ARGUMENT_KEY, dayType)
                }
            }
        }
    }
}
