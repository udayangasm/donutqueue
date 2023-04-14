package com.atrify.shop.service;

import com.atrify.shop.domain.Order;

import java.util.PriorityQueue;
import java.util.Queue;

public class OrderQueueSingleton {

    public static Queue<Order> orderQueue = null;
    public static Queue<Order> premiumOrderQueue = null;

    private OrderQueueSingleton() {
    }

    public static Queue<Order> getOrderQueue() {

        if (orderQueue == null) {
            orderQueue = new PriorityQueue<>();
        }
        return orderQueue;
    }

    public static Queue<Order> getPremiumOrderQueue() {

        if (premiumOrderQueue == null) {
            premiumOrderQueue = new PriorityQueue<>();
        }
        return premiumOrderQueue;
    }
}