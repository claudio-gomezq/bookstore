package com.cgomezq.bookstore.features.cart.di

import androidx.room.Room
import com.cgomezq.bookstore.core.common.contract.CartManagerRepository
import com.cgomezq.bookstore.features.cart.data.database.CartDatabase
import com.cgomezq.bookstore.features.cart.data.repositories.ActualCartRepository
import com.cgomezq.bookstore.features.cart.domain.repositories.CartRepository
import com.cgomezq.bookstore.features.cart.domain.usecases.ClearCart
import com.cgomezq.bookstore.features.cart.domain.usecases.GetCartItems
import com.cgomezq.bookstore.features.cart.domain.usecases.UpdateCartQuantity
import com.cgomezq.bookstore.features.cart.presentation.viewmodels.CartViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val cartDatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            CartDatabase::class.java,
            "cart_db"
        ).build()
    }

    single { get<CartDatabase>().cartDao() }
}

val cartDataModule = module {
    factory { ActualCartRepository(get()) } bind CartRepository::class
    factory { ActualCartRepository(get()) } bind CartManagerRepository::class
}

val cartDomainModule = module {
    factory { GetCartItems(get()) }
    factory { UpdateCartQuantity(get()) }
    factory { ClearCart(get()) }
}

val cartViewModelModule = module {
    viewModelOf(::CartViewModel)
}

val cartModule = module {
    includes(
        cartDatabaseModule,
        cartDataModule,
        cartDomainModule,
        cartViewModelModule
    )
}
