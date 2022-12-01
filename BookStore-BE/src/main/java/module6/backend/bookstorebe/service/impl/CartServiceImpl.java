package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.entity.cart.Cart;
import module6.backend.bookstorebe.repository.CartRepository;
import module6.backend.bookstorebe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart> findAllCart(Long id) {
        return cartRepository.findAllCart(id);
    }

    @Override
    public void updateQuantityCart(Integer cartQuantity, Double cartTotalMoney, Long cartId) {
        cartRepository.updateQuantityCart(cartQuantity, cartTotalMoney, cartId);
    }

    @Override
    public Cart addBook(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> findById(Long cardId) {
        return cartRepository.findById(cardId);
    }

    @Override
    public void deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public void paymentCart(String cartCode, String cartPurchaseDate, Boolean cartStatus, Long cartId) {
        cartRepository.paymentCart(cartCode, cartPurchaseDate, cartStatus, cartId);
    }

    @Override
    public List<String> checkCodeCart() {
        return cartRepository.checkCodeCart();
    }

    @Override
    public void deleteManyBookCart(Long id) {
        cartRepository.deleteManyBookCart(id);
    }

    @Override
    public Page<Cart> findAllCartPayed(Long accountId, Pageable pageable) {
        return cartRepository.findAllCartPayed(accountId, pageable);
    }
}
