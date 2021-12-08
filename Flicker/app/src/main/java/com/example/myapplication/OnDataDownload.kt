package com.example.myapplication

import com.example.myapplication.DownloadStatus

interface OnDataDownload {
    fun onDataDownloaded(result: String,downloadStatus: DownloadStatus)

}