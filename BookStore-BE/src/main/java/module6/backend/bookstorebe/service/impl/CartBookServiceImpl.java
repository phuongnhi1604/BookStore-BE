package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.entity.cart.Cart;
import module6.backend.bookstorebe.entity.cart.CartBook;
import module6.backend.bookstorebe.repository.CartBookRepository;
import module6.backend.bookstorebe.service.CartBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartBookServiceImpl implements CartBookService {
    @Autowired
    private CartBookRepository cartBookRepository;

    @Override
    public List<CartBook> findAllCartBook(Long id) {
        return cartBookRepository.findAllBookCart(id);
    }

    @Override
    public CartBook addBook(CartBook cartBook) {
        return cartBookRepository.save(cartBook);
    }

    @Override
    public CartBook findByCartId(Cart cart) {
        return cartBookRepository.findByCartId(cart);
    }

    @Override
    public Optional<CartBook> findCartBookByBookId(Long accountId, Long bookId) {
        return cartBookRepository.findCartBookByBookId(accountId, bookId);
    }
}
