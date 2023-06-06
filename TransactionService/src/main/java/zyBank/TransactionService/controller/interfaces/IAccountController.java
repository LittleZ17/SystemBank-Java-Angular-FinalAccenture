package zyBank.TransactionService.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;

import java.util.List;

public interface IAccountController {
    List<Account> getAccounts();
    public ResponseEntity<List<Account>> getAccountsByCustomerId(Integer customerId);

    Account getAccount(String number);

    Money getAccountBalance(String number);

    Account createAccount(Account account);

    Account updateAccountBalance(String number, AccountBalanceDTO accountBalanceDTO);

    String deletedAccount(String number);
}
