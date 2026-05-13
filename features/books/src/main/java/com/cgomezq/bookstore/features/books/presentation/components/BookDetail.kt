package com.cgomezq.bookstore.features.books.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cgomezq.bookstore.designsystem.theme.BookstoreTheme
import com.cgomezq.bookstore.features.books.R
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price
import com.cgomezq.bookstore.designsystem.R as DesignSystemR

@Composable
fun BookDetail(
    book: Book,
    addToFavorite: () -> Unit,
    addToCart: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AsyncImage(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth(),
            model = book.coverUrl,
            contentDescription = book.title
        )
        BookContent(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            book = book,
            addToFavorite = addToFavorite
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = addToCart
        ) {
            Text(stringResource(R.string.books_add_to_cart))
        }
    }
}

@Composable
private fun BookContent(modifier: Modifier, book: Book, addToFavorite: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            IconButton(onClick = addToFavorite) {
                Icon(
                    painter = painterResource(DesignSystemR.drawable.ic_favorite),
                    contentDescription = stringResource(R.string.books_favorite_content_description),
                    tint = if (book.isFavorite) {
                        Color.Red
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = book.price.displayValue,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.books_description_label),
            style = MaterialTheme.typography.titleSmall,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = book.summary,
            style = MaterialTheme.typography.bodyLarge
        )

    }
}

@Preview
@Composable
private fun BookDetailPreview() {
    BookstoreTheme {
        Surface {
            BookDetail(
                book = Book(
                    isbn = 1,
                    title = "Title",
                    author = "Author",
                    summary = "",
                    coverUrl = "",
                    price = Price(
                        value = 1000.0,
                        currency = "CLP",
                        displayValue = "$1.000"
                    ),
                    isFavorite = false
                ),
                addToCart = {},
                addToFavorite = {}
            )
        }
    }
}