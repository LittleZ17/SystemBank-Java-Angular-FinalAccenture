package zyBank.TransactionService.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import zyBank.TransactionService.embeddables.Address;
import zyBank.TransactionService.model.Account.Account;

import java.time.LocalDate;
import java.util.List;
@Entity
@PrimaryKeyJoinColumn(name="id")
@Data
@NoArgsConstructor
public class Customer extends User{

    private String name;
    private String lastname;
    private String idNational;
    private String phone;
    @Embedded
    private Address homeAddress;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accountList;

    public Customer(String name, String lastname, String idNational, String phone, Address homeAddress) {
        this.name = name;
        this.lastname = lastname;
        this.idNational = idNational;
        this.phone = phone;
        this.homeAddress = homeAddress;
    }
}
