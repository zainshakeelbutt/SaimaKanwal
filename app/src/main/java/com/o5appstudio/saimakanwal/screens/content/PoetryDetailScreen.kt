package com.o5appstudio.saimakanwal.screens.content

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.ui.theme.background
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.viewmodel.BooksListsViewModel

@Composable
fun PoetryDetailScreen(navController: NavHostController, title:String, detail:String){
    val context = LocalContext.current
    val booksListsViewModel : BooksListsViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        title,
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
                        booksListsViewModel.sharePoetry(context,detail)
                    }) {
                        Icon(imageVector = Icons.Default.Share  , contentDescription = "Share", tint = Color.White)
                    }
                    IconButton(onClick = {
                        copyTextToClipboard(context, detail)
                        Toast.makeText(context, "Text copied", Toast.LENGTH_SHORT).show()

                    }) {
                        Icon(painter = painterResource(id = R.drawable.copy_text)  , contentDescription = "Copy", tint = Color.White)
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
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState, enabled = true)
            ) {
                SelectionContainer {
                    Text(detail,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.noto_font)),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                    )
                }



            }

        }
    }
}

private fun copyTextToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
}