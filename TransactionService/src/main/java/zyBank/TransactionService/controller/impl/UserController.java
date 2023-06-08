package zyBank.TransactionService.controller.impl;

import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import zyBank.TransactionService.controller.dto.CustomerUpdateDTO;
import zyBank.TransactionService.controller.dto.UserCredentialsDTO;
import zyBank.TransactionService.controller.interfaces.IUserController;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.model.User.User;
import zyBank.TransactionService.repository.UsersRepository.AdminRepository;
import zyBank.TransactionService.repository.UsersRepository.CustomerRepository;
import zyBank.TransactionService.repository.UsersRepository.UserRepository;
import zyBank.TransactionService.service.interfaces.IServiceUser;


import java.util.List;

@RestController
public class UserController implements IUserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    IServiceUser serviceUser;

    @Override
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Integer id) {
        return serviceUser.getUserById(id);
    }

    @Override
    @GetMapping("/users/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    @Override
    @GetMapping("/users/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @PostMapping("/users/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin addAdmin(@RequestBody @Valid Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    @PostMapping("/users/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return customerRepository.save(customer);
    }


    @PostMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<User> login(@RequestBody UserCredentialsDTO userCredentials) {
        return  serviceUser.login(userCredentials);
    }

    @PutMapping("users/customer/{customerId}")
    public ResponseEntity<Customer> updateCustomerFields(
            @PathVariable Integer customerId,
            @RequestBody CustomerUpdateDTO customerUpdateDTO
    ) {
        try {
            Customer updatedCustomer = serviceUser.updateCustomer(customerId, customerUpdateDTO);
            return ResponseEntity.ok(updatedCustomer);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
}
