package com.mycompany.mock.dal;

import com.mycompany.mock.models.Book;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by pandian on 7/23/15.
 */
public class BookDALTest {

    private static BookDAL mockedBookDAL;
    private static Book bookOne;
    private static Book bookTwo;


    @org.junit.Before
    public void setUp() throws Exception {

        mockedBookDAL = mock(BookDAL.class); //Creating MOC object

        bookOne = new Book("1234567890","My First Book",
                Arrays.asList("Author 1", "Author 2", "Author 3"),
                "My Publication 1", 2015, 200, "My Second Book Image");
        bookTwo = new Book("0987654321","My Second Book",
                Arrays.asList("Autor x", "Author y", "Author z"),
                "My Publication 2", 2014, 100, "My Second Book Image");

        when(mockedBookDAL.getAllBooks()).thenReturn(Arrays.asList(bookOne,bookTwo));
        when(mockedBookDAL.getBook("1234567890")).thenReturn(bookOne);
        when(mockedBookDAL.addBook(bookOne)).thenReturn(bookOne.getIsbn());
        when(mockedBookDAL.updateBook(bookOne)).thenReturn(bookOne.getIsbn());

    }

    @org.junit.Test
    public void testGetAllBooks() throws Exception {

        List<Book> allBooks = mockedBookDAL.getAllBooks();
        Assert.assertEquals(2, allBooks.size());
        Assert.assertEquals("1234567890",allBooks.get(0).getIsbn());
        Assert.assertEquals("0987654321", allBooks.get(1).getIsbn());

    }

    @org.junit.Test
    public void testGetBook() throws Exception {

        String isbn = "1234567890";
        Book book = mockedBookDAL.getBook(isbn);
        Assert.assertEquals(isbn, book.getIsbn());
        Assert.assertEquals("My First Book",book.getTitle());

    }

    @org.junit.Test
    public void testAddBook() throws Exception {

        String isbn = mockedBookDAL.addBook(bookOne);
        Assert.assertEquals(bookOne.getIsbn(), isbn);

    }

    @org.junit.Test
    public void testUpdateBook() throws Exception {

        String isbn = mockedBookDAL.updateBook(bookOne);
        Assert.assertEquals(isbn, bookOne.getIsbn());

    }
}