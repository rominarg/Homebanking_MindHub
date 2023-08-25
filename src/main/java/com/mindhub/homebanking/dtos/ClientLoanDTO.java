package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.ClientLoan;

// DTO (Data Transfer Object) para representar la información simplificada de un préstamo para clientes
public class ClientLoanDTO {

    private Long id;         // ID del ClientLoan
    private Long loanId;     // ID del préstamo asociado
    private String name;     // Nombre del préstamo asociado
    private Double amount;   // Monto del préstamo
    private Integer payments; // Cantidad de pagos del préstamo

    // Constructor que recibe un objeto ClientLoan y crea un objeto ClientLoanDTO
    public ClientLoanDTO(ClientLoan clientLoan) {
        this.id = clientLoan.getId();
        this.loanId = clientLoan.getLoan().getId();
        this.name = clientLoan.getLoan().getName();
        this.amount = clientLoan.getAmount();
        this.payments = clientLoan.getPayments();
    }

    // Métodos getters para obtener los atributos del ClientLoanDTO

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLoanId() {
        return loanId;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }
}
