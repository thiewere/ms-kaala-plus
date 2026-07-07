package com.thifuge.kaala_plus.clients;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public void create(@RequestBody Client client) {
        this.clientService.createClient(client);
        log.info("New client created: {}", client);
    }

    @GetMapping
    public List<Client> findAllClients() {
        return this.clientService.findAllClients();
    }

    @GetMapping(path = "{id}")
    public Client findClientById(@PathVariable int id) {
        return this.clientService.findClientById(id);
    }

    @PutMapping(path = "{id}")
    public Client updateClient(@PathVariable int id, @RequestBody Client client) {
        return this.clientService.updateClient(id, client);
    }

    @DeleteMapping(path = "{id}")
    public void deleteClient(@PathVariable int id) {
        this.clientService.deleteClient(id);
    }
}
