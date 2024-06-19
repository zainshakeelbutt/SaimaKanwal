package com.o5appstudio.saimakanwal.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.model.Poetry
import com.o5appstudio.saimakanwal.model.VideosPoetry
import com.o5appstudio.saimakanwal.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(private val repository: PoetryRepository) : ViewModel() {
    private val _allVideosList = MutableStateFlow<List<VideosPoetry>>(emptyList())
    val allVideosList: StateFlow<List<VideosPoetry>> get() = _allVideosList
    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getVideosPoetry().collect{
                _allVideosList.value = it
            }
        }
    }

    fun openVideo(context: Context, url:String){
        viewModelScope.launch {
            repository.openExternalLink(context, url)
        }
    }

    fun openLink(context: Context,sAppLink: String?, sPackage: String?, sWebLink: String?){
        repository.openLink(context,sAppLink,sPackage,sWebLink)
    }
    fun openFbPage(context: Context, pageId: String, pageName: String){
        repository.ourFbPage(context, pageId , pageName)
    }

    fun shareApp(context: Context, text: String){
        repository.shareText(context, text)
    }

}