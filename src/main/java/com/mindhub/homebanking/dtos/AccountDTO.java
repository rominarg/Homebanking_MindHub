package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDTO {
    private Long id;
    private String number;
    private LocalDate creationDate;
    private Double balance;
    private Set<TransactionDTO> transactions = new HashSet<>();

    // Constructor que recibe un objeto Account y crea un objeto AccountDTO
    public AccountDTO(Account account) {
        this.id = account.getId();
        this.number = account.getNumber();
        this.creationDate = account.getCreationDate();
        this.balance = account.getBalance();

        // Mapear las transacciones de la cuenta a objetos TransactionDTO y agregarlas al conjunto de transacciones
        this.transactions = account.getTransactions().stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toSet());
    }

    // Métodos getters para acceder a los atributos de AccountDTO
    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return creationDate;
    }

    public Double getBalance() {
        return balance;
    }

    public Set<TransactionDTO> getTransactions() {
        return transactions;
    }
}