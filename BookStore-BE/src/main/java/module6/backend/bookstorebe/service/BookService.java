package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;;import java.util.List;

public interface BookService {
    Page<Book> findAllTopNewBook(Pageable pageable);
    List<Book> findTopNewBook();

    Book findById(Long id);
}