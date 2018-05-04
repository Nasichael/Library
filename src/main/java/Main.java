import java.util.List;

public class Main {

    Library library = new Library();

    public static void main(String[] args) {
        new Main().start();     /*final Main main = new Main(); main.start();*/
    }

    private void start() {
        String author = "Tolkien";
        String title = "Hobbit";
        // List<Book> books = library.search(title);
        // System.out.println(books);
        List<Book> books2 = library.searchAuthor(author);
        System.out.println(books2);
        List<Book> books3 = library.searchCategory(CategoryBook.NOVEL);
        //System.out.println(books3);
        //  library.search(title("Ania"), author("Lem"), year(1989));
    }
}
