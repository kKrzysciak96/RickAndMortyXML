package com.example.rickandmortyxml.features.characters.navigation

import com.example.rickandmortyxml.R
import com.example.rickandmortyxml.core.navigation.FragmentNavigator
import com.example.rickandmortyxml.features.characters.presentation.zoom.ZoomedPhotoDialog

class CharacterDetailsNavigatorImpl(private val fragmentNavigator: FragmentNavigator) :
    CharacterDetailsNavigator {
    override fun zoomPhoto(photoUrl: String) {
        val pair = ZoomedPhotoDialog.PHOTO_BUNDLE_KEY to photoUrl
        fragmentNavigator.navigateTo(
            R.id.action_zoom_photo,
            pair
        )
    }

    override fun goBack() {
        fragmentNavigator.goBack()
    }

}


