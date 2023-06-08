package zyBank.TransactionService.controller.dto;

import lombok.Data;
import zyBank.TransactionService.embeddables.Address;

@Data
public class CustomerUpdateDTO {
    private String phone;
    private String password;
    private Address address;
}
