package library.engine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import library.data.Book;
import library.data.Booking;
import library.data.CategoryBook;
import library.data.User;
import library.inventory.BookInventory;
import library.inventory.BookingInventory;
import library.inventory.UserInventory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LibraryTest {

    BookInventory bookInventory = new BookInventory();
    BookingInventory bookingInventory = new BookingInventory();
    UserInventory userInventory = new UserInventory();

    Library library = new Library(bookInventory, bookingInventory, userInventory);

    @Test
    public void shouldSearchForExistingTitle() {

        //given
        String title = "Ania";

        //when
        final List<Book> bookList = library.search(Filters.title(title));

        //then
        assertEquals(2, bookList.size());
    }

    @Test
    public void shouldSearchCategoryAndTitle() {

        //given
        CategoryBook categoryBook = CategoryBook.SCIENCEFICTION;
        String title = "Sol";

        //when
        final List<Book> bookList = library.search(Filters.category(categoryBook), Filters.title(title));

        //then
        assertEquals(2, bookList.size());
    }

    @Test
    public void shouldSearchByManyFilters() {

        //given
        String title = "Avon";
        String author = "Lucy";
        CategoryBook categoryBook = CategoryBook.NOVEL;

        //when
        final List<Book> bookList = library.search(Filters.category(CategoryBook.NOVEL),
                Filters.title(title), Filters.author(author), Filters.category(CategoryBook.NOVEL));

        //then
        assertEquals(1, bookList.size());
    }

    @Test
    public void shouldSearchByID() {

        //given
        int ID = 4;

        //when
        final List<Book> bookList = library.search(Filters.ID(ID));

        //then
        assertEquals(1, bookList.size());
    }

    @Test
    public void shouldSearchByYear() {

        //given
        short year = 2017;

        //when
        List<Book> bookList = library.search(Filters.year(year));

        //then
        assertEquals(1, bookList.size());

    }

    @Test
    public void shouldRentOneBook() {
        //given

        Book book = bookInventory.getById(3);
        User user = new User(1, "Zan", "Ala");
        //when

        final Booking rent = library.rent(book, user);

        //then
        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldRentTwoBooks() {
        //given

        Book book = bookInventory.getById(8);
        Book book2 = bookInventory.getById(7);
        User user = new User(1, "Zan", "Ala");
        //when

        final Booking rent = library.rent(book, user);
        final Booking rent2 = library.rent(book2, user);

        //then
        //rent.getUser()==user
        //rent.getBook() ==book
        assertEquals(book, rent.getBook());
        assertEquals(user, rent.getUser());

        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(2, bookings.size());
    }

    @Test
    public void shouldReturnBook() {

        //given
        Book book = bookInventory.getById(3);
        Book book2 = bookInventory.getById(6);
        Book book3 = bookInventory.getById(5);
        User user = userInventory.getById(7);
        User user2 = userInventory.getById(2);
        Booking booking1 = library.rent(book, user);
        Booking booking2 = library.rent(book2, user);
        Booking booking3 = library.rent(book3, user2);

        //when
        library.returnBook(booking2);

        //then
        assertEquals(2, bookingInventory.getBookings().size());

        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(1, bookings.size());
    }
}

