package zyBank.TransactionService.repository.AccountsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zyBank.TransactionService.model.Account.Account;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
    List<Account> findByCustomerId(Integer customerId);
}
