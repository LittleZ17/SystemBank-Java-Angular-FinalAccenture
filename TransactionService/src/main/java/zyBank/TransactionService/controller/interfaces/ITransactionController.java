package zyBank.TransactionService.controller.interfaces;

import zyBank.TransactionService.model.Transaction.Transfer;

import java.util.List;
import java.util.Optional;

public interface ITransactionController {
    List<Transfer> getAllTransfers();

    public Optional<Transfer> getTransaction(Integer id);

    public Transfer createTransaction(Transfer transaction);
}
