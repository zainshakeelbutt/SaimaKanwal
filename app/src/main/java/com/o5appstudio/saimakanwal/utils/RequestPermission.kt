package com.o5appstudio.saimakanwal.utils

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun RequestPermissions(onGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionState = remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            permissionState.value = isGranted
            if (isGranted) onGranted()
        }
    )

    LaunchedEffect(Unit) {
        when {
            ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED -> {
                permissionState.value = true
                onGranted()
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.INTERNET)
            }
        }
    }
}