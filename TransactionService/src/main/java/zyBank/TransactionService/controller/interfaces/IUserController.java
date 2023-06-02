package zyBank.TransactionService.controller.interfaces;

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
}
