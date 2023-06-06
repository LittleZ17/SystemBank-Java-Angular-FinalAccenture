package zyBank.TransactionService;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zyBank.TransactionService.embeddables.Address;
import zyBank.TransactionService.embeddables.Money;
import zyBank.TransactionService.model.Account.Account;
import zyBank.TransactionService.model.User.Admin;
import zyBank.TransactionService.model.User.Customer;
import zyBank.TransactionService.model.User.User;
import zyBank.TransactionService.repository.AccountsRepository.AccountRepository;
import zyBank.TransactionService.repository.TransactionsRepository.TransferRepository;
import zyBank.TransactionService.repository.UsersRepository.UserRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class TransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
			}
		};
	}
	@Bean
	CommandLineRunner run(UserRepository userRepository, AccountRepository accountRepository, TransferRepository transferRepository) {
		return args -> {
			createFakeCustomersAndAccounts(userRepository, accountRepository);
			createFakeAdmins(userRepository);


		};
	}

	public static void createFakeCustomersAndAccounts(UserRepository userRepository, AccountRepository accountRepository){
		Faker faker = new Faker();

		for (int i = 0; i < 5; i++) {
			String email = "client" + (i + 1) + "@gbank.es";
			String password = faker.regexify("[A-Za-z0-9]{8}");
			String name = faker.name().firstName();
			String lastName = faker.name().lastName();
			String idNational = faker.idNumber().valid();
			String phone = generatePhoneNumber();

			String street = faker.address().streetAddress();
			String city = faker.address().city();
			String postalCode = faker.regexify("[0-9]{4}");

			double minBalance = 10000.0;
			double maxBalance = 100000.0;
			double randomBalance = faker.number().numberBetween((int) minBalance, (int) maxBalance);
			BigDecimal balance = BigDecimal.valueOf(randomBalance);
			Money initialBalance = new Money(balance);

			Address homeAddress = new Address(street, city, postalCode);

			Customer customer = new Customer(email, password, name, lastName, idNational, phone, homeAddress);
			userRepository.save(customer);

			Account account = new Account(initialBalance, customer);
			accountRepository.save(account);
		}
	}

	private static void createFakeAdmins(UserRepository userRepository){
		Faker faker = new Faker();
		for (int i = 0; i < 5; i++){
			String email = "admin" + (i + 1) + "@zybank.es";
			String password = faker.regexify("[A-Za-z0-9]{8}");

			Admin admin = new Admin(email, password);
			userRepository.save(admin);
		}

	}

	private static String generatePhoneNumber() {
		Faker faker = new Faker();
		String phoneNumber;
		do {
			phoneNumber = "+34" + faker.numerify("#######");
		} while (phoneNumber.length() != 10); // Asegurar que el número de teléfono tiene 10 caracteres (incluyendo el código de país)

		return phoneNumber;
	}
}
