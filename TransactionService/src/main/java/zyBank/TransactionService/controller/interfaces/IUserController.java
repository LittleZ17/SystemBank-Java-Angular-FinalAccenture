package zyBank.TransactionService.controller.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import zyBank.TransactionService.controller.dto.UserCredentialsDTO;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.model.User.User;

import java.util.List;

public interface IUserController {
    List<User> getUsers();

    User getUser(Integer id);

    List<Admin> getAdmins();

    List<Customer> getCustomers();

    Admin addAdmin(Admin admin);

    Customer addCustomer(Customer customer);
    ResponseEntity<User> login(UserCredentialsDTO userCredentials);

}
