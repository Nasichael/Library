package library.data;

import java.time.LocalDate;
import java.util.List;

public class SearchBookView {
    private List<Book> searchViewBooks;
    private BookStatus status;
    private LocalDate dateOfBookReturn;

    public SearchBookView(List<Book> searchViewBooks, BookStatus status, LocalDate dateOfBookReturn) {
        this.searchViewBooks = searchViewBooks;
        this.status = status;
        this.dateOfBookReturn = dateOfBookReturn;
    }
}
