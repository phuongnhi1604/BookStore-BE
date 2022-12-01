package module6.backend.bookstorebe.entity.cart;

import module6.backend.bookstorebe.entity.account.Account;
import module6.backend.bookstorebe.entity.account.Role;
import module6.backend.bookstorebe.entity.book.Book;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;
    private String cartCode;
    private Integer cartQuantity;
    private Double cartTotalMoney;
    private LocalDate cartPurchaseDate;
    private Boolean cartStatus = false;
    @ManyToOne()
    @JoinColumn(name = "cart_account_id")
    private Account cartAccountId;

    public Cart() {
    }

    public Cart(String cartCode, Integer cartQuantity, Double cartTotalMoney, LocalDate cartPurchaseDate, Boolean cartStatus, Account cartAccountId) {
        this.cartCode = cartCode;
        this.cartQuantity = cartQuantity;
        this.cartTotalMoney = cartTotalMoney;
        this.cartPurchaseDate = cartPurchaseDate;
        this.cartStatus = cartStatus;
        this.cartAccountId = cartAccountId;
    }

    public Cart(Long cartId, String cartCode, Integer cartQuantity, Double cartTotalMoney, LocalDate cartPurchaseDate, Boolean cartStatus, Account cartAccountId) {
        this.cartId = cartId;
        this.cartCode = cartCode;
        this.cartQuantity = cartQuantity;
        this.cartTotalMoney = cartTotalMoney;
        this.cartPurchaseDate = cartPurchaseDate;
        this.cartStatus = cartStatus;
        this.cartAccountId = cartAccountId;
    }
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public LocalDate getCartPurchaseDate() {
        return cartPurchaseDate;
    }

    public void setCartPurchaseDate(LocalDate cartPurchaseDate) {
        this.cartPurchaseDate = cartPurchaseDate;
    }

    public Double getCartTotalMoney() {
        return cartTotalMoney;
    }

    public void setCartTotalMoney(Double cartTotalMoney) {
        this.cartTotalMoney = cartTotalMoney;
    }

    public Boolean getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(Boolean cartStatus) {
        this.cartStatus = cartStatus;
    }

    public Account getCartAccountId() {
        return cartAccountId;
    }

    public void setCartAccountId(Account cartAccountId) {
        this.cartAccountId = cartAccountId;
    }
}
