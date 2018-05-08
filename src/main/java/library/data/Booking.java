package library.data;

import java.time.LocalDate;

public class Booking {

    private static int counter = 0;
    private int id;
    private User user;
    private Book book;
    private LocalDate date;

    public Booking(int id, User user, Book book, LocalDate date) {

        this.id = id;
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDate() {
        return date;
    }

    public static int getNextId()
    {
        return counter++;
    }
}
