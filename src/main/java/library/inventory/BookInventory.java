package library.inventory;

import library.data.Book;
import library.data.CategoryBook;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookInventory {

    Map<Integer, Book> books = new HashMap<>();

    public BookInventory() {
        books.put(1, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Solaris", 1000));
        books.put(2, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Solaris", 1001));
        books.put(3, new Book("Stanisław Lem", (short) 1959, CategoryBook.SCIENCEFICTION, "Eden", 1010));
        books.put(4, new Book("Stanisław Lem", (short) 1961, CategoryBook.SCIENCEFICTION, "Powrót z gwiazd", 1020));
        books.put(5, new Book("Lucy Maud Montgomery", (short) 1908, CategoryBook.NOVEL, "Ania z Zielonego Wzgórza", 3000));
        books.put(6, new Book("Lucy Maud Montgomery", (short) 1909, CategoryBook.NOVEL, "Ania z Avonlea", 3010));
        books.put(7, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 4000));
        books.put(8, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 4001));
        books.put(9, new Book("John Ronald Reuel Tolkien", (short) 1954, CategoryBook.FANTASY, "Władca Pierścieni", 4002));
        books.put(10, new Book("John Ronald Reuel Tolkien", (short) 1937, CategoryBook.FANTASY, "Hobbit", 4010));
        books.put(11, new Book("Joshua Bloch", (short) 2017, CategoryBook.GUIDE, "Effective Java", 5010));
        books.put(12, new Book("Edgar Rice Burroughs", (short) 1912, CategoryBook.ADVENTURE, "Tarzan of the Apes", 6000));
        books.put(13, new Book("Jules Verne", (short) 1864, CategoryBook.SCIENCEFICTION, "Journey to the Center of the Earth", 1030));
    }

    public Collection<Book> getAll() {
        return books.values();
    }

    public Book getById(int bookId) {

       return books.get(bookId);
    }
}
