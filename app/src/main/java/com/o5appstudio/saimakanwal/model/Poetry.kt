package com.o5appstudio.saimakanwal.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Poetry(
    val id:String = "",
    val title:String = "",
    val detail:String =""
)

data class ImagePoetry(
    val id: String = "",
    val image: String = "",
    val text: String = "",
    val time: String = "",
    val admin: String = "",
)

data class VideosPoetry(
    val id: String = "",
    val image: String = "",
    val link: String = "",
    val title: String = "",
    val date: String = "",
)

data class SocialIcons(
    val title: String,
    val icon : Int,
    val link : String
)
