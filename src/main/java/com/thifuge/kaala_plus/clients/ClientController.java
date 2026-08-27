package com.thifuge.kaala_plus.clients;

import com.thifuge.kaala_plus.shared.constants.ApiEndpoints;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(ApiEndpoints.CLIENTS)
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public Client create(@RequestBody Client client) {
        return this.clientService.createClient(client);
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
