package com.atrify.shop.service;

import com.atrify.shop.domain.Order;
import com.atrify.shop.exception.OrderAlreadyExistsException;
import com.atrify.shop.exception.OrderNotExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Value("${cart.capacity}")
    private int cartCapacity;

    public void addOrder(Order order) {

        Queue<Order> orderQueue = null;

        if (order.getClientId() < 1000) {
            orderQueue = OrderQueueSingleton.getPremiumOrderQueue();
        } else {
            orderQueue = OrderQueueSingleton.getOrderQueue();
        }

        if (orderQueue.contains(order)) {
            logger.error("The client : {} already placed an order", order.getClientId());
            throw new OrderAlreadyExistsException("Client ID: "+order.getClientId());
        }
        order.setSeconds(System.currentTimeMillis() / 1000);
        orderQueue.add(order);
    }

    public int checkOrder(int clientId) {

        Queue<Order> orderQueue = null;

        if (clientId < 1000) {
            orderQueue = OrderQueueSingleton.getPremiumOrderQueue();
        } else {
            orderQueue = OrderQueueSingleton.getOrderQueue();
        }

        int count = 1;
        boolean itemFound = false;

        for (Order order : orderQueue) {

            if (order.getClientId() == clientId) {
                itemFound = true;
                break;
            }
            count++;
        }

        if (itemFound && clientId >= 1000){
            count +=  OrderQueueSingleton.getPremiumOrderQueue().size();
        }
        return itemFound ? count : -1;
    }

    public List<Order> listOrders() {

        List<Order> orderList = new ArrayList<>();
        Queue<Order> premiumOrderQueue = OrderQueueSingleton.getPremiumOrderQueue();
        Queue<Order> normalOrderQueue = OrderQueueSingleton.getOrderQueue();

        int counter = 1;
        for (Order order : premiumOrderQueue) {
            order.setCounter(counter++);
            orderList.add(order);
        }
        for (Order order : normalOrderQueue) {
            order.setCounter(counter++);
            orderList.add(order);
        }
        return orderList;
    }

    public List<Order> nextDeliveryOrders() {

        List<Order> orderList = new ArrayList<>();
        Queue<Order> premiumOrderQueue = OrderQueueSingleton.getPremiumOrderQueue();
        Queue<Order> normalOrderQueue = OrderQueueSingleton.getOrderQueue();

        int totalOrderQty = 0;

        for (Order order : premiumOrderQueue) {
            if (cartCapacity >= totalOrderQty + order.getDonutQty()) {
                totalOrderQty += order.getDonutQty();
                orderList.add(order);
            }
        }
        for (Order order : normalOrderQueue) {
            if (cartCapacity >= totalOrderQty + order.getDonutQty()) {
                totalOrderQty += order.getDonutQty();
                orderList.add(order);
            }
        }
        premiumOrderQueue.removeAll(orderList);
        normalOrderQueue.removeAll(orderList);

        return orderList;
    }

    public void cancelOrder(int clientId) {

        Queue<Order> orderQueue = null;

        if (clientId < 1000) {
            orderQueue = OrderQueueSingleton.getPremiumOrderQueue();
        } else {
            orderQueue = OrderQueueSingleton.getOrderQueue();
        }
        Order order = new Order();
        order.setClientId(clientId);
        if (orderQueue.contains(order)){
            orderQueue.remove(order);
        }else{
            logger.error("The order can not be found for client : {} ", order.getClientId());
            throw new OrderNotExistsException("Client ID: "+order.getClientId());
        }
    }
}