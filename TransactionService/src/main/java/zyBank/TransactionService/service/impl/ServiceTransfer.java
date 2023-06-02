package zyBank.TransactionService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zyBank.TransactionService.service.interfaces.IServiceAccount;
import zyBank.TransactionService.service.interfaces.IServiceTransfer;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.Transaction.Transfer;
import zyBank.TransactionService.repository.TransactionsRepository.TransferRepository;
@Service
public class ServiceTransfer implements IServiceTransfer {

    @Autowired
    private IServiceAccount serviceAccount;
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer createTransfer(Transfer transfer) {
        Account sourceAccount = serviceAccount.getAccountById(transfer.getSourceAccountNumber());
        Account destinationAccount = serviceAccount.getAccountById(transfer.getDestinationAccountNumber());

        if (sourceAccount != null && destinationAccount != null){
            if (sourceAccount.getBalance().getAmount().compareTo(transfer.getAmount().getAmount()) > 0){
                sourceAccount.getBalance().decreaseAmount(transfer.getAmount());
                destinationAccount.getBalance().increaseAmount(transfer.getAmount());
                transferRepository.save(transfer);
            }else {
                throw new RuntimeException("Insufficient balance in the source account");
            }
        }else {
            throw new RuntimeException("Invalid source or destination account number!");
        }
        return transfer;
    }
}
