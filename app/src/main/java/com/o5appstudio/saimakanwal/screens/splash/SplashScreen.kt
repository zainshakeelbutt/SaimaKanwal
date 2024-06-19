package com.o5appstudio.saimakanwal.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.navigationsgraphs.StartScreens
import com.o5appstudio.saimakanwal.ui.theme.background
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(StartScreens.Main.route){
            popUpTo(0)
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = background)
    ){
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .size(400.dp),
            painter = painterResource(id = R.drawable.splash_logo), contentDescription = "splashLogo")


    }

}