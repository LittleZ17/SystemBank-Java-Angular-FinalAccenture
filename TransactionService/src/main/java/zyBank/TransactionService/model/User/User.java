package zyBank.TransactionService.model.User;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @Email
    private String email;
    @Size(min = 8, message = "The password must be at least 8 characters long.")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
