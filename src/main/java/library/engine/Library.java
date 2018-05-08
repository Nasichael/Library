package library.engine;

import library.data.Book;
import library.data.Booking;
import library.data.CategoryBook;
import library.data.User;
import library.inventory.BookInventory;
import library.inventory.BookingInventory;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {

    BookInventory bookInventory = new BookInventory();
    Map<Integer, Book> cds = new HashMap<>();
    BookingInventory bookingInventory = new BookingInventory();

    public Library() {

    }


    public List<Book> search(Predicate<Book>... predicates) {

        final Predicate<Book> bookPredicate = Arrays.stream(predicates)
                .reduce(Predicate::and)
                .orElse(t -> true);

        List<Book> filteredBooks = bookInventory.getAll().stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());
        System.out.println(filteredBooks.size());
        return filteredBooks;
    }


    public Booking rent(Book book, User user) {
        Booking booking = new Booking(Booking.getNextId(), user, book, LocalDate.now());
        bookingInventory.addBooking(booking);
        return booking;
    }

    public Collection<Booking> getRentedBooksForUser(User user) {

       return bookingInventory.findBookingsForUser(user);

    }
}
