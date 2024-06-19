package com.o5appstudio.saimakanwal.repository

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.collection.BuildConfig
import com.o5appstudio.saimakanwal.model.ImagePoetry
import com.o5appstudio.saimakanwal.model.Poetry
import com.o5appstudio.saimakanwal.model.VideosPoetry
import com.o5appstudio.saimakanwal.utils.Consts
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PoetryRepository @Inject constructor(private val databaseReference: DatabaseReference) {

    suspend fun fetchImage(id: String): ImagePoetry? {
        return try {
            val snapshot =
                databaseReference.child(Consts.SaimaKanwalApp).child(Consts.StatusPoetry).child(id)
                    .get().await()
            Log.d("ppp", snapshot.getValue(ImagePoetry::class.java).toString())
            snapshot.getValue(ImagePoetry::class.java)

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    fun getCategoriesData(categoryName: String): Flow<List<Poetry>> = callbackFlow {
        val catReference =
            databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllCategories)
                .child(categoryName)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoriesList = mutableListOf<Poetry>()
                for (dataSnapshot in snapshot.children) {
                    val category = dataSnapshot.getValue(Poetry::class.java)
                    category?.let { categoriesList.add(it) }
                }
                trySend(categoriesList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        catReference.addValueEventListener(listener)
        awaitClose { catReference.removeEventListener(listener) }
    }

    fun getAllCategoriesData(): Flow<List<Poetry>> = callbackFlow {
        val catReference =
            databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllCategories)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoriesList = mutableListOf<Poetry>()
                for (dataSnapshot in snapshot.children) {
                    for (dSnapshot in dataSnapshot.children) {
                        val category = dSnapshot.getValue(Poetry::class.java)
                        category?.let { categoriesList.add(it) }
                    }

                }
                trySend(categoriesList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        catReference.addValueEventListener(listener)
        awaitClose { catReference.removeEventListener(listener) }
    }

    fun getBooksData(bookName: String): Flow<List<Poetry>> = callbackFlow {
        val bookReference =
            databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllBooks).child(bookName)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val bookDataList = mutableListOf<Poetry>()
                for (dataSnapshot in snapshot.children) {
                    val dataList = dataSnapshot.getValue(Poetry::class.java)
                    dataList?.let { bookDataList.add(it) }
                }
                trySend(bookDataList).isSuccess
//                Log.d("BooksData",bookDataList.toString())

            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        bookReference.addValueEventListener(listener)
        awaitClose { bookReference.removeEventListener(listener) }
    }

    fun getAllBooksData(): Flow<List<Poetry>> = callbackFlow {
        val catReference = databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllBooks)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val booksList = mutableListOf<Poetry>()
                for (dataSnapshot in snapshot.children) {
                    for (dSnapshot in dataSnapshot.children) {
                        val books = dSnapshot.getValue(Poetry::class.java)
                        books?.let { booksList.add(it) }
                    }

                }
                trySend(booksList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        catReference.addValueEventListener(listener)
        awaitClose { catReference.removeEventListener(listener) }
    }

    fun getAllBooks(): Flow<List<String>> = callbackFlow {
        val allBooksRef = databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllBooks)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allBooksList = mutableListOf<String>()
                for (dataSnapshot in snapshot.children) {
                    val booksList = dataSnapshot.key
                    booksList?.let { allBooksList.add(it) }
                }
                trySend(allBooksList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        allBooksRef.addValueEventListener(listener)
        awaitClose { allBooksRef.removeEventListener(listener) }
    }

    fun getAllCategories(): Flow<List<String>> = callbackFlow {
        val allCatsRef = databaseReference.child(Consts.SaimaKanwalApp).child(Consts.AllCategories)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allCatsList = mutableListOf<String>()
                for (dataSnapshot in snapshot.children) {
                    val catsList = dataSnapshot.key
                    catsList?.let { allCatsList.add(it) }
                }
                trySend(allCatsList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        allCatsRef.addValueEventListener(listener)
        awaitClose { allCatsRef.removeEventListener(listener) }
    }

    fun getImagesPoetry(): Flow<List<ImagePoetry>> = callbackFlow {
        val imagesRef = databaseReference.child(Consts.SaimaKanwalApp).child(Consts.StatusPoetry)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allImagesList = mutableListOf<ImagePoetry>()
                for (dataSnapshot in snapshot.children) {
                    val imagesList = dataSnapshot.getValue(ImagePoetry::class.java)
                    imagesList?.let { allImagesList.add(it) }
                }
                trySend(allImagesList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        imagesRef.addValueEventListener(listener)
        awaitClose { imagesRef.removeEventListener(listener) }
    }

    fun getVideosPoetry(): Flow<List<VideosPoetry>> = callbackFlow {
        val videosRef = databaseReference.child(Consts.SaimaKanwalApp).child(Consts.Videos)
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val allVideosList = mutableListOf<VideosPoetry>()
                for (dataSnapshot in snapshot.children) {
                    val videosList = dataSnapshot.getValue(VideosPoetry::class.java)
                    videosList?.let { allVideosList.add(it) }
                }
                trySend(allVideosList).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }
        videosRef.addValueEventListener(listener)
        awaitClose { videosRef.removeEventListener(listener) }
    }

    fun openExternalLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        val activities = context.packageManager.queryIntentActivities(intent, 0)
        if (activities.isNotEmpty()) {
            context.startActivity(intent)
        } else {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(webIntent)
            // Handle the case where no app can handle the URL
            // You might want to show a message to the user
        }
    }

    fun shareText(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val chooser = Intent.createChooser(intent, "Share via")
        context.startActivity(chooser)
    }

    fun openLink(context: Context, sAppLink: String?, sPackage: String?, sWebLink: String?) {
        try {
            val uri = Uri.parse(sAppLink)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(uri)
            intent.setPackage(sPackage)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (activityNotFoundException: ActivityNotFoundException) {
            val uri = Uri.parse(sWebLink)
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(uri)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    fun ourFbPage(context: Context, pageId: String, pageName: String) {
        val facebookAppUri = Uri.parse("fb://page/$pageId")
        val webUri = Uri.parse("https://touch.facebook.com/$pageName")

        try {
            // Check if the Facebook app is installed
            context.packageManager.getPackageInfo("com.facebook.katana", 0)
            // Facebook app is installed, use the app URI
            val intent = Intent(Intent.ACTION_VIEW, facebookAppUri)
            context.startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException) {
            // Facebook app is not installed, use the web URI
            val intent = Intent(Intent.ACTION_VIEW, webUri)
            context.startActivity(intent)
        }
    }
}
