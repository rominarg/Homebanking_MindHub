package com.mindhub.homebanking.dtos;


import com.mindhub.homebanking.models.Client;
import java.util.Set;
import java.util.stream.Collectors;


public class ClientDTO {
    private long id;
    private String firstName;

    private String lastName;

    private String email;

    private Set<AccountDTO> account;

    public ClientDTO() {
    }
    public ClientDTO(Client client) {

        this.id = client.getId();

        this.firstName = client.getFirstName();

        this.lastName = client.getLastName();

        this.email = client.getEmail();

        this.account = client.getAccount().stream().map(account -> new AccountDTO(account)).collect(Collectors.toSet());
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccount(Set<AccountDTO> account) {
        this.account = account;
    }

    public Set<AccountDTO> getAccount() {
        return account;
    }

    public String getEmail() {
        return email;
    }

}
