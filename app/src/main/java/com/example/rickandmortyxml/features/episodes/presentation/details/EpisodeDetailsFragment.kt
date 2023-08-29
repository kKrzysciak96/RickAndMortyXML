package com.example.rickandmortyxml.features.episodes.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentEpisodeDetailsBinding
import com.example.rickandmortyxml.features.characters.presentation.whole.CharacterAdapter
import com.example.rickandmortyxml.features.episodes.presentation.model.EpisodeDisplayable
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class EpisodeDetailsFragment :
    BaseFragment<EpisodeDetailsViewModel>(R.layout.fragment_episode_details) {
    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<EpisodeDetailsViewModel>()
    private val characterAdapter: CharacterAdapter by inject()

    companion object {
        const val EPISODE_DETAILS_BUNDLE_KEY = "EPISODE_DETAILS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments
            ?.getParcelable<EpisodeDisplayable>(EPISODE_DETAILS_BUNDLE_KEY)
            ?.let { viewModel.onEpisodePassed(it) }

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
        observeEpisode()
        observeCharacters()
    }

    private fun observeEpisode() {
        viewModel.episode.observe(this) {
            binding.episodeName.text = it.name
            binding.episodeDescription.text = it.episode
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
        _binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}