package com.o5appstudio.saimakanwal.viewmodel

import androidx.compose.runtime.mutableStateOf
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
class CategoriesListsViewModel@Inject constructor(private val repository: PoetryRepository) : ViewModel(){

    private val _categoryDataList = MutableStateFlow<List<Poetry>>(emptyList())
    val categoryDataList: StateFlow<List<Poetry>> get() = _categoryDataList
    var categoryName = mutableStateOf("")

    fun updateCategory(newString: String) {
        categoryName.value = newString

        viewModelScope.launch {
            repository.getCategoriesData(categoryName.value).collect{
                _categoryDataList.value = it

            }
        }

    }

    fun getAllCategoriesData(){
        viewModelScope.launch {
            repository.getAllCategoriesData().collect{
                _categoryDataList.value = it
            }
        }
    }


//    init {
//        viewModelScope.launch {
//            repository.getBooksData(bookName.value).collect{
//                _booksDataList.value = it
//            }
//        }
//    }


}