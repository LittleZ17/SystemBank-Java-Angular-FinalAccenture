package zyBank.TransactionService.repository.TransactionsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zyBank.TransactionService.model.Transaction.Transfer;
@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}
