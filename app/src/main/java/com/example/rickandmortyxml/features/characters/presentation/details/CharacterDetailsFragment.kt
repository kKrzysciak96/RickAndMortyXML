package com.example.rickandmortyxml.features.characters.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.base.BaseFragment
import com.example.rickandmortyxml.databinding.FragmentCharacterDetailsBinding
import com.example.rickandmortyxml.features.characters.presentation.model.CharacterDisplayable
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterDetailsFragment :
    BaseFragment<CharacterDetailsViewModel>(R.layout.fragment_character_details) {
    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is null" }
    override val viewModel by viewModel<CharacterDetailsViewModel>()

    companion object {
        const val CHARACTER_DETAILS_BUNDLE_KEY = "CHARACTER_DETAILS_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments
            ?.getParcelable<CharacterDisplayable>(CHARACTER_DETAILS_BUNDLE_KEY)
            ?.let { viewModel.onCharacterPassed(it) }
    }

    override fun initObservers() {
        super.initObservers()
        observeCharacter()
    }

    private fun observeCharacter() {
        viewModel.character.observe(this) { character ->
            binding.run {
                Glide.with(root)
                    .load(character.image)
                    .into(characterImage)
                characterNameContent.text = character.name
                characterSpeciesContent.text = character.species
                characterStatusContent.text = character.status
                characterGenderContent.text = character.gender
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnPhotoClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setOnPhotoClickListener() {
        binding.characterImage.setOnClickListener {
            viewModel.zoomPhotoOnClick()
        }
    }


}