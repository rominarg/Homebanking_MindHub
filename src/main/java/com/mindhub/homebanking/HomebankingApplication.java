package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository,
									  TransactionRepository transactionRepository, LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository){
		return (args -> {
			// Creación de clientes
			Client client1 = new Client("Melba", "Morel", "melba@mindhub.com");
			Client client2 = new Client("Romina", "Navarrete", "ro@gmail.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			// Creación de cuentas
			Account account1 = new Account("VIN001", LocalDate.now(), 2000.0);
			Account account2 = new Account("VIN002", LocalDate.now().plus(Period.ofDays(1)), 3500.0);

			// Asociar cuentas 1 y 2 al cliente1
			client1.addAccount(account1);
			client1.addAccount(account2);

			// Guardar cuentas en el repositorio
			accountRepository.save(account1);
			accountRepository.save(account2);

			// Crear nuevas cuentas para Task3
			Account account3 = new Account("VIN003", LocalDate.now(), 1200.0);
			Account account4 = new Account("VIN004", LocalDate.now(), 1000.0);

			// Asociar cuentas 3 y 4 al cliente2
			client2.addAccount(account3);
			client2.addAccount(account4);

			// Guardar cuentas 3 y 4 en el repositorio
			accountRepository.save(account3);
			accountRepository.save(account4);

			// Crear y guardar nuevas transacciones
			Transaction transaction1 = new Transaction(TransactionType.DEBIT, -1500.0, "Débito -1500", LocalDateTime.now());
			account1.addTransaction(transaction1);
			transactionRepository.save(transaction1);

			Transaction transaction2 = new Transaction(TransactionType.CREDIT, 3500.0, "Crédito $3500", LocalDateTime.now());
			account2.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Transaction transaction3 = new Transaction(TransactionType.DEBIT, -200.0, "Débito $200", LocalDateTime.now());
			account4.addTransaction(transaction3);
			transactionRepository.save(transaction3);

			Transaction transaction4 = new Transaction(TransactionType.CREDIT, 3500.0, "Crédito $3500", LocalDateTime.now());
			account1.addTransaction(transaction4);
			transactionRepository.save(transaction4);

			Transaction transaction5 = new Transaction(TransactionType.DEBIT, -2500.0, "Débito $2500", LocalDateTime.now());
			account4.addTransaction(transaction5);
			transactionRepository.save(transaction5);

			//Créditos para Task4
			Loan loan1 = new Loan("Hipotecario", 500000.0, List.of(12,24,36,48,60));
			loanRepository.save(loan1);
			Loan loan2 = new Loan("Personal", 100000.0, List.of(6,12,24));
			loanRepository.save(loan2);
			Loan loan3 = new Loan("Automotriz", 300000.0, List.of(6,12,24,36));
			loanRepository.save(loan3);

			//Créditos para Melba y segundo client
			ClientLoan clientLoan1 = new ClientLoan(400000.0, 60, client1, loan1);
			client1.addClientLoans(clientLoan1);
			loan1.addClientLoans(clientLoan1);
			clientLoanRepository.save(clientLoan1);

			ClientLoan clientLoan2 = new ClientLoan(50000.0, 12, client1, loan2);
			loan2.addClientLoans(clientLoan2);
			client1.addClientLoans(clientLoan2);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan3 = new ClientLoan(100000.0, 24, client2, loan2);
			client2.addClientLoans(clientLoan3);
			loan2.addClientLoans(clientLoan3);
			clientLoanRepository.save(clientLoan3);

			ClientLoan clientLoan4 = new ClientLoan(200000.0, 36, client2, loan3);
			loan3.addClientLoans(clientLoan4);
			client2.addClientLoans(clientLoan4);
			clientLoanRepository.save(clientLoan4);
		});
	}

}