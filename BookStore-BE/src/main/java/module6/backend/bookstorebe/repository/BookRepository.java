package module6.backend.bookstorebe.repository;

import module6.backend.bookstorebe.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book order by book_publish_date desc", nativeQuery = true)
    Page<Book> findAllTopNewBook(Pageable pageable);

    @Query(value = "SELECT * FROM book order by book_publish_date desc limit 8", nativeQuery = true)
    List<Book> findTopNewBook();

    @Query(value = "SELECT * FROM book where book_category_id=?1 order by book_publish_date desc", nativeQuery = true)
    Page<Book> findAllBookByCategoryId(Long book_category_id, Pageable pageable);

    @Query(value = "SELECT * FROM book where book_author_id=?1 order by book_publish_date desc limit 4", nativeQuery = true)
    List<Book> findAllBookByAuthorId(Long book_author_id);

    @Query(value = "SELECT * FROM book left join author on author.author_id = book.book_author_id where book.book_flag = 0 and book.book_author_id=?1  order by book.book_author_id limit 0,4", nativeQuery = true)
    List<Book>findBookSameAuthor(Long book_author_id);

    @Query(value = "SELECT * FROM book where book_promotion_id  <> 1", nativeQuery = true)
    Page<Book> findAllBookByPromotion(Pageable pageable);

    @Query(value = "SELECT * FROM book b join category c on b.book_category_id = c.category_id join author au on b.book_author_id = au.author_id where b.book_name like concat('%',:search_key,'%') or c.category_name like concat('%',:search_key,'%')  or au.author_name like concat('%',:search_key,'%')", nativeQuery = true,
            countQuery = "SELECT count(*) from (SELECT * FROM book b join category c on b.book_category_id = c.category_id join author au on b.book_author_id = au.author_id where b.book_name like concat('%',:search_key,'%') or c.category_name like concat('%',:search_key,'%')  or au.author_name like concat('%',:search_key,'%')) abc ")
    Page<Book> searchBook(@Param("search_key") String search_key, Pageable pageable);
}
