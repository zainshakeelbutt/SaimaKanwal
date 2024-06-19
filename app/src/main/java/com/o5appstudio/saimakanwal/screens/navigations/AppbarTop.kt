package com.o5appstudio.saimakanwal.screens.navigations

import android.app.Activity
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.o5appstudio.saimakanwal.R
import com.o5appstudio.saimakanwal.ui.theme.secondary
import com.o5appstudio.saimakanwal.utils.CustomDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppBarTop(coroutineScope: CoroutineScope, drawerState: DrawerState) {
    val showDialog = rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = secondary,
        navigationIcon = {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Icon(Icons.Default.Menu, contentDescription = "menu", tint = Color.White)
            }
        },
        title = {
            Text(
                text = "صائمہ کنول شاعری",
                fontFamily = FontFamily(Font(R.font.noto_font)),
                color = Color.White

            ) },

        actions = {
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(painter = painterResource(id = R.drawable.power_off), contentDescription = "quit", tint = Color.White)
            }
        }
    )

    if (showDialog.value) {
        CustomDialog(
            onDismiss = { showDialog.value = false },
            onConfirm = { showDialog.value = false
                (context as? Activity)?.finishAffinity()
                        },
            onCancel = { showDialog.value = false }
        )
    }
}