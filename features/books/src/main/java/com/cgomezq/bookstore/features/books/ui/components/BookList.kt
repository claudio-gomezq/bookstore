package com.cgomezq.bookstore.features.books.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.cgomezq.bookstore.designsystem.theme.BookstoreTheme
import com.cgomezq.bookstore.features.books.domain.entities.Book
import com.cgomezq.bookstore.features.books.domain.entities.Price

@Composable
fun BookList(books: List<Book>, onBookClick: (Book) -> Unit) {
    LazyColumn {
        items(books) { book ->
            Column {
                BookItem(
                    modifier = Modifier.fillMaxWidth(),
                    book = book,
                    onClick = {
                        onBookClick(book)
                    }
                )
                HorizontalDivider()
            }

        }
    }
}

@Composable
private fun BookItem(
    modifier: Modifier = Modifier,
    book: Book,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        AsyncImage(
            modifier = Modifier.size(60.dp),
            model = book.coverUrl,
            contentDescription = book.title
        )
        Spacer(Modifier.width(8.dp))
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = book.author,
                style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = book.price.displayValue,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun BookItemPreview() {
    BookstoreTheme {
        Surface {
            BookItem(
                modifier = Modifier.fillMaxWidth(),
                book = Book(
                    isbn = 1,
                    title = "Title",
                    author = "Author",
                    summary = "",
                    coverUrl = "",
                    price = Price(
                        value = "1000",
                        currency = "CLP",
                        displayValue = "$1.000"
                    ),
                    isFavorite = false
                ),
                onClick = {}
            )
        }
    }
}