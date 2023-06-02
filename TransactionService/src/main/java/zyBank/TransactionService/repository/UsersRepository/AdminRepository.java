package zyBank.TransactionService.repository.UsersRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zyBank.TransactionService.model.User.Admin;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
