package com.o5appstudio.saimakanwal.viewmodel

import android.content.Context
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
class BooksListsViewModel@Inject constructor(private val repository: PoetryRepository) : ViewModel(){

    private val _booksDataList = MutableStateFlow<List<Poetry>>(emptyList())
    val booksDataList: StateFlow<List<Poetry>> get() = _booksDataList
    var bookName = mutableStateOf("")

    fun updateBook(newString: String) {
        bookName.value = newString

        viewModelScope.launch {
            repository.getBooksData(bookName.value).collect{
                _booksDataList.value = it

            }
        }

    }

    fun getAllBooksData(){
        viewModelScope.launch {
            repository.getAllBooksData().collect{
                _booksDataList.value = it
            }
        }
    }


    fun sharePoetry(context: Context, text: String){
        repository.shareText(context, text)
    }

//    init {
//        viewModelScope.launch {
//            repository.getBooksData(bookName.value).collect{
//                _booksDataList.value = it
//            }
//        }
//    }


}