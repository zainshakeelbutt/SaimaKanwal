package com.o5appstudio.saimakanwal.navigationsgraphs

sealed class StartScreens (val route : String) {
    data object Splash : StartScreens("splash")
    data object Main : StartScreens("main")
    data object PoetryList : StartScreens("poetryList/{book}")
    data object CategoryList : StartScreens("categoryList/{category}")
    data object SearchCategoryList : StartScreens("searchCategoryList")
    data object SearchBooksList : StartScreens("searchBooksList")
    data object ImagesView : StartScreens("imagesView/{viewImage}")
    data object ImagesScreen: StartScreens("imagesScreen")
    data object VideosScreen: StartScreens("videosScreen")
    data object PoetryDetail : StartScreens("poetryDetail/{title}/{detail}")

}