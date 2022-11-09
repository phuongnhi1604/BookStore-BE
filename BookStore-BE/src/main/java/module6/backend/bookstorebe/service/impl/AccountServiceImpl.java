package module6.backend.bookstorebe.service.impl;

import module6.backend.bookstorebe.dto.CustomerAccount;
import module6.backend.bookstorebe.entity.account.Account;
import module6.backend.bookstorebe.entity.account.Role;
import module6.backend.bookstorebe.entity.customer.Customer;
import module6.backend.bookstorebe.repository.AccountRepository;
import module6.backend.bookstorebe.repository.CustomerRepository;
import module6.backend.bookstorebe.repository.RoleRepository;
import module6.backend.bookstorebe.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account findAccountByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void createCustomerAccount(CustomerAccount customerAccount) {
//        if (accountRepository.findAccountByUsername(customerAccount.getAccount().getUsername()) != null){
//            System.out.println("Username existed");
//        }else {
        Set<Role> roles = new HashSet<>();
        customerRepository.save(customerAccount.getCustomer());
        Optional<Role> role = Optional.of(roleRepository.findById(Long.valueOf(2)).get());
        if (role.isPresent()) {
            Role role1 = role.get();
            roles.add(role1);
        }
        Account account = customerAccount.getAccount();
        account.setRoles(roles);
        account.setPassword(getEncodedPassword(customerAccount.getAccount().getPassword()));
        customerAccount.getCustomer().setCustomerAccountId(account);
        customerRepository.save(customerAccount.getCustomer());
//        }
    }
}
