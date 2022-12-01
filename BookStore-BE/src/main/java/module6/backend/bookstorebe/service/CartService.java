package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.cart.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CartService {
    List<Cart> findAllCart(Long accountId);
    void updateQuantityCart(Integer cartQuantity, Double cartTotalMoney, Long cartId);

    Cart addBook(Cart cart);

    Optional<Cart> findById(Long cardId);

    void deleteCartById(Long cartId);

    void paymentCart(String cartCode, String cartPurchaseDate, Boolean cartStatus, Long cartId);

    List<String> checkCodeCart();

    void deleteManyBookCart(Long id);

    Page<Cart> findAllCartPayed(Long accountId, Pageable pageable);
}
