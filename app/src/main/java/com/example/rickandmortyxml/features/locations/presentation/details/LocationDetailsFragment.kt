package com.example.rickandmortyxml.features.locations.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentLocationDetailsBinding
import com.example.rickandmortyxml.features.characters.presentation.whole.CharacterAdapter
import com.example.rickandmortyxml.features.locations.presentation.model.LocationDisplayable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LocationDetailsFragment :
    BaseFragment<LocationDetailsViewModel>(R.layout.fragment_location_details) {
    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<LocationDetailsViewModel>()
    private val characterAdapter: CharacterAdapter by inject()

    companion object {
        const val LOCATION_DETAILS_BUNDLE_KEY = "LOCATION_DETAILS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments
            ?.getParcelable<LocationDisplayable>(LOCATION_DETAILS_BUNDLE_KEY)
            ?.let { viewModel.onLocationPassed(it) }
    }

    override fun initViews() {
        super.initViews()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        with(binding.castRecyclerView) {
            adapter = characterAdapter
            setHasFixedSize(true)
        }
    }

    override fun initObservers() {
        super.initObservers()
        observeLocation()
        observeCharacters()
    }

    private fun observeLocation() {
        viewModel.location.observe(this) {
            binding.locationName.text = it.name
            binding.locationType.text = it.type
        }
    }

    private fun observeCharacters() {
        viewModel.characters.observe(this) {
            characterAdapter.setCharacters(it)
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