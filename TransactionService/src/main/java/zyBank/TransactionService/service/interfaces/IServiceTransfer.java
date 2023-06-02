package zyBank.TransactionService.service.interfaces;

import zyBank.TransactionService.model.Transaction.Transfer;

public interface IServiceTransfer {
    Transfer createTransfer(Transfer transfer);
}
