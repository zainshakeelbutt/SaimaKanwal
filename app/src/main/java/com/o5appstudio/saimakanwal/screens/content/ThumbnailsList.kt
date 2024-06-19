package com.o5appstudio.saimakanwal.screens.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.navigationsgraphs.StartScreens
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.utils.ImagesListCard
import com.o5appstudio.saimakanwal.viewmodel.ImagesViewModel


@Composable
fun ImagesListScreen(navController: NavHostController) {
    val context = LocalContext.current.applicationContext
    val imageViewModel : ImagesViewModel = hiltViewModel()
    val imagesList : State<List<ImagePoetry>> = imageViewModel.allImagesList.collectAsState()
    val columnState: LazyListState = rememberLazyListState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "تصویری شاعری",
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
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                ){

                    if(imagesList.value.isNotEmpty()){
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                        ){
                            LazyColumn (state = columnState){
                                items(imagesList.value) {
                                    ImagesListCard(imageUrl = it.image) {
                                        navController.navigate("${StartScreens.ImagesView.route}/${it.id}")
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

