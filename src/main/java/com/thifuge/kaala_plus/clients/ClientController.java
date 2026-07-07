package com.thifuge.kaala_plus.clients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("clients")
public class ClientController {

    @RequestMapping
    public void create(@RequestBody Client client) {
        log.info("Creating a new client: {}", client);
    }
}
