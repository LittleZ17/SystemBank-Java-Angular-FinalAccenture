package zyBank.TransactionService.model.Account;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.User.Customer;

@Entity
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "number")
public class CheckingAccount extends Account{

    public CheckingAccount(Money balance, Customer customer) {
        super(balance, customer);
    }
}
