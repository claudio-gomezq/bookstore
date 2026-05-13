package com.cgomezq.bookstore.features.favorites.di

import androidx.room.Room
import com.cgomezq.bookstore.core.common.contract.FavoriteManagerRepository
import com.cgomezq.bookstore.features.favorites.data.database.FavoritesDatabase
import com.cgomezq.bookstore.features.favorites.data.repositories.ActualFavoritesRepository
import com.cgomezq.bookstore.features.favorites.domain.repositories.FavoritesRepository
import com.cgomezq.bookstore.features.favorites.domain.usecases.GetFavorites
import com.cgomezq.bookstore.features.favorites.domain.usecases.RemoveFavorite
import com.cgomezq.bookstore.features.favorites.presentation.viewmodels.FavoritesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            FavoritesDatabase::class.java,
            "favorites_db"
        ).build()
    }

    single { get<FavoritesDatabase>().favoritesDao() }
}

val favoritesDataModule = module {
    factory { ActualFavoritesRepository(get()) } bind FavoritesRepository::class
    factory { ActualFavoritesRepository(get()) } bind FavoriteManagerRepository::class
}

val favoritesDomainModule = module {
    factory { GetFavorites(get()) }
    factory { RemoveFavorite(get()) }
}

val favoritesViewModelModule = module {
    viewModelOf(::FavoritesViewModel)
}

val favoritesModule = module {
    includes(
        databaseModule,
        favoritesDataModule,
        favoritesDomainModule,
        favoritesViewModelModule
    )
}
