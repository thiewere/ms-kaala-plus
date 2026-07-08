package com.thifuge.kaala_plus.clients;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(Client client) {
        log.info("Creating a new client: {}", client);
        clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        log.info("Finding all clients");
        return clientRepository.findAll();
    }

    public Client findClientById(int id) {
        log.info("Finding client by id: {}", id);
        Optional<Client> optionalClient = this.clientRepository.findById(id);
        return optionalClient.orElseThrow(() -> new EntityNotFoundException(
                "Client with id: " + id + " not found"
        ));
    }

    public Client findClientByPhone(String phone) {
        log.info("Finding client by phone: {}", phone);
        Optional<Client> client = this.clientRepository.findClientByPhone(phone);
        return client.orElse(null);
    }

    public Client updateClient(int id, Client client) {
        log.info("Updating client by id: {}", client);
        Client clientInDb = this.findClientById(id);
        clientInDb.setFirstname(client.getFirstname());
        clientInDb.setLastname(client.getLastname());
        clientInDb.setPhone(client.getPhone());
        clientInDb.setCountry(client.getCountry());
        clientInDb.setCreatedAt(client.getCreatedAt());
        clientInDb.setUpdatedAt(client.getUpdatedAt());
        clientInDb = clientRepository.save(clientInDb);
        return clientInDb;
    }

    public void deleteClient(int id) {
        log.info("Deleting client by id: {}", id);
        clientRepository.deleteById(id);
    }

}
