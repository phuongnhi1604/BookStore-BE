package module6.backend.bookstorebe.repository;

import module6.backend.bookstorebe.entity.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM customer WHERE customer_account_id =?1", nativeQuery = true)
    Customer findCustomerByAccountId(Long accountId);
}
