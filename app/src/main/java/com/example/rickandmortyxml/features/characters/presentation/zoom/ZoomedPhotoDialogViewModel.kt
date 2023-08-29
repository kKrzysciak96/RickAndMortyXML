package com.example.rickandmortyxml.features.characters.presentation.zoom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ZoomedPhotoDialogViewModel() : ViewModel() {

    private val _photo by lazy { MutableLiveData<String>() }
    val photo: LiveData<String>
        get() = _photo

    fun onPhotoPassed(photoUrl: String) {
        _photo.value = photoUrl
        Log.d("PHOTO", photoUrl)
    }

}