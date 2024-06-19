package com.o5appstudio.saimakanwal.screens.navigations

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.ui.theme.tertiary
import com.o5appstudio.saimakanwal.utils.Consts
import com.o5appstudio.saimakanwal.utils.CustomDialog
import com.o5appstudio.saimakanwal.viewmodel.VideosViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(coroutineScope: CoroutineScope, drawerState: DrawerState, navController: NavHostController) {
    val context = LocalContext.current
    val videosViewModel : VideosViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        CustomDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = { showDialog.value = false
                (context as? Activity)?.finishAffinity()
            },
            onCancel = { showDialog.value = false }
        )
    }
    ModalDrawerSheet(
        drawerContainerColor = Color.White,
        modifier = Modifier

            .fillMaxWidth(.6f)
            .fillMaxHeight(1f)
            .verticalScroll(state = scrollState, enabled = true)

    ) {
        Box(
            modifier = Modifier
                .background(color = tertiary)
                .fillMaxWidth()
                .height(200.dp)
        ) {

            Image(

                painter = painterResource(id = R.drawable.navigation_icon), contentDescription = "nav",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(130.dp)

            )

        }
        Divider()
        val items = listOf(
            DrawerItems.Intro,
            DrawerItems.BooksSearch,
            DrawerItems.CatSearch,
        )

//        Column {
//            DrawerNavItem(
//                title = DrawerItems.Intro.title,
//                icon = DrawerItems.Intro.icon,
//                coroutineScope = coroutineScope,
//                drawerState = drawerState,
//                navController = navController,
//                onClick = {
//                    coroutineScope.launch {
//                        drawerState.close()
//                    }
//                    navController.navigate(DrawerItems.Intro.route) {
//                        popUpTo(navController.graph.startDestinationId) {
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }

        Column{
            items.forEach { item ->
                DrawerNavItem(title = item.title, icon = item.icon, urduFont = true) {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
//                NavigationDrawerItem(
//                    label = { Text(text = item.title, color = Color.Black, fontSize = 16.sp, fontFamily = FontFamily(
//                        Font(R.font.noto_font)
//                    )) },
//                    icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(25.dp)) },
//                    selected = false,
//                    onClick = {
//                        coroutineScope.launch {
//                            drawerState.close()
//                        }
//                        navController.navigate(item.route) {
//                            popUpTo(navController.graph.startDestinationId) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//
//
//                    })
            }
            Divider(modifier = Modifier.padding(vertical = 10.dp))

            Text(text = "Social Accounts", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(start = 20.dp, bottom = 10.dp))
            Consts.socialIcons.forEach{
                DrawerNavItem(
                    title = it.title,
                    icon = it.icon,
                ) {
                    coroutineScope.launch {
                        drawerState.close()
                    }
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

            Divider(modifier = Modifier.padding(vertical = 10.dp))

            Text(text = "Options", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.padding(start = 20.dp, bottom = 10.dp))
            Consts.optionsIcons.forEach{
                DrawerNavItem(
                    title = it.title,
                    icon = it.icon,
                ) {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    when (it.title) {
                        "Share App" -> {
                            videosViewModel.shareApp(context,it.link)
                        }
                        "Quit" -> {
                            showDialog.value = true
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

@Composable
fun DrawerNavItem(title:String, icon:Int,urduFont : Boolean = false, onClick : ()-> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 20.dp, bottom = 5.dp, top = 5.dp)

        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = title,
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 10.dp)
            )
            Text(text = title, color = Color.Black, fontSize = 16.sp,
                fontFamily = if(urduFont){ FontFamily(Font(R.font.noto_font))
                } else {
                    FontFamily.Default }
            )
        }
    }
}