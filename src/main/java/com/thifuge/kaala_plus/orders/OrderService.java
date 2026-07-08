package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.clients.Client;
import com.thifuge.kaala_plus.clients.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;

    public void createOrder(Order order) {
        Client client = order.getClient();

        /* check if the client exist in the db, if not, register it before saving the order */
        Client clientInDB = this.clientService.findClientById(client.getId());
        if (clientInDB == null) {
            log.info("Creating new client");
            this.clientService.createClient(client);
        }
        log.info("Creating new order");
        this.orderRepository.save(order);
    }

    public List<Order> findAllOrders() {
        log.info("Finding all Orders");
        return this.orderRepository.findAll();
    }

    public Order findOrder(Integer id) {
        log.info("Finding Order with id {}", id);
        Optional<Order> order = this.orderRepository.findById(id);
        return order.orElseThrow(() -> new EntityNotFoundException(
                "Order with id " + id + " not found"
        ));
    }

    public Order updateOrder(Integer id, Order order) {
        log.info("Updating Order with id {}", order.getId());
        Order orderInDB = this.findOrder(id);

        orderInDB.setReference(order.getReference());
        orderInDB.setClient(order.getClient());
        orderInDB.setCompletedAt(order.getCompletedAt());
        orderInDB.setOrderState(order.getOrderState());
        orderInDB.setUpdatedAt(order.getUpdatedAt());

        orderInDB = this.orderRepository.save(orderInDB);
        return orderInDB;
    }

    public void deleteOrder(Integer id) {
        log.info("Deleting Order with id {}", id);
        Order order = this.findOrder(id);
        this.orderRepository.delete(order);
    }
}
