package module6.backend.bookstorebe.controller;

import module6.backend.bookstorebe.entity.book.Book;
import module6.backend.bookstorebe.entity.cart.Cart;
import module6.backend.bookstorebe.entity.cart.CartBook;
import module6.backend.bookstorebe.service.AccountService;
import module6.backend.bookstorebe.service.BookService;
import module6.backend.bookstorebe.service.CartBookService;
import module6.backend.bookstorebe.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartBookService bookCartService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CartBookService cartBookService;

    @Autowired
    private BookService bookService;

    @GetMapping("/cart-book-detail")
    public ResponseEntity<CartBook> findCartBookByBookId(@RequestParam("accountId") Long accountId,
                                                         @RequestParam("bookId") Long bookId) {
        Optional<CartBook> cartBook = cartBookService.findCartBookByBookId(accountId, bookId);
        if (!cartBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cartBook.get(), HttpStatus.OK);
        }
    }
    @GetMapping("/list-cart-book")
    public ResponseEntity<List<CartBook>> findAllCartBook(@RequestParam("accountId") Long accountId) {
        List<CartBook> cartBookList = bookCartService.findAllCartBook(accountId);

            return new ResponseEntity<>(cartBookList, HttpStatus.OK);

    }

    @PutMapping("/update-quantity")
    public ResponseEntity<CartBook> updateQuantityCart(@RequestBody CartBook bookCart) {
        Double totalMoney = bookCart.getBookId().getBookPrice() * bookCart.getCartId().getCartQuantity()
                - bookCart.getBookId().getBookPrice() * bookCart.getCartId().getCartQuantity()
                * (bookCart.getBookId().getBookPromotionId().getPromotionPercent() / 100);
        bookCart.getCartId().setCartTotalMoney(totalMoney);
        cartService.updateQuantityCart(bookCart.getCartId().getCartQuantity(), bookCart.getCartId().getCartTotalMoney(), bookCart.getCartId().getCartId());
        return new ResponseEntity<>(bookCart, HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity<?> addBook(@RequestParam("accountId") Long accountId
            , @RequestBody Book book) {
        Book bookById = bookService.findBookById(book.getBookId());
        List<CartBook> bookCartList = bookCartService.findAllCartBook(accountId);
        // kiểm tra tồn tại giỏ hàng
        for (CartBook cartBook : bookCartList) {
            // update số lượng
            // book.getBookQuantity() là số lượng đưa vào giỏ hàng
            if (cartBook.getBookId().getBookId() == book.getBookId()) {
                if ((book.getBookQuantity() + cartBook.getCartId().getCartQuantity()) > bookById.getBookQuantity()) {
                    String message = "Đã hết hàng!";
                    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                }
                cartBook.getCartId().setCartQuantity(cartBook.getCartId().getCartQuantity() + book.getBookQuantity());
                Double totalMoney = cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
                        - cartBook.getBookId().getBookPrice() * cartBook.getCartId().getCartQuantity()
                        * (cartBook.getBookId().getBookPromotionId().getPromotionPercent() / 100);
                cartBook.getCartId().setCartTotalMoney(totalMoney);
                cartService.updateQuantityCart(cartBook.getCartId().getCartQuantity(), cartBook.getCartId().getCartTotalMoney(), cartBook.getCartId().getCartId());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        // thêm sách mới vào giỏ hàng
        // book.getBookQuantity() là số lượng đưa vào giỏ hàng
        if (book.getBookQuantity() > bookById.getBookQuantity()) {
            String message = "Đã hết hàng!";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        Cart cart = new Cart();
        Double totalMoney = book.getBookPrice() * book.getBookQuantity()
                - book.getBookPrice() * book.getBookQuantity()
                * (book.getBookPromotionId().getPromotionPercent() / 100);
        cart.setCartQuantity(book.getBookQuantity());
        cart.setCartTotalMoney(totalMoney);
        cart.setCartAccountId(accountService.findById(accountId));

        Cart cartCreate = cartService.addBook(cart);

        CartBook cartBook = new CartBook();
        cartBook.setBookId(bookService.findBookById(book.getBookId()));
        cartBook.setCartId(cartCreate);

        CartBook cartBookCreate = bookCartService.addBook(cartBook);
        return new ResponseEntity<>(cartBookCreate, HttpStatus.OK);
    }

    @DeleteMapping("/cart-delete")
    public ResponseEntity<Cart> deleteCustomer(@RequestParam("cardId") Long cardId) {
        Optional<Cart> foundCart = cartService.findById(cardId);
        if (!foundCart.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            cartService.deleteCartById(cardId);
            return new ResponseEntity<>(foundCart.get(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/payment")
    public ResponseEntity<?> paymentCart(@RequestBody List<Cart> cartListPayment) {
        List<String> cartCodeList = cartService.checkCodeCart();

        // lấy mã hoá đơn
        String cartCode = "";
        for (String code : cartCodeList) {
            cartCode = code;
        }
        if (cartCode.equals("")) {
            cartCode = "1";
        }

        String cartCodePayment = "";
        String[] cartCodeArray = cartCode.split("-");
        System.out.println(Integer.parseInt(cartCodeArray[1]));
        if ((Integer.parseInt(cartCodeArray[1]) + 1) < 10) {
            cartCodePayment = "MHD-00" + (Integer.parseInt(cartCodeArray[1]) + 1);
        } else if (Integer.parseInt(cartCodeArray[1] + 1) < 100) {
            cartCodePayment = "MHD-0" + (Integer.parseInt(cartCodeArray[1]) + 1);
        } else {
            cartCodePayment = "MHD-" + (Integer.parseInt(cartCodeArray[1]) + 1);
        }

        // lấy ngày hiện tại
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);

        CartBook bookCart = null;
        // tiến hành thanh toán
        for (Cart cart : cartListPayment) {
            cartService.paymentCart(cartCodePayment, formatted, true, cart.getCartId());

            // cập nhật lại số lượng sách
            bookCart = bookCartService.findByCartId(cart);
            bookCart.getBookId().setBookQuantity(bookCart.getBookId().getBookQuantity() - cart.getCartQuantity());
            bookService.updateQuantityBook(bookCart.getBookId());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete-many-book-cart")
    public ResponseEntity<?> deleteManyCartBook(@RequestBody Long[] cardId) {
        for (int i=0 ; i < cardId.length; i++) {
            cartService.deleteManyBookCart(cardId[i]);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list-cart-history")
    public ResponseEntity<?> getAllCartPayed(@RequestParam("accountId") Long accountId,@PageableDefault(value = 6) Pageable pageable){
        Page<Cart> carts = cartService.findAllCartPayed(accountId, pageable);
        if (carts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }
}