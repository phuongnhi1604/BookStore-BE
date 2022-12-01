package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.entity.customer.Customer;
import module6.backend.bookstorebe.repository.CustomerRepository;
import module6.backend.bookstorebe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer findCustomerByAccountId(Long accountId) {
        return customerRepository.findCustomerByAccountId(accountId);
    }
}
