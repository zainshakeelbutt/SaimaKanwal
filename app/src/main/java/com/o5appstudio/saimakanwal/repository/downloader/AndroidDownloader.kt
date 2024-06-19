package com.o5appstudio.saimakanwal.repository.downloader

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.core.net.toUri

class AndroidDownloader(
    private val context: Context
): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpeg")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("image.jpg")
            .addRequestHeader("Authorization", "Bearer <token>")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "image.jpg")
            .setAllowedOverMetered(true) // Allow downloading over mobile data
            .setAllowedOverRoaming(true)
        return downloadManager.enqueue(request)
    }
}