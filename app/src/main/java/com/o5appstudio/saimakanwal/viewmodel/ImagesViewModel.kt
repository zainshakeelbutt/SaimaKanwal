package com.o5appstudio.saimakanwal.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.activity.ComponentActivity
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(private val repository: PoetryRepository) : ViewModel() {
    private val _allImagesList = MutableStateFlow<List<ImagePoetry>>(emptyList())
    val allImagesList: StateFlow<List<ImagePoetry>> get() = _allImagesList
    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getImagesPoetry().collect{
                _allImagesList.value = it
            }
        }
    }

    private val _imageData = MutableStateFlow<ImagePoetry?>(null)
    val imageData: StateFlow<ImagePoetry?> get() = _imageData

    fun getImageData(id: String) {
        viewModelScope.launch {
            val fetchedImage = repository.fetchImage(id)
            _imageData.value = fetchedImage
        }
    }



}