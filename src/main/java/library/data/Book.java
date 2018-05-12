package library.data;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getYear() != book.getYear()) return false;
        if (getId() != book.getId()) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (getCategoryBook() != book.getCategoryBook()) return false;
        return getTitle().equals(book.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getAuthor().hashCode();
        result = 31 * result + (int) getYear();
        result = 31 * result + getCategoryBook().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getId();
        return result;
    }
}
