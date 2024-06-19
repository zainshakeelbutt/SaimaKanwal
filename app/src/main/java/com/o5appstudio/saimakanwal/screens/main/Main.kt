package com.o5appstudio.saimakanwal.screens.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.model.VideosPoetry
import com.o5appstudio.saimakanwal.navigationsgraphs.StartScreens
import com.o5appstudio.saimakanwal.screens.navigations.AppBarTop
import com.o5appstudio.saimakanwal.screens.navigations.DrawerContent
import com.o5appstudio.saimakanwal.ui.theme.primary
import com.o5appstudio.saimakanwal.ui.theme.tertiary
import com.o5appstudio.saimakanwal.utils.BooksCard
import com.o5appstudio.saimakanwal.utils.CategoriesCard
import com.o5appstudio.saimakanwal.utils.Consts
import com.o5appstudio.saimakanwal.utils.Consts.sliderItems
import com.o5appstudio.saimakanwal.utils.CustomDialog
import com.o5appstudio.saimakanwal.utils.ImagesCard
import com.o5appstudio.saimakanwal.utils.PoetryText
import com.o5appstudio.saimakanwal.utils.SliderCard
import com.o5appstudio.saimakanwal.utils.SocialIconsCard
import com.o5appstudio.saimakanwal.viewmodel.AllBooksViewModel
import com.o5appstudio.saimakanwal.viewmodel.CategoriesViewModel
import com.o5appstudio.saimakanwal.viewmodel.ImagesViewModel
import com.o5appstudio.saimakanwal.viewmodel.VideosViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@Composable
fun MainNavScreens(navController: NavHostController) {

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current
    val showDialog = remember { mutableStateOf(false) }

    // Handle the back press
    BackHandler {
        showDialog.value = true
    }

    if (showDialog.value) {
        CustomDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = { showDialog.value = false
                (context as? Activity)?.finishAffinity()
            },
            onCancel = { showDialog.value = false }
        )
    }

//    Log.d("CategoriesData",categoryList.value.toString())
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(coroutineScope = coroutineScope, drawerState = drawerState, navController = navController)
        }
    ) {

        Scaffold(
            topBar = {
                AppBarTop(coroutineScope,drawerState)
            },

        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(color = primary)

            ) {
                MainCompositions(navController)
            }
        }

    }

}

@Composable
fun MainCompositions(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ){
        AutoPlayImageTextSlider()
        Spacer(modifier = Modifier.height(10.dp))
        AllBooksSlider(navController)
        ImagesSlider(navController)
        CategoriesSlider(navController)
        VideosSlider(navController)
        SocialIconsScreen()
    }

}


@Composable
fun AutoPlayImageTextSlider() {
    val pagerState = rememberPagerState()
    val isAutoScrollEnabled = remember { mutableStateOf(true) }

    LaunchedEffect(pagerState) {
        // Auto-scroll every 3 seconds
       if(isAutoScrollEnabled.value){
           while (true) {
               yield() // Allows coroutine cancellation
               delay(3000)
               val nextPage = (pagerState.currentPage + 1) % sliderItems.size
               pagerState.animateScrollToPage(nextPage)
           }
       }

    }

    Box(
        modifier = Modifier
            .height(250.dp)
            .shadow(
                elevation = 1.dp,
                clip = true,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
    ){
        Image(
            painter = painterResource(id = R.drawable.cover),
            contentDescription ="",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                count = sliderItems.size,
                state = pagerState,
                modifier = Modifier.height(180.dp)
            ) { page ->
                SliderCard(sliderItems[page])
            }
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.White,
                modifier = Modifier.padding(8.dp)
            )

        }
    }
}

@Composable
fun CategoriesSlider(navController: NavHostController) {
    val categoryViewModel : CategoriesViewModel = hiltViewModel()
    val categoryList : State<List<String>> = categoryViewModel.categoriesList.collectAsState()
    if (categoryList.value.isNotEmpty()){
        Box(
            modifier = Modifier
                .background(color = primary)
                .padding(10.dp)
        ){

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ){
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(StartScreens.SearchCategoryList.route){
                                    popUpTo(StartScreens.Main.route)
                                }
                            }
                    ) {
                        PoetryText(
                            text = "تلاش کریں",
                            fontSize = 14.sp,
                            textColor = Color.Blue,
                            modifier = Modifier
                        )
                    }
                    PoetryText(text = "تمام شعری اصناف", fontSize = 14.sp, textColor = Color.Black, modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(categoryList.value) {
                        CategoriesCard(categories = it) {
                            navController.navigate("${StartScreens.CategoryList.route}}/${it}"){
                                popUpTo(StartScreens.Main.route)
                            }
                        }
                    }
                }
            }


        }
    }

}

