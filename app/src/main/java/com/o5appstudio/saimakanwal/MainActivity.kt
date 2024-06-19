package com.o5appstudio.saimakanwal

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.o5appstudio.saimakanwal.ui.theme.SaimaKanwalTheme
import com.o5appstudio.saimakanwal.navigationsgraphs.StartScreens
import com.o5appstudio.saimakanwal.screens.IntroScreen
import com.o5appstudio.saimakanwal.screens.content.CategoriesListScreen
import com.o5appstudio.saimakanwal.screens.content.ImageViewScreen
import com.o5appstudio.saimakanwal.screens.content.ImagesListScreen
import com.o5appstudio.saimakanwal.screens.content.PoetryDetailScreen
import com.o5appstudio.saimakanwal.screens.content.PoetryListScreen
import com.o5appstudio.saimakanwal.screens.content.SearchBooksListScreen
import com.o5appstudio.saimakanwal.screens.content.SearchCategoriesListScreen
import com.o5appstudio.saimakanwal.screens.content.VideosListScreen
import com.o5appstudio.saimakanwal.screens.main.MainNavScreens
import com.o5appstudio.saimakanwal.screens.navigations.DrawerItems
import com.o5appstudio.saimakanwal.screens.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SaimaKanwalTheme {

                StartNavScreens()
            }
        }
    }
}



@Composable
fun StartNavScreens() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = StartScreens.Splash.route) {
        composable(StartScreens.Splash.route){
            SplashScreen(navController)
        }
        composable(StartScreens.Main.route){
            MainNavScreens(navController)
        }
        composable(StartScreens.SearchCategoryList.route){
            SearchCategoriesListScreen(navController)
        }
        composable(StartScreens.SearchBooksList.route){
            SearchBooksListScreen(navController)
        }
        composable(StartScreens.ImagesScreen.route,){
            ImagesListScreen(navController)
        }
        composable(StartScreens.VideosScreen.route,){
            VideosListScreen(navController)
        }

        composable(DrawerItems.Intro.route,){
            IntroScreen(navController)
        }


        composable(
            "${StartScreens.PoetryList.route}/{book}",
            arguments = listOf(
                navArgument("book"){ type = NavType.StringType }
            )
        ){
            val bookName = it.arguments?.getString("book") ?: ""
            PoetryListScreen(navController, bookName)
        }
        composable(
            "${StartScreens.ImagesView.route}/{viewImage}",
            arguments = listOf(
                navArgument("viewImage"){ type = NavType.StringType }
            )
        ){
            val imageUrl = it.arguments?.getString("viewImage") ?: ""
            ImageViewScreen(navController, imageUrl)
        }

        composable(
            "${StartScreens.CategoryList.route}/{category}",
            arguments = listOf(
                navArgument("category"){ type = NavType.StringType }
            )
        ){
            val category = it.arguments?.getString("category") ?: ""
            CategoriesListScreen(navController, category)
        }

        composable(
            "${StartScreens.PoetryDetail.route}/{title}/{detail}",
            arguments = listOf(

                navArgument("title"){
                    type = NavType.StringType
                },
                navArgument("detail"){
                    type = NavType.StringType
                },

            )
        ){
            val title = it.arguments?.getString("title") ?: ""
            val detail = it.arguments?.getString("detail") ?: ""
            PoetryDetailScreen(navController, title, detail)
        }



    }

}
