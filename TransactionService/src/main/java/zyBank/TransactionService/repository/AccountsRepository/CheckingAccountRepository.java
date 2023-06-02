package zyBank.TransactionService.repository.AccountsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zyBank.TransactionService.model.Account.CheckingAccount;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, String> {
}