@Composable
fun ImagesSlider(navController: NavHostController) {
    val imageViewModel : ImagesViewModel = hiltViewModel()
    val imagesList : State<List<ImagePoetry>> = imageViewModel.allImagesList.collectAsState()

    val imageListLimit = if(imagesList.value.size > 5){imagesList.value.take(5)} else imagesList.value

    if(imagesList.value.isNotEmpty()){
        Box(
            modifier = Modifier
                .background(color = tertiary)
                .padding(10.dp)
        ){

            Column{
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ){
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(StartScreens.ImagesScreen.route)
                            }
                    ) {
                        PoetryText(
                            text = "مزید دیکھیں",
                            fontSize = 14.sp,
                            textColor = Color.Blue,
                            modifier = Modifier
                        )
                    }
                    PoetryText(text = "تصویری شاعری", fontSize = 14.sp, textColor = Color.Black, modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(imageListLimit) {
                        ImagesCard(imageUrl = it.image) {
                            navController.navigate("${StartScreens.ImagesView.route}/${it.id}")
                        }
                    }
                }
            }


        }
    }

}

@Composable
fun AllBooksSlider(navController: NavHostController) {
    val allBooksViewModel : AllBooksViewModel = hiltViewModel()
    val booksList : State<List<String>> = allBooksViewModel.allBooksList.collectAsState()
    val context = LocalContext.current.applicationContext

    if(booksList.value.isNotEmpty()){
        Box(
            modifier = Modifier
                .background(color = primary)
                .padding(10.dp)
        ){

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ){
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(StartScreens.SearchBooksList.route){
                                    popUpTo(StartScreens.Main.route)
                                }
                            }
                    ) {
                        PoetryText(
                            text = "تلاش کریں",
                            fontSize = 14.sp,
                            textColor = Color.Blue,
                            modifier = Modifier
                        )
                    }
                    PoetryText(
                        text = "تمام کتابیں",
                        fontSize = 14.sp,
                        textColor = Color.Black,
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(booksList.value) {
                        BooksCard(bookName = it) {
//                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            navController.navigate("${StartScreens.PoetryList.route}}/${it}"){
                                popUpTo(StartScreens.Main.route)
                            }
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun VideosSlider(navController: NavHostController) {
    val context = LocalContext.current
    val videosViewModel : VideosViewModel = hiltViewModel()
    val videosList : State<List<VideosPoetry>> = videosViewModel.allVideosList.collectAsState()
    val videosListLimit = if(videosList.value.size > 5){videosList.value.take(5)} else videosList.value

    if(videosList.value.isNotEmpty()){
        Box(
            modifier = Modifier
                .background(color = tertiary)
                .padding(10.dp)
        ){

            Column{
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ){
                    Box(
                        modifier = Modifier
                            .clickable {
                                navController.navigate(StartScreens.VideosScreen.route)
                            }
                    ) {
                        PoetryText(
                            text = "مزید دیکھیں",
                            fontSize = 14.sp,
                            textColor = Color.Blue,
                            modifier = Modifier
                        )
                    }
                    PoetryText(text = "ویڈیوز", fontSize = 14.sp, textColor = Color.Black, modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow {
                    items(videosListLimit) {
                        ImagesCard(imageUrl = it.image) {
                            videosViewModel.openVideo(context,it.link)
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun SocialIconsScreen() {
    val context = LocalContext.current
    val videosViewModel : VideosViewModel = hiltViewModel()
    Box(
        modifier = Modifier
            .background(color = primary)
            .padding(10.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Consts.socialIcons.forEach{
                SocialIconsCard(icon = it.icon) {
//                    Toast.makeText(context, it.link, Toast.LENGTH_SHORT).show()
                    when (it.link) {
                        "facebook" -> {
                            videosViewModel.openFbPage(context,"100855037997331","saimakanwal78")
                        }
                        "twitter" -> {
                            val sAppLink = "twitter://user?screen_name=Kanwaljiii"
                            val sPackage = "com.twitter.android"
                            val sWebLink = "https://twitter.com/Kanwaljiii/"
                            videosViewModel.openLink(context,sAppLink, sPackage, sWebLink)
                        }
                        else -> {
                            videosViewModel.openVideo(context, it.link)
                        }
                    }


                }
            }
        }
    }

}

