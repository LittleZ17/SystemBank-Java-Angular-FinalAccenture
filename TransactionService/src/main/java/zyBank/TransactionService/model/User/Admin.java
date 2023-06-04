package zyBank.TransactionService.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id")
public class Admin extends User{
    public Admin(String email, String password) {
        super(email, password);
    }
}
