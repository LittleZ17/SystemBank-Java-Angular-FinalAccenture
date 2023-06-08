package zyBank.TransactionService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import zyBank.TransactionService.controller.dto.CustomerUpdateDTO;
import zyBank.TransactionService.controller.dto.UserCredentialsDTO;
import zyBank.TransactionService.embeddables.Address;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.service.interfaces.IServiceUser;
import zyBank.TransactionService.model.User.User;
import zyBank.TransactionService.repository.UsersRepository.UserRepository;

import java.util.List;
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

    public ResponseEntity<User> login(UserCredentialsDTO userCredentials) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(userCredentials.getEmail()) && user.getPassword().equals(userCredentials.getPassword())) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    public Customer updateCustomer(Integer customerId, CustomerUpdateDTO customerUpdateDTO) {
        Optional<User> customerOptional = userRepository.findById(customerId);
        if (customerOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found!");
        }

        Customer customer = (Customer) customerOptional.get();

        if (customerUpdateDTO.getPhone() != null) {
            customer.setPhone(customerUpdateDTO.getPhone());
        }
        if (customerUpdateDTO.getPassword() != null) {
            customer.setPassword(customerUpdateDTO.getPassword());
        }
        if (customerUpdateDTO.getAddress() != null) {
            Address address = customerUpdateDTO.getAddress();
            customer.getHomeAddress().setFullStreet(address.getFullStreet());
            customer.getHomeAddress().setCity(address.getCity());
            customer.getHomeAddress().setPostalCode(address.getPostalCode());
        }
        return userRepository.save(customer);
    }
}
