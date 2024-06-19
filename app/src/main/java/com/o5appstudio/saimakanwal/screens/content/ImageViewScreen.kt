package com.o5appstudio.saimakanwal.screens.content

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.repository.downloader.AndroidDownloader
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.utils.GlideImageWithLoading
import com.o5appstudio.saimakanwal.utils.LoadNetworkImage
import com.o5appstudio.saimakanwal.utils.PoetryText
import com.o5appstudio.saimakanwal.viewmodel.ImagesViewModel


@Composable
fun ImageViewScreen(navController: NavHostController, id:String){
    val context = LocalContext.current
    val downloader = AndroidDownloader(context)
    val imageViewModel : ImagesViewModel = hiltViewModel()

    val storagePermission = Manifest.permission.WRITE_EXTERNAL_STORAGE
    val isStoragePermissionGranted = remember {
        mutableStateOf(checkPermissionFor(context,storagePermission))
    }
    
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { map->
        val isGranted = map.values.reduce{acc, b -> acc && b}
        if(isGranted){
            Log.d("TAG","Granted")
            isStoragePermissionGranted.value = true
        }
    }

    LaunchedEffect(Unit) {
        imageViewModel.getImageData(id)
    }
    val imageData: State<ImagePoetry?> = imageViewModel.imageData.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "تصویری شاعری",
                        color = Color.White ,
                        fontFamily = FontFamily(Font(R.font.noto_font)),
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        overflow = TextOverflow.Ellipsis
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
                actions = {
                    IconButton(onClick = {
                         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                             Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show()
                             imageData.value?.let { downloader.downloadFile(it.image) }
                        } else {
                             if(!isStoragePermissionGranted.value){
                                 launcher.launch(storagePermissions)
                             } else {
                                 Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show()
                                 imageData.value?.let { downloader.downloadFile(it.image) }
                             }

                        }

                        Log.d("TAG",isStoragePermissionGranted.value.toString())

                    }) {
                        Icon(painter = painterResource(id = R.drawable.download)  , contentDescription = "download", tint = Color.White)
                    }

                }
            )
        }
    ){innerPadding->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = background)
                .fillMaxSize()
        ){
            imageData.value?.let {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ){
                    ImageViewCard(imageUrl = it.image)
                    Spacer(modifier = Modifier.height(20.dp))
                    PoetryText(text = it.text, fontSize = 20.sp, modifier = Modifier)
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }?: run {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = secondary)
            }

        }
    }
}

fun checkPermissionFor(context: Context, permission:String) = ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED


@Composable
fun ImageViewCard(imageUrl: String) {

    Box (
        modifier = Modifier
            .padding(10.dp)
    ){
//        LoadNetworkImage(
//            imageUrl = imageUrl,
//            modifier = Modifier
//                .fillMaxWidth()
//        )

        GlideImageWithLoading(
            url = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}
val storagePermissions = arrayOf(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
)