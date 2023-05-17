package com.example.rickandmortyxml.features.episodes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentEpisodeBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment() : BaseFragment<EpisodeViewModel>(R.layout.fragment_episode) {
    private var _binding: FragmentEpisodeBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<EpisodeViewModel>()
    private val episodeAdapter: EpisodeAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        with(binding.episodeRecyclerview) {
            adapter = episodeAdapter
        }
    }

    override fun initObservers() {
        super.initObservers()
        observeEpisodes()
    }

    override fun onIdleState() {
        super.onIdleState()
    }

    override fun onPendingState() {
        super.onPendingState()
    }

    private fun observeEpisodes() {
        viewModel.episodes.observe(this) {
            episodeAdapter.setEpisodes(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}