package com.example.rickandmortyxml.features.locations.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentLocationDetailsBinding
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailsFragment() :
    BaseFragment<LocationDetailsViewModel>(R.layout.fragment_location_details) {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<LocationDetailsViewModel>()

    companion object {
        const val LOCATION_DETAILS_BUNDLE_KEY = "LOCATION_DETAILS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments
            ?.getParcelable<LocationDisplayable>(LOCATION_DETAILS_BUNDLE_KEY)
            ?.let { viewModel.onLocationPassed(it) }
    }

    override fun initObservers() {
        super.initObservers()
        observeLocation()
    }

    private fun observeLocation() {
        viewModel.location.observe(this) {
            binding.locationName.text = it.name
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocationDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}