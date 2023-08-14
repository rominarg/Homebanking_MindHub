package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.repositories.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api")
public class AccountController {
    // Inyección de dependencia para el repositorio de Cuentas
    @Autowired
    private AccountRepository accountRepository;

    // Manejo de la solicitud para obtener un conjunto de Cuentas (endpoint /api/accounts)
    @RequestMapping("/accounts")
    public Set<AccountDTO> getAccounts() {
        // Buscar todas las Cuentas en el repositorio, convertirlas a AccountDTO y recopilar en un conjunto
        return accountRepository.findAll().stream().map(account -> new AccountDTO(account)).collect(toSet());
    }

    // Manejo de la solicitud para obtener una Cuenta por su ID (endpoint /api/accounts/{id})
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id) {
        // Buscar una Cuenta por su ID en el repositorio y convertirla a AccountDTO
        // Si la Cuenta no se encuentra, se devuelve null
        return accountRepository.findById(id).map(account -> new AccountDTO(account)).orElse(null);
    }
}
