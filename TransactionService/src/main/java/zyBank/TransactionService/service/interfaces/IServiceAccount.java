package zyBank.TransactionService.service.interfaces;

import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.Account.CheckingAccount;

public interface IServiceAccount {
    Account getAccountById(String number);
    Money getAccountBalance(String number);
    Account updateAccountBalance(String number, AccountBalanceDTO accountBalanceDTO);
    Account saveAccount(CheckingAccount checkingAccount);
    String deleteAccount(String number);
}
