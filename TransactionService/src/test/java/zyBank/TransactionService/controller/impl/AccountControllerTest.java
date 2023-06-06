package zyBank.TransactionService.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zyBank.TransactionService.controller.dto.AccountBalanceDTO;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.repository.AccountsRepository.AccountRepository;
import zyBank.TransactionService.repository.UsersRepository.CustomerRepository;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    WebApplicationContext webApplicationContext;
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.registerModule(new JavaTimeModule());

        Optional<Customer> customer = customerRepository.findById(1);
        String accountId = "ES-123";
        Money balanceTest = new Money(new BigDecimal(1234.00));
        Account accountTest = new Account(accountId, balanceTest, customer.get());
        accountRepository.save(accountTest);
    }

    @Test
    void getAllAccounts_validRequest_allAccounts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        /*System.out.println("\n Aqui-------->");
        System.out.println(mvcResult.getResponse().getContentAsString());*/
        String responseContent = mvcResult.getResponse().getContentAsString();

        List<Account> accounts = accountRepository.findAll();

        assertEquals(6, accounts.size());
        assertTrue(responseContent.contains("ES-"));
        assertTrue(responseContent.contains("USD"));
    }


    @Test
    void getAccountByNumber_validNumber_correctAccount() throws Exception {
       MvcResult mvcResult = mockMvc.perform(get("/accounts/ES-123"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("\n Aqui-------->");
        System.out.println(responseContent);

        assertTrue(responseContent.contains("ES-123"));
        assertTrue(responseContent.contains("USD"));
        assertTrue(responseContent.contains("client1@gbank.es"));
    }

    @Test
    void getAccountByNumber_invalidNumber_BadRequest() throws Exception {

        mockMvc.perform(get("/accounts/4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void getAccountBalance_validNumber_balance() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/balance/ES-123"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        assertFalse(responseContent.contains("EUR"));
        assertFalse(responseContent.contains("1000.00"));

        assertTrue(responseContent.contains("USD"));
        assertTrue(responseContent.contains("1234.00"));
    }

    @Test
    void updateAccountBalance_newBalance_balanceUpdated() throws Exception {
        Money mount = new Money(new BigDecimal(80999.0));
        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO(mount.getAmount(), mount.getCurrency());
        String body = objectMapper.writeValueAsString(accountBalanceDTO);

        mockMvc.perform(patch("/accounts/balance/ES-123").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/accounts/ES-123"))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse().getContentAsString());
        String responseContent = mvcResult.getResponse().getContentAsString();

        assertTrue(responseContent.contains("ES-123"));
        assertTrue(responseContent.contains("80999.0"));

    }

    @Test
    void deleteAccount_validNumber_deleteAccount() throws Exception{
        mockMvc.perform(delete("/accounts/delete/ES-123"))
                .andExpect(status().isNoContent())
                .andReturn();
        mockMvc.perform(get("/accounts/ES-123").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
    @Test
    void deleteAccount_invalidNumber_notFound() throws Exception{
        mockMvc.perform(delete("/accounts/delete/AB-987"))
                .andExpect(status().isNotFound())
                .andReturn();
    }


}