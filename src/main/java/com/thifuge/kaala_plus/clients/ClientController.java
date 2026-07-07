package com.thifuge.kaala_plus.clients;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @RequestMapping
    public void create(@RequestBody Client client) {
        this.clientService.createClient(client);
        log.info("New client created: {}", client);
    }
}
