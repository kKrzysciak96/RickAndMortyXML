package com.example.rickandmortyxml.features.characters.presentation.whole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentCharacterBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment : BaseFragment<CharacterViewModel>(R.layout.fragment_character) {
    private var _binding: FragmentCharacterBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<CharacterViewModel>()
    private val characterAdapter: CharacterAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initViews() {
        super.initViews()
        initRecyclerview()
    }

    private fun initRecyclerview() {
        with(binding.characterRecyclerview) {
            adapter = characterAdapter
            setHasFixedSize(true)
        }
        setOnClickListener()
    }

    override fun initObservers() {
        super.initObservers()
        observeCharacters()
    }

    override fun onIdleState() {
        super.onIdleState()
        binding.characterProgressBar.visibility = View.GONE
    }

    override fun onPendingState() {
        super.onPendingState()
        binding.characterProgressBar.visibility = View.VISIBLE
    }

    private fun observeCharacters() {
        viewModel.characters.observe(this) {
            characterAdapter.setCharacters(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnClickListener() {
        characterAdapter.setOnClickListener {
            viewModel.onCharacterClick(it)
        }

    }


}