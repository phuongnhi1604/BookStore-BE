package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {
    Page<Book> findAllTopNewBook(Pageable pageable);
    List<Book> findTopNewBook();

    Book findById(Long id);

    Page<Book> findAllBookByCategoryId(Long categoryId, Pageable pageable);
    List<Book> findAllBookByAuthorId(Long authorId);
    List<Book>findBookSameAuthor(Long authorId);
    Page<Book> findAllBookByPromotion(Pageable pageable);
    Page<Book> searchBook(String searchKey, Pageable pageable);
}
