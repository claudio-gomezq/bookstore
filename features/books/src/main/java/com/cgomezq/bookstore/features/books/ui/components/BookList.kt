package com.cgomezq.bookstore.features.books.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cgomezq.bookstore.designsystem.components.BookstoreListItem
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
                    onClick = { onBookClick(book) }
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
    BookstoreListItem(
        modifier = modifier,
        title = book.title,
        subtitle = book.author,
        price = book.price.displayValue,
        imageUrl = book.coverUrl,
        onClick = onClick
    )
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