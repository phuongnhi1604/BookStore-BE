package module6.backend.bookstorebe.controller;

import com.nimbusds.oauth2.sdk.ErrorResponse;
import module6.backend.bookstorebe.dto.CustomerAccount;
import module6.backend.bookstorebe.entity.account.Account;
import module6.backend.bookstorebe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/byUsername/{username}")
    public ResponseEntity<Account> findAccountByUsername(@PathVariable("username") String username) {
        Account account = accountService.findAccountByUsername(username);
        if (account != null)
            return new ResponseEntity<Account>(account, HttpStatus.FOUND);
        return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-Customer-Account")
    public ResponseEntity<?> createCustomerAccount(@RequestBody CustomerAccount customerAccount, BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errors = new HashMap<>();
//            bindingResult.getAllErrors().forEach((error) -> {
//                String fieldName = ((FieldError) error).getField();
//                String errorMessage = error.getDefaultMessage();
//                errors.put(fieldName, errorMessage);
//            });
//            System.out.println(errors);
//            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//        }else {
//            accountService.createCustomerAccount(customerAccount);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
        if(accountService.findAccountByUsername(customerAccount.getAccount().getUsername()) != null){
            System.out.println("Test");
            bindingResult.rejectValue("username", "Tên tài khoản đã tồn tại.");
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        }
        accountService.createCustomerAccount(customerAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
