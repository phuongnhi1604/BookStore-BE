package module6.backend.bookstorebe.repository;

import module6.backend.bookstorebe.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT * FROM account WHERE username=?1 and account_flag = 0", nativeQuery = true)
    Account findAccountByUsername(String username);
}
