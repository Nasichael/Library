package library.engine;

import java.time.LocalDate;
import java.util.*;

import library.data.*;
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
        Optional<Book> book = bookInventory.getById(5);
       // User user = new User(1, "Zan", "Ala");
        User user = userInventory.getById(7);

        //when
        final Booking rent = library.rent(book.get().getId(), user.getId());

        //then
        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldRentTwoBooks() {
        //given

        Optional<Book> book = bookInventory.getById(8);
        Optional<Book> book2 = bookInventory.getById(7);
        User user = new User(1, "Zan", "Ala");

        //when

        final Booking rent = library.rent(book2.get().getId(), user.getId());
        final Booking rent2 = library.rent(book.get().getId(), user.getId());

        //then
        //rent.getUser()==user
        //rent.getBook() ==book
       /* assertEquals(book, rent.getBook());
        assertEquals(user, rent.getUser());*/

        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(2, bookings.size());
    }

    @Test
    public void shouldReturnBook() {

        //given
        Optional<Book> book = bookInventory.getById(3);
        Optional<Book> book2 = bookInventory.getById(6);
        Optional<Book> book3 = bookInventory.getById(5);
        User user = userInventory.getById(7);
        User user2 = userInventory.getById(2);
        Booking booking1 = library.rent(book.get().getId(), user.getId());
        Booking booking2 = library.rent(book2.get().getId(), user.getId());
        Booking booking3 = library.rent(book3.get().getId(), user2.getId());

        //when
        library.returnBook(booking2);

        //then
        assertEquals(2, bookingInventory.getBookings().size());

        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(1, bookings.size());
    }

    @Test
    public void shouldCheckIfBookNumberReachedLimit() {
        //given
        User user = userInventory.getById(7);
        Optional<Book> book = bookInventory.getById(3);
        Optional<Book> book2 = bookInventory.getById(6);
        library.rent(book.get().getId(), user.getId());
        library.rent(book2.get().getId(), user.getId());

        //when
        final boolean checkBookLimit = library.checkBookLimit(user);
        //then
        assertEquals(false, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookNumberNotReachedLimit() {
        //given
        User user = userInventory.getById(7);
        Optional<Book> book = bookInventory.getById(3);
        Optional<Book> book2 = bookInventory.getById(6);
        Optional<Book> book3 = bookInventory.getById(1);
        library.rent(book.get().getId(), user.getId());
        library.rent(book2.get().getId(), user.getId());
        library.rent(book3.get().getId(), user.getId());

        //when
        final boolean checkBookLimit = library.checkBookLimit(user);
        //then
        assertEquals(true, checkBookLimit);

    }

    @Test
    public void shouldCheckIfBookRented() {
        //given
        User user = userInventory.getById(7);
        Optional<Book> book = bookInventory.getById(3);
        library.rent(book.get().getId(), user.getId());

        //when
        final boolean checkRentedBook = library.checkIfBookRented(book.get());

        //then
        assertEquals(true, checkRentedBook);
    }

    @Test
    public void shouldCheckIfNotBookRented() {
        //given
        Optional<Book> book = bookInventory.getById(3);

        //when
        final boolean checkRentedBook = library.checkIfBookRented(book.get());

        //then
        assertEquals(false, checkRentedBook);
    }

    @Test
    public void shouldCreateSearchBookView() {
        //given
        Optional<Book> book = bookInventory.getById(13);
        User user = userInventory.getById(4);

        //when
        library.rent(book.get().getId(), user.getId());
        List<SearchBookView> searchBookView = library.searchBookView(Filters.title("an"));

        //then
        assertEquals(3, searchBookView.size());

    }

    @Test

    public void shouldCalculateReturnDate() {

        //given
        Optional<Book> book = bookInventory.getById(12);
        User user = userInventory.getById(6);
        library.rent(book.get().getId(), user.getId());
        Optional<Booking> booking = bookingInventory.getById(0);

        //when
        LocalDate returnDate = bookingInventory.calculateReturnDate(booking.get().getDate());

        //then
        assertEquals(LocalDate.now().plusMonths(1), returnDate);
    }
    //LocalDate.now().plusMonths(1)
}


