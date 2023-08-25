package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Loan {

    // Atributos principales de la clase Loan
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;
    private Double maxAmount;

    // Lista de pagos asociada al préstamo
    @ElementCollection
    @Column(name = "payment")
    private List<Integer> payments = new ArrayList<>();

    // Relación uno a muchos con la clase ClientLoan
    @OneToMany(mappedBy = "loan", fetch = FetchType.EAGER)
    private Set<ClientLoan> clients = new HashSet<>();

    // Constructores
    public Loan() {
        // Constructor vacío necesario para JPA
    }

    public Loan(String name, Double maxAmount, List<Integer> payments) {
        this.name = name;
        this.maxAmount = maxAmount;
        this.payments = payments;
    }

    // Métodos getter y setter
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clients;
    }

    // Método para agregar una relación Cliente-Préstamo
    public void addClientLoans(ClientLoan clientLoan) {
        clientLoan.setLoan(this);
        clients.add(clientLoan);
    }

    // Método para obtener la lista de clientes asociados al préstamo
    public List<Client> getClients() {
        return clients.stream().map(clientLoan -> clientLoan.getClient()).collect(Collectors.toList());
    }
}