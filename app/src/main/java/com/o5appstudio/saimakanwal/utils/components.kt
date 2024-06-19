package com.o5appstudio.saimakanwal.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.model.SliderItem
import com.o5appstudio.saimakanwal.model.SocialIcons
import com.o5appstudio.saimakanwal.ui.theme.secondary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun PoetryText(text:String,fontSize:TextUnit, textColor: Color = Color.Black,  modifier: Modifier) {

    Text(
        text = text,
        style = TextStyle(
            fontSize = fontSize,
            color = textColor,
            lineHeight = TextUnit(1.2f, TextUnitType(Long.MIN_VALUE)),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.noto_font)),
        ),
        modifier = modifier
    )

}

@Composable
fun GlideImageWithLoading(
    url: String,
    modifier: Modifier = Modifier
) {
    var bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(url) {
        coroutineScope.launch {
            isLoading.value = true
            bitmap.value = loadImage(context, url)
            isLoading.value = false
        }
    }

    Box(modifier = modifier) {
        if (isLoading.value) {
            CircularProgressIndicator(
                color = secondary,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
            )
        } else {
            bitmap.let {
                it.value?.let { it1 ->
                    Image(
                        bitmap = it1.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

suspend fun loadImage(context: Context, url: String): Bitmap? {
    return withContext(Dispatchers.IO) {
        try {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .submit()
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

@Composable
fun LoadNetworkImage(imageUrl : String, modifier: Modifier) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current.applicationContext)
            .data(imageUrl)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .error(android.R.drawable.stat_notify_error)
            .build()
    )
    Image(
        painter = painter,
        contentDescription = "Network Image",
        contentScale = ContentScale.Crop,
        modifier = modifier

    )
}


@Composable
fun SliderCard(item: SliderItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // Display image

        Text(
            text = item.title,
            style = TextStyle(
                lineHeight = TextUnit(1.2f, TextUnitType(Long.MIN_VALUE)),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.noto_font)),
                fontWeight = FontWeight(1000)

            ),
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Display description
        Text(
            text = item.poet,
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(Font(R.font.noto_font)),
                color = Color.White,
                fontWeight = FontWeight(1000)
                ),
            modifier = Modifier
                .padding(top = 8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun CategoriesCard(categories: String, onClick : () -> Unit) {
    Card(
        shape = RoundedCornerShape(12),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .shadow(elevation = 4.dp, clip = true, shape = RoundedCornerShape(12))
            .clip(shape = RoundedCornerShape(12))
            .clickable { onClick() }


    ) {
        PoetryText(
            text = categories, 20.sp,
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 8.dp)
                .align(Alignment.CenterHorizontally)

        )
    }

}

@Composable
fun BooksCard(bookName:String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(14),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .shadow(elevation = 4.dp, clip = true, shape = RoundedCornerShape(14))
            .clip(shape = RoundedCornerShape(14))
            .clickable { onClick() }


    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ){

            Image(
                painter = painterResource(id = R.drawable.navigation_icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            PoetryText(
                text = bookName, 18.sp,
                modifier = Modifier

            )
        }

    }

}

@Composable
fun ImagesCard(imageUrl: String, onClick: () -> Unit) {

    Box (
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .shadow(elevation = 4.dp, clip = true, shape = RoundedCornerShape(10))
            .clip(shape = RoundedCornerShape(10))
            .clickable { onClick() }
    ){
//        LoadNetworkImage(
//            imageUrl = imageUrl,
//            modifier = Modifier
//                .width(380.dp)
//                .height(200.dp)
//        )
        GlideImageWithLoading(
            url = imageUrl,
            modifier = Modifier
                .width(300.dp)
                .height(160.dp)
        )
    }
    
}

@Composable
fun ImagesListCard(imageUrl: String, onClick: () -> Unit) {

    Box (
        modifier = Modifier
            .padding(10.dp)
            .shadow(elevation = 4.dp, clip = true, shape = RoundedCornerShape(10))
            .clip(shape = RoundedCornerShape(10))
            .clickable { onClick() }
    ){

        GlideImageWithLoading(url = imageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp))

//        LoadNetworkImage(
//            imageUrl = imageUrl,
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//        )
    }

}

@Composable
fun SocialIconsCard(icon:Int, onClick: () -> Unit){
    Box (
        modifier = Modifier
            .size(40.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(25))
            .clickable { onClick() }


    ){
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .shadow(elevation = 2.dp, clip = true, shape = RoundedCornerShape(25))
                .clip(RoundedCornerShape(25))
            )
    }
}


@Composable
fun TextFieldCustom(
    label: String,
    hint: String,
    textStateValue: MutableState<String>,
    keyboardType: KeyboardType,
    isPassword: Boolean
) {
    val isFocused = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = textStateValue.value,
        onValueChange = { textStateValue.value = it },
        label = {
            Text(
                text = label,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.Gray ,
                    textDirection = TextDirection.Rtl,
                    fontFamily = FontFamily(Font(R.font.noto_font))
                ) ,
            )
                },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedLeadingIconColor = Color.Black,
            unfocusedLeadingIconColor = Color.Gray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        textStyle = TextStyle(
            textAlign = TextAlign.End,
            fontFamily = FontFamily(Font(R.font.noto_font))
        ),
        placeholder = {
            Text(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.LightGray ,
                    textDirection = TextDirection.Rtl,
                    fontFamily = FontFamily(Font(R.font.noto_font))
                ) ,
            )
        },
        leadingIcon = {
            IconButton(onClick = {
                if(textStateValue.value != ""){
                    textStateValue.value = ""
                } else {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }

            }) {
                Icon(Icons.Outlined.Clear, contentDescription = "")
            }

        },
        trailingIcon = {
            Icon(Icons.Outlined.Search, contentDescription = "")
        },

        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(
            onGo = {
                focusManager.clearFocus()
                keyboardController?.hide()
            },
            onDone = {
                focusManager.clearFocus()
                keyboardController?.hide()
            }
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .border(
                width = 2.dp,
                shape = RoundedCornerShape(8.dp),
                color = if (isFocused.value) secondary else Color.LightGray
            )
            .onFocusChanged {
                isFocused.value = it.isFocused
            }

    )
}

@Composable
fun CustomDialog(onDismiss: () -> Unit, onConfirm: () -> Unit, onCancel: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Exit")
        },
        text = {
            Text("Do You Want to Close the App?")
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onCancel) {
                Text("Cancel")
            }
        }
    )
}