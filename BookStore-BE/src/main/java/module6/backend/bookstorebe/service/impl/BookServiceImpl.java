package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.entity.book.Book;
import module6.backend.bookstorebe.repository.BookRepository;
import module6.backend.bookstorebe.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> findAllTopNewBook(Pageable pageable) {
        return bookRepository.findAllTopNewBook(pageable);
    }

    @Override
    public List<Book> findTopNewBook() {
        return bookRepository.findTopNewBook();
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Page<Book> findAllBookByCategoryId(Long categoryId, Pageable pageable) {
        return bookRepository.findAllBookByCategoryId(categoryId, pageable);
    }

    @Override
    public List<Book> findAllBookByAuthorId(Long authorId) {
        return bookRepository.findAllBookByAuthorId(authorId);
    }

    @Override
    public List<Book> findBookSameAuthor(Long authorId) {
        return bookRepository.findBookSameAuthor(authorId);
    }

    @Override
    public Page<Book> findAllBookByPromotion(Pageable pageable) {
        return bookRepository.findAllBookByPromotion(pageable);
    }

    @Override
    public Page<Book> searchBook(String searchKey, Pageable pageable) {
        return bookRepository.searchBook(searchKey, pageable);
    }

    @Override
    public Book updateQuantityBook(Book book) {
        return bookRepository.save(book);
    }


}
