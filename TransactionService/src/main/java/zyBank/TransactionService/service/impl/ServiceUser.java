package zyBank.TransactionService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.service.interfaces.IServiceUser;
import zyBank.TransactionService.model.User.User;
import zyBank.TransactionService.repository.UsersRepository.UserRepository;

import java.util.Optional;

@Service
public class ServiceUser implements IServiceUser {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(Integer number) {
        Optional<User> userOptional = userRepository.findById(number);
        if (userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "UserID not found!");
        }
        return userOptional.get();
    }


    @Override
    public Admin saveAdmin(Admin admin) {
        System.out.println(admin);
        if (userRepository.findById(admin.getId()).isPresent()) {
            throw new RuntimeException("This user Admin exist!");
        } else if (!admin.getEmail().matches("@")) {
            throw new RuntimeException("The mail needs a format email");
        } else {
            return userRepository.save(admin);
        }
    }

    @Override
    public Customer saveCostumer(Customer costumer) {
        if (userRepository.findById(costumer.getId()).isPresent()) {
            throw new RuntimeException("This user exist!");
        } else {
            return userRepository.save(costumer);
        }
    }
}
