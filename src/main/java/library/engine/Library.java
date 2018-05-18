package library.engine;

import library.data.*;
import library.inventory.BookInventory;
import library.inventory.BookingInventory;
import library.inventory.UserInventory;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library {
    public static final int BOOKING_LIMIT = 3;

    //  Map<Integer, Book> cds = new HashMap<>();

    BookInventory bookInventory;
    BookingInventory bookingInventory;
    UserInventory userInventory;

    public Library(BookInventory bookInventory, BookingInventory bookingInventory1, UserInventory userInventory) {
        this.bookInventory = bookInventory;
        this.bookingInventory = bookingInventory1;
        this.userInventory = userInventory;
    }

    public List<Book> search(Predicate<Book>... predicates) {

        final Predicate<Book> bookPredicate = Arrays.stream(predicates)
                .reduce(Predicate::and)
                .orElse(t -> true);

        List<Book> filteredBooks = bookInventory.getAll()
                .stream()
                .filter(bookPredicate)
                .collect(Collectors.toList());
        System.out.println(filteredBooks.size());
        return filteredBooks;
    }

    public List<SearchBookView> searchBookView(Predicate<Book>... predicates) {

        List<Book> filteredBooks = this.search(predicates);


        List<SearchBookView> filteredView =
                filteredBooks.stream().map(p1 -> {
                    Optional<Booking> date =
                            bookingInventory.bookings.stream().filter(p2 -> p2.getBook().getId() == p1.getId()).findAny();

                    if (date.isPresent()) {

                        return new SearchBookView(p1.getId(), p1.getAuthor(), p1.getCategoryBook(),
                                p1.getTitle(), p1.getYear(), BookStatus.RENTED, bookingInventory.calculateReturnDate(date.get().getDate()));

                    } else {

                        return new SearchBookView(p1.getId(), p1.getAuthor(), p1.getCategoryBook(),
                                p1.getTitle(), p1.getYear(), BookStatus.AVAILABLE);
                    }
                }).collect(Collectors.toList());

        return filteredView;
    }

    public Booking rent(Book book, User user) {
        Booking booking = new Booking(Booking.getNextId(), user, book, LocalDate.now());
        bookingInventory.addBooking(booking);
        return booking;
    }

    public Collection<Booking> getRentedBooksForUser(User user) {
        return bookingInventory.findBookingForUser(user);
    }

    public void returnBook(Booking booking) {
        bookingInventory.removeBook(booking);
    }

    public boolean checkBookLimit(User user) {
        int count = bookingInventory.findBookingForUser(user).size();
        if (count >= BOOKING_LIMIT) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfBookRented(Book book) {
        Optional<Booking> booking = bookingInventory.findBookingForBook(book);
        return booking.isPresent();
    }
}