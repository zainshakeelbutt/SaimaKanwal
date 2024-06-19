package com.o5appstudio.saimakanwal.screens.navigations

import com.o5appstudio.saimakanwal.R

sealed class DrawerItems(val route:String, val icon:Int, val title:String) {

    data object Intro : DrawerItems("intro",  R.drawable.intro, "تعارف")
    data object CatSearch : DrawerItems("searchCategoryList",  R.drawable.ic_search_n, "تمام اصناف میں تلاش کریں")
    data object BooksSearch : DrawerItems("searchBooksList",  R.drawable.ic_search_n, "تمام کتابوں میں تلاش کریں")

}