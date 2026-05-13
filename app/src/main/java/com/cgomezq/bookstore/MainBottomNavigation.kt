package com.cgomezq.bookstore

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.cgomezq.bookstore.designsystem.R as DesignSystemR

private enum class MainDestinations(
    val destination: Any,
    @param:StringRes val label: Int,
    @param:DrawableRes val icon: Int
) {
    Books(
        destination = Destinations.Catalog,
        label = R.string.nav_catalog,
        icon = DesignSystemR.drawable.ic_book
    ),
    Favorites(
        destination = Destinations.Favorites,
        label = R.string.nav_favorites,
        icon = DesignSystemR.drawable.ic_favorite
    ),
    Cart(
        destination = Destinations.Cart,
        label = R.string.nav_cart,
        icon = DesignSystemR.drawable.ic_shopping_cart
    )
}

@Composable
fun MainBottomNavigation(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        MainDestinations.entries.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any { 
                it.hasRoute(item.destination::class) 
            } ?: false

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(route = item.destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.label)
                    )
                },
                label = { Text(stringResource(item.label)) }
            )
        }
    }
}
