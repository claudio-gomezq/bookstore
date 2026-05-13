package com.cgomezq.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.cgomezq.bookstore.designsystem.theme.BookstoreTheme
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookstoreTheme {
                setSingletonImageLoaderFactory { context ->
                    ImageLoader.Builder(context)
                        .components {
                            add(
                                OkHttpNetworkFetcherFactory(
                                    callFactory = OkHttpClient.Builder().followRedirects(true)
                                        .build()
                                )
                            )
                        }
                        .build()
                }
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { MainBottomNavigation(navController) }
                ) { contentPadding ->
                    MainNavHost(
                        modifier = Modifier.padding(contentPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}