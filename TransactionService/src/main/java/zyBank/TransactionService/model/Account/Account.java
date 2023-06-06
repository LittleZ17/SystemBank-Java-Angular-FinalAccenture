package zyBank.TransactionService.model.Account;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.User.Customer;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {

    @Id
    private String number;

    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    @Embedded
    private Money balance;

    private final Money minimumBalance = new Money(BigDecimal.valueOf(100));

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private final LocalDate creationDate = LocalDate.now();


    public Account(Money balance, Customer customer) {
        this.number = generateAccountNumber();
        this.balance = balance;
        this.customer = customer;
    }

    public Account(String number, Money balance, Customer customer) {
        this.number = number;
        this.balance = balance;
        this.customer = customer;
    }

    private String generateAccountNumber() {
        String randomDigits = generateRandomDigits(4);
        return "ES-" + randomDigits;
    }

    private String generateRandomDigits(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }
}
