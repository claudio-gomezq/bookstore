package com.cgomezq.bookstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.cgomezq.bookstore.designsystem.theme.BookstoreTheme
import com.cgomezq.bookstore.features.books.ui.navigation.BooksDestinations
import com.cgomezq.bookstore.features.books.ui.navigation.booksNavigation
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookstoreTheme {
                val navController = rememberNavController()
                var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
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
                Scaffold(
                    bottomBar = {
                        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                            MainDestinations.entries.forEach {
                                NavigationBarItem(
                                    selected = selectedDestination == it.ordinal,
                                    onClick = {
                                        navController.navigate(route = it.destination)
                                        selectedDestination = it.ordinal
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(it.icon),
                                            contentDescription = it.label
                                        )
                                    },
                                    label = { Text(it.label) }
                                )
                            }
                        }
                    }
                ) { contentPadding ->
                    NavHost(
                        modifier = Modifier.padding(contentPadding),
                        navController = navController,
                        startDestination = BooksDestinations.BookList
                    ) {
                        booksNavigation(navController)
                    }
                }
            }
        }
    }
}