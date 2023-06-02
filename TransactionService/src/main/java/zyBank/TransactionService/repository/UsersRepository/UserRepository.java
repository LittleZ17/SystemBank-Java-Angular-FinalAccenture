package zyBank.TransactionService.repository.UsersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zyBank.TransactionService.model.User.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
