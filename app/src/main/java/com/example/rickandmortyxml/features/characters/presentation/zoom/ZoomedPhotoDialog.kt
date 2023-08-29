package com.example.rickandmortyxml.features.characters.presentation.zoom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.databinding.FragmentDialogZoomedPhotoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ZoomedPhotoDialog : DialogFragment(R.layout.fragment_dialog_zoomed_photo) {

    private var _binding: FragmentDialogZoomedPhotoBinding? = null
    private val binding
        get() = checkNotNull(_binding) { "Binding is not initialized" }
    val viewModel by viewModel<ZoomedPhotoDialogViewModel>()

    companion object {
        const val PHOTO_BUNDLE_KEY = "PHOTO_BUNDLE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        arguments
            ?.getString(PHOTO_BUNDLE_KEY)
            ?.let { viewModel.onPhotoPassed(it) }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogZoomedPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initObservers() {
        observeCharacters()
    }

    private fun observeCharacters() {
        viewModel.photo.observe(this) {
            setPhoto(it)
        }
    }

    private fun setPhoto(photoUrl: String) {
        Glide.with(binding.root).load(photoUrl).into(binding.photoView)
    }
}