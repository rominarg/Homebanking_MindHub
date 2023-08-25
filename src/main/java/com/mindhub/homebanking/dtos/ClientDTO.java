package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Client;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientDTO {
    // Atributos de la clase ClientDTO
    private Long id;
    private String firstName, lastName, email;

    private Set<AccountDTO> accounts;
    private Set<ClientLoanDTO> loans;

    // Constructor que recibe un objeto Client y crea un DTO
    public ClientDTO(Client client) {
        // Inicialización de atributos utilizando información del cliente
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();

        // Mapeo de las cuentas del cliente a AccountDTO utilizando stream y Collectors
        this.accounts =  client.getAccounts().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());

        // Mapeo de los préstamos del cliente a ClientLoanDTO utilizando stream y Collectors
        this.loans = client.getClientLoans().stream().map(clientLoan -> new ClientLoanDTO(clientLoan)).collect(Collectors.toSet());
    }

    // Métodos getter para acceder a los atributos
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<AccountDTO> getAccounts() {
        return accounts;
    }

    public Set<ClientLoanDTO> getLoans() {
        return loans;
    }
}

//Este código define la clase ClientDTO, que es un Data Transfer Object (DTO) utilizado para transmitir información específica del cliente a través de la aplicación. Los atributos id, firstName, lastName y email representan información básica del cliente. Los conjuntos accounts y loans contienen DTOs de las cuentas y préstamos del cliente, respectivamente.
//El constructor ClientDTO(Client client) toma un objeto Client y asigna los valores relevantes a los atributos del DTO. Los conjuntos accounts y loans se construyen mapeando los objetos relacionados del cliente a sus DTOs correspondientes.
//Los métodos getter proporcionan acceso a los atributos del DTO. En resumen, esta clase facilita la transferencia de información sobre el cliente y sus relaciones en un formato más adecuado para su consumo en distintas partes de la aplicación.