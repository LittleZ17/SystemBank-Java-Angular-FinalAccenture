package zyBank.TransactionService.controller.interfaces;

import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.Account.CheckingAccount;

import java.util.List;

public interface IAccountController {
    List<Account> getAccounts();

    Account getAccount(String number);

    Money getAccountBalance(String number);

    Account createAccount(CheckingAccount checkingAccount);

    Account updateAccountBalance(String number, AccountBalanceDTO accountBalanceDTO);

    String deletedAccount(String number);
}
