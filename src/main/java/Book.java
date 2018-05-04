public class Book {

    private String author;
    private short year;
    private CategoryBook categoryBook;
    private String title;
    private int id;

    public Book(String author, short year, CategoryBook categoryBook, String title, int id) {
        this.author = author;
        this.year = year;
        this.categoryBook = categoryBook;
        this.title = title;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public short getYear() {
        return year;
    }
    public CategoryBook getCategoryBook() {
        return categoryBook;
    }
    public String getTitle() {
        return title;
    }
    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "author: " + author +
                ", year: " + year +
                ", categoryBook: " + categoryBook +
                ", title: \"" + title + "\"" +
                ", id: " + id;
    }
}
