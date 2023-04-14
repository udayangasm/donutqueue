package com.atrify.shop.controller;

import com.atrify.shop.domain.Order;
import com.atrify.shop.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping("")
    public void addOrder(@RequestBody Order order) {

        logger.info("Add order: {} ", order);
        orderService.addOrder(order);
    }

    @GetMapping("/{clientId}")
    public int checkOrder(@PathVariable int clientId) {

        logger.info("Check order for the client Id : {} ", clientId);
        return orderService.checkOrder(clientId);
    }

    @GetMapping("")
    public List<Order> listOrders() {

        logger.info("List all orders ");
        return orderService.listOrders();
    }

    @GetMapping("/delivery")
    public List<Order> nextDelivery() {

        logger.info("List of next delivery orders ");
        return orderService.nextDeliveryOrders();
    }

    @DeleteMapping("/{clientId}")
    public void cancelOrder(@PathVariable int clientId) {

        logger.info("Cancel the order for client ID : {} ", clientId);
        orderService.cancelOrder(clientId);
    }
}