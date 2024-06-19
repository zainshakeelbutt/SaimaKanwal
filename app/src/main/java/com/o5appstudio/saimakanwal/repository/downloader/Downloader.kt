package com.o5appstudio.saimakanwal.repository.downloader

interface Downloader {
    fun downloadFile(url: String): Long
}