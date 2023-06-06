package zyBank.TransactionService.service.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import zyBank.TransactionService.controller.dto.UserCredentialsDTO;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.model.User.User;

import java.util.List;

public interface IServiceUser {
    User getUserById(Integer number);

    Customer saveCostumer(Customer costumer);

    Admin saveAdmin(Admin admin);

    ResponseEntity<User> login(UserCredentialsDTO userCredentials);
}
