import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LibraryTest {

    Library library = new Library();

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
        final List<Book> bookList = library.search(Filters.category(categoryBook),Filters.title(title));

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
                Filters.title(title),Filters.author(author),Filters.category(CategoryBook.NOVEL));

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
        assertEquals(1,bookList.size());
    }

    @Test
    public void shouldSearchByYear(){

        //given
        short year = 2017;

        //when
        List<Book> bookList = library.search(Filters.year(year));

        //then
        assertEquals(1,bookList.size());

    }
}

