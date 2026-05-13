package com.cgomezq.bookstore

import kotlinx.serialization.Serializable

sealed interface Destinations {
    @Serializable
    data object Catalog : Destinations

    @Serializable
    data object Favorites : Destinations

    @Serializable
    data object Cart : Destinations
}