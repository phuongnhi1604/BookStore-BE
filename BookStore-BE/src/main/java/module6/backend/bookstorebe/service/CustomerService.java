package module6.backend.bookstorebe.service;

import module6.backend.bookstorebe.entity.customer.Customer;

public interface CustomerService {
    Customer findCustomerById(Long id);
    Customer findCustomerByAccountId(Long accountId);
}
