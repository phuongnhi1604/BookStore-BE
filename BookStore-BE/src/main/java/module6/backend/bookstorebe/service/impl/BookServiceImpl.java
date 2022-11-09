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
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
