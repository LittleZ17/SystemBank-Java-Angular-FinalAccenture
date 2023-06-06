package zyBank.TransactionService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.repository.UsersRepository.CustomerRepository;
import zyBank.TransactionService.service.interfaces.IServiceAccount;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.repository.AccountsRepository.AccountRepository;

import java.util.List;
import java.util.Optional;
@Service
public class ServiceAccount implements IServiceAccount {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Account> getAccountsByCustomerId(Integer customerId) {
        return accountRepository.findByCustomerId(customerId);
    }

    @Override
    public Account getAccountById(String number) {
        Optional<Account> accountOptional = accountRepository.findById(number);
        if (accountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found");
        }
        return accountOptional.get();
    }

    @Override
    public Money getAccountBalance(String number) {
        Account account = getAccountById(number);
        return account.getBalance();
    }

    @Override
    public Account updateAccountBalance(String id, AccountBalanceDTO accountBalanceDTO) {
        Account newAccountBalance = getAccountById(id);
        try {
            newAccountBalance.setBalance(new Money(accountBalanceDTO.getBalance(), accountBalanceDTO.getCurrency()));
            accountRepository.save(newAccountBalance);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This balance is not valid");
        }
        return newAccountBalance;
    }

    @Override
    public Account saveAccount(Account account) {
        System.out.println(account);
        if (account.getCustomer() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer information is missing");
        }

        Customer customer = customerRepository.findById(account.getCustomer().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        Money balance;
        if (account.getBalance().getAmount().compareTo(account.getMinimumBalance().getAmount()) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The balance must be greater than or equal to the minimum required balance of 100 USD");
        } else {
            balance = new Money(account.getBalance().getAmount());
        }

        Account newAccountToSave = new Account(balance, customer);
        return accountRepository.save(newAccountToSave);
    }

    @Override
    public String deleteAccount(String number) {
        Optional<Account> accountOptional = accountRepository.findById(number);
        if (accountOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This account is not found!");
        }
        accountRepository.delete(accountOptional.get());
        return "Account" + accountOptional.get().getNumber() + "deleted";
    }
}
