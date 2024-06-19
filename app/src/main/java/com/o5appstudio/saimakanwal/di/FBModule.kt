package com.o5appstudio.saimakanwal.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FBModule {

    @Provides
    @Singleton
    fun providesDatabaseRef() : DatabaseReference{
        return FirebaseDatabase.getInstance().reference
    }

}