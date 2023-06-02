package zyBank.TransactionService.model.Transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyBank.TransactionService.embeddables.Money;

import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;

    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private Money amount;


    private LocalDate transactionDate;

    public Transfer(String sourceAccountNumber, String destinationAccountNumber, Money amount) {
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
        this.transactionDate = LocalDate.now();
    }
}
