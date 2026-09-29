package com.thifuge.kaala_plus.orders;

import com.thifuge.kaala_plus.clients.Client;
import com.thifuge.kaala_plus.clients.ClientService;
import com.thifuge.kaala_plus.containers.Container;
import com.thifuge.kaala_plus.containers.ContainerService;
import com.thifuge.kaala_plus.order_items.OrderItem;
import com.thifuge.kaala_plus.order_items.OrderItemService;
import com.thifuge.kaala_plus.orders.dto.CreateCompleteOrderRequest;
import com.thifuge.kaala_plus.orders.dto.CreateOrderItemRequest;
import com.thifuge.kaala_plus.orders.dto.CreateOrderResponse;
import com.thifuge.kaala_plus.shared.entities.Currency;
import com.thifuge.kaala_plus.shared.services.CurrencyService;
import com.thifuge.kaala_plus.supplier_products.SupplierProduct;
import com.thifuge.kaala_plus.supplier_products.SupplierProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ContainerService containerService;
    private final CurrencyService currencyService;
    private final SupplierProductService supplierProductService;
    private final OrderItemService orderItemService;

    @Transactional
    public CreateOrderResponse createCompleteOrder(CreateCompleteOrderRequest request){
        Client client = this.clientService.findClientById(request.clientId());

        Currency currency = this.currencyService.getCurrencyByName(request.currency());

        Order order = new Order();
        order.setReference(this.generateOrderReference());
        order.setClient(client);
        order.setCurrency(currency);
        order.setDescription(request.description());
        order.setOrderState(OrderState.READY_FOR_CONFIRMATION);
        order.setCreatedAt(LocalDate.now());
        order.setUpdatedAt(LocalDate.now());
        this.orderRepository.save(order);

        BigDecimal orderTotal = BigDecimal.ZERO;

        for(CreateOrderItemRequest itemRequest : request.items()){
            SupplierProduct supplierProduct = this.supplierProductService
                    .findSupplierProduct(itemRequest.supplierProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setSupplierProduct(supplierProduct);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setUnitPrice(itemRequest.unitPrice());
            BigDecimal totalPrice = itemRequest.unitPrice()
                            .multiply(BigDecimal
                            .valueOf(itemRequest.quantity()));
            orderItem.setTotalPrice(totalPrice);

            orderTotal = orderTotal.add(totalPrice);
            this.orderItemService.createOrderItem(orderItem);
        }

        order.setTotalprice(orderTotal);

        return new CreateOrderResponse(
                order.getId(),
                order.getReference(),
                order.getOrderState()
        );
    }

    public void createOrder(Order order) {
        Client client = order.getClient();

        /* check if the client exist in the db, if not, register it before saving the order */
        Client clientInDB = this.clientService.findClientByPhone(client.getPhone());
        if (clientInDB == null) {
            log.info("Creating new client");
            this.clientService.createClient(client);
        } else {
            order.setClient(clientInDB);
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

    public Order findOrderByReference(String reference) {
        log.info("Finding Order with reference {}", reference);
        Optional<Order> order = this.orderRepository.findByReference(reference);
        return order.orElseThrow(()  -> new EntityNotFoundException(
                "Order with reference " + reference + " not found"
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

    public Order addContainer(String reference, Order order) {
        Container container = order.getContainer();
        Container containerInDB = this.containerService.findContainer(container.getReference());

        log.info("Adding order in container with order reference {}", reference);
        Order orderInDB = this.findOrderByReference(reference);
        orderInDB.setContainer(containerInDB);
        orderInDB = this.orderRepository.save(orderInDB);
        return orderInDB;
    }

    private String generateOrderReference() {
        return "CMD-" + UUID.randomUUID().toString()
                .substring(0, 8)
                .toUpperCase();
    }
}
