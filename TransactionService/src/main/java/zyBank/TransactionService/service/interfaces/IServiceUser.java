package zyBank.TransactionService.service.interfaces;

import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.model.User.User;

public interface IServiceUser {
    User getUserById(Integer number);

    Customer saveCostumer(Customer costumer);

    Admin saveAdmin(Admin admin);
}
