package zyBank.TransactionService.controller.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.controller.interfaces.IAccountController;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.repository.AccountsRepository.AccountRepository;
import zyBank.TransactionService.service.interfaces.IServiceAccount;

import java.util.List;

@RestController
public class AccountController implements IAccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IServiceAccount serviceAccount;

    @Override
    @GetMapping("/accounts/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Account>> getAccountsByCustomerId(@PathVariable(name = "customerId") Integer customerId) {
        List<Account> accounts = serviceAccount.getAccountsByCustomerId(customerId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @Override
    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @GetMapping("/accounts/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccount(@PathVariable String number) {
        return serviceAccount.getAccountById(number);
    }

    @Override
    @GetMapping("/balance/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Money getAccountBalance(@PathVariable String number) {
        return serviceAccount.getAccountBalance(number);
    }

    @Override
    @PostMapping("/accounts/new-account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody @Valid Account account) {
        return serviceAccount.saveAccount(account);
    }

    @Override
    @PatchMapping("/accounts/balance/{number}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountBalance(@PathVariable String number, @RequestBody @Valid AccountBalanceDTO accountBalanceDTO) {
        return serviceAccount.updateAccountBalance(number, accountBalanceDTO);
    }

    @Override
    @DeleteMapping("/accounts/delete/{number}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deletedAccount(@PathVariable String number) {
        return serviceAccount.deleteAccount(number);
    }
}
