package library.engine;

import java.util.Collection;
import java.util.List;

import library.data.Book;
import library.data.Booking;
import library.data.CategoryBook;
import library.data.User;
import library.inventory.BookInventory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LibraryTest {

    Library library = new Library();
    BookInventory bookInventory = new BookInventory();

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
        int ID = 4001;

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

        Book book = bookInventory.getById(3);
        Book book2 = bookInventory.getById(2);
        User user = new User(1, "Zan", "Ala");
        //when

        final Booking rent = library.rent(book, user);
        final Booking rent2 = library.rent(book2, user);

        //then
        //rent.getUser()==user
        //rent.getBook() ==book
        assertEquals(book,rent.getBook());
        assertEquals(user,rent.getUser());

        Collection<Booking> bookings = library.getRentedBooksForUser(user);
        assertEquals(2, bookings.size());
    }

}

