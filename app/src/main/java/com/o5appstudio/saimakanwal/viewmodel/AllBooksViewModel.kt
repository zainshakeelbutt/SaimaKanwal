package com.o5appstudio.saimakanwal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.o5appstudio.saimakanwal.model.Poetry
import com.o5appstudio.saimakanwal.repository.PoetryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllBooksViewModel @Inject constructor(private val repository: PoetryRepository) : ViewModel() {
    private val _allBooksList = MutableStateFlow<List<String>>(emptyList())
    val allBooksList: StateFlow<List<String>> get() = _allBooksList
    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllBooks().collect{
                _allBooksList.value = it
            }
        }
    }

}