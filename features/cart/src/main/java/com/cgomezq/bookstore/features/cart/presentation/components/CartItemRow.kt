package com.cgomezq.bookstore.features.cart.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cgomezq.bookstore.designsystem.components.BookstoreListItem
import com.cgomezq.bookstore.features.cart.R
import com.cgomezq.bookstore.features.cart.domain.entities.CartItem

@Composable
fun CartItemRow(
    item: CartItem,
    onQuantityChange: (Int) -> Unit
) {
    BookstoreListItem(
        modifier = Modifier.fillMaxWidth(),
        title = item.title,
        subtitle = item.author,
        price = item.priceDisplayValue,
        imageUrl = item.coverUrl,
        onClick = {},
        trailingContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { onQuantityChange(item.quantity - 1) }
                ) {
                    Icon(
                        painter =painterResource(R.drawable.ic_remove) , // placeholder for minus
                        contentDescription = stringResource(R.string.cart_decrease_quantity)
                    )
                }

                Text(
                    text = item.quantity.toString(),
                    style = MaterialTheme.typography.bodyLarge
                )

                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = { onQuantityChange(item.quantity + 1) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.cart_increase_quantity)
                    )
                }
            }
        }
    )
}
