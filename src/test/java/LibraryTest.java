import java.util.List;
import org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class LibraryTest {

    Library library = new Library();

    @Test
    public void shouldSearchForExistingTitle() {

        //given
        String title = "Solaris";

        //when
        final List<Book> bookList = library.search(title);

        //then
        assertEquals(2, bookList.size());
    }


    @Test
    public void shouldSearchByManyFilters() {

        //given
        String title = "Java";
        String author = "Jo";

        //when
        final List<Book> bookList = library.search2(Filters.author("ttt"), Filters.title("andrzej"));

        //then
        assertEquals(1, bookList.size());

    }

    @Test
    public void shouldSearchCategory() {

        //given
        CategoryBook categoryBook = CategoryBook.SCIENCEFICTION;

        //when
        final List<Book> bookList = library.search2(Filters.category(categoryBook));

        //then
        assertEquals(4, bookList.size());

    }

}

