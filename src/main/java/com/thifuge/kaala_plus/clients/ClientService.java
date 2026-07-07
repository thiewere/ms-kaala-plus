package com.thifuge.kaala_plus.clients;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(Client client) {
        log.info("Creating a new client: {}", client);
        clientRepository.save(client);
    }
}
