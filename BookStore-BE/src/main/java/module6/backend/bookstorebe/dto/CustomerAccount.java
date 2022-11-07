package module6.backend.bookstorebe.dto;

import module6.backend.bookstorebe.entity.account.Account;
import module6.backend.bookstorebe.entity.customer.Customer;

public class CustomerAccount {
    private Customer customer;
    private Account account;

    public CustomerAccount() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
