package com.example.imagesearchapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagesearchapp.data.local.dao.UnsplashImageDao
import com.example.imagesearchapp.data.local.dao.UnsplashRemoteKeysDao
import com.example.imagesearchapp.model.UnsplashImage
import com.example.imagesearchapp.model.UnsplashRemoteKeys

@Database(
    entities = [UnsplashImage::class, UnsplashRemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class UnsplashDatabase : RoomDatabase() {

    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}