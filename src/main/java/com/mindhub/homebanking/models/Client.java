package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Relación one-to-many con la entidad Account, mapeada por el campo "client"
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<ClientLoan> loans = new HashSet<>();

    // Constructor vacío
    public Client() {
    }

    // Constructor con parámetros para inicializar algunos atributos
    public Client(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Método getter para acceder al ID del cliente
    public Long getId() {
        return id;
    }

    // Métodos getters y setters para acceder a los atributos del cliente
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos getters y setters para la relación con Account
    public Set<Account> getAccounts() {
        return accounts;
    }

    public Set<ClientLoan> getClientLoans(){
        return loans;
    }

    public void addAccount(Account account) {
        // Configurar la relación bidireccional entre Client y Account
        account.setClient(this);
        accounts.add(account);
    }
    public void addClientLoans(ClientLoan clientLoan){
        clientLoan.setClient(this);
        loans.add(clientLoan);
    }


    @JsonIgnore
    public List<Loan> getLoans(){
        return loans.stream().map(clientLoan -> clientLoan.getLoan()).collect(Collectors.toList());
    }


}