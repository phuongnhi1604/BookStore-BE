package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.cart.Cart;
import module6.backend.bookstorebe.entity.cart.CartBook;

import java.util.List;
import java.util.Optional;

public interface CartBookService {
    List<CartBook> findAllCartBook(Long id);

    CartBook addBook(CartBook cartBook);

    CartBook findByCartId(Cart cart);

    Optional<CartBook> findCartBookByBookId(Long accountId, Long bookId);

}
