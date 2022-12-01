package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.dto.CustomerAccount;
import module6.backend.bookstorebe.entity.account.Account;

public interface AccountService {
    Account findAccountByUsername(String username);
    String getEncodedPassword(String password);
    void createCustomerAccount(CustomerAccount customerAccount);
    Account findById(Long accountId);
}
