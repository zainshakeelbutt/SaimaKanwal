package com.o5appstudio.saimakanwal.screens.content

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.Poetry
import com.o5appstudio.saimakanwal.navigationsgraphs.StartScreens
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.utils.TextFieldCustom
import com.o5appstudio.saimakanwal.viewmodel.CategoriesListsViewModel


@Composable
fun CategoriesListScreen(navController: NavHostController, categoryName:String) {
    val categoriesListsViewModel: CategoriesListsViewModel = hiltViewModel()
    val query = rememberSaveable {
        mutableStateOf("")
    }
    val columnState: LazyListState = rememberLazyListState()
    LaunchedEffect(Unit) {
        categoriesListsViewModel.updateCategory(categoryName)
    }
        val categoryDataList : State<List<Poetry>> = categoriesListsViewModel.categoryDataList.collectAsState()
//        Log.d("BooksData",booksDataList.value.toString())

    val filteredList = categoryDataList.value.filter {
        it.title.contains(query.value, ignoreCase = true) ||
                it.detail.contains(query.value)
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        categoryName,
                        color = Color.White ,
                        fontFamily = FontFamily(Font(R.font.noto_font)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        textAlign = TextAlign.End
                    )
                        },
                backgroundColor = secondary,
                navigationIcon = {
                    IconButton(onClick = {
                      navController.popBackStack()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back", tint = Color.White)
                    }
                },
            )
        }
    ){innerPadding->
        Box (
            modifier = Modifier
                .padding(innerPadding)
                .background(color = background)
                .fillMaxSize()
        ){
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ){
                TextFieldCustom(
                    label = "تلاش کریں",
                    hint = "$categoryName میں تلاش کریں",
                    textStateValue = query,
                    keyboardType = KeyboardType.Text,
                    isPassword = false
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box (
                    modifier = Modifier
                        .fillMaxSize()
                ){

                    if(categoryDataList.value.isNotEmpty()){
                        if(query.value ==""){
                            LazyColumn(
                                state = columnState,
                                userScrollEnabled = true
                            ) {
                                items(categoryDataList.value){items->
                                    Box(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                    ) {
                                        PoetryListItem(title = items.title, detail = items.detail){
                                            try {
                                                navController.navigate("${StartScreens.PoetryDetail.route}/${items.title}/${items.detail}"){
                                                    popUpTo(StartScreens.PoetryList.route)
                                                }
                                            }
                                            catch (e:Exception){
                                                Log.d("Saima",e.toString())
                                            }

                                        }
                                    }
                                }
                            }
                        }
                        else{
                            LazyColumn(
                                state = columnState,
                                userScrollEnabled = true
                            ) {
                                items(filteredList){items->
                                    Box(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                    ) {
                                        PoetryListItem(title = items.title, detail = items.detail){
                                            navController.navigate("${StartScreens.PoetryDetail.route}/${items.title}/${items.detail}"){
                                                popUpTo(StartScreens.PoetryList.route)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else{
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = secondary)                    }
                }

            }
        }

    }
}
