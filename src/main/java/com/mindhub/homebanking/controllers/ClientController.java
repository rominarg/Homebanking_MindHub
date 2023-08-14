package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {

    // Inyección de dependencia para el repositorio de Clientes
    @Autowired
    private ClientRepository clientRepository;

    // Manejo de la solicitud para obtener una lista de Clientes (endpoint /api/clients)
    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        // Buscar todos los Clientes en el repositorio, convertirlos a ClientDTO y recopilar en una lista
        return clientRepository.findAll().stream().map(client -> new ClientDTO(client)).collect(toList());
    }

    // Manejo de la solicitud para obtener un Cliente por su ID (endpoint /api/clients/{id})
    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        // Buscar un Cliente por su ID en el repositorio y convertirlo a ClientDTO
        // Si el Cliente no se encuentra, se devuelve null
        return clientRepository.findById(id).map(client -> new ClientDTO(client)).orElse(null);
    }
}

