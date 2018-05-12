import library.data.Book;
import library.data.Booking;
import library.data.CategoryBook;
import library.engine.Library;
import library.engine.Filters;
import library.inventory.BookInventory;
import library.inventory.BookingInventory;
import library.inventory.UserInventory;

import java.util.List;

public class Main {

    BookInventory bookInventory = new BookInventory();
    BookingInventory bookingInventory = new BookingInventory();
    UserInventory userInventory = new UserInventory();

    Library library = new Library(bookInventory, bookingInventory, userInventory);

    public static void main(String[] args) {
        new Main().start();     /*final Main main = new Main(); main.start();*/
    }

    private void start() {

        List<Book> books2 = library.search(Filters.title("an"));
        System.out.println(books2);
        List<Book> books3 = library.search(Filters.category(CategoryBook.ADVENTURE));
        System.out.println(books3);
        List<Book> books1 = library.search(Filters.author("Jo"), Filters.title("Ja"));
        System.out.println(books1);



    }
}
