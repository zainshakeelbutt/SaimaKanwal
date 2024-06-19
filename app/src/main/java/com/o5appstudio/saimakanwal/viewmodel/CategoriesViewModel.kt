package com.o5appstudio.saimakanwal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.saimakanwal.model.Poetry
import com.o5appstudio.saimakanwal.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel@Inject constructor(private val repository: PoetryRepository) : ViewModel(){

    private val _categoriesList = MutableStateFlow<List<String>>(emptyList())
    val categoriesList: StateFlow<List<String>> get() = _categoriesList

    init {
        viewModelScope.launch {
            repository.getAllCategories().collect{
                _categoriesList.value = it
            }
        }
    }


}