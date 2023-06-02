package zyBank.TransactionService.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zyBank.TransactionService.controller.interfaces.ITransactionController;
import zyBank.TransactionService.model.Transaction.Transfer;
import zyBank.TransactionService.repository.TransactionsRepository.TransferRepository;
import zyBank.TransactionService.service.interfaces.IServiceTransfer;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController implements ITransactionController {

    @Autowired
    TransferRepository transferRepository;
    @Autowired
    IServiceTransfer serviceTransfer;

    @Override
    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    @Override
    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Transfer> getTransaction(@PathVariable Integer id) {
        return transferRepository.findById(id);
    }

    @Override
    @PostMapping("transactions/new-transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transfer createTransaction(@RequestBody Transfer transaction) {
        return serviceTransfer.createTransfer(transaction);
    }
}
