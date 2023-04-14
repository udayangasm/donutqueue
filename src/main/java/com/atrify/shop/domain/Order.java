package com.atrify.shop.domain;

public class Order implements Comparable<Order> {

    private int clientId;
    private int donutQty;
    private long seconds;
    private int counter;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getDonutQty() {
        return donutQty;
    }

    public void setDonutQty(int donutQty) {
        this.donutQty = donutQty;
    }

    public long getSeconds() {
        return seconds;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public int compareTo(Order o) {
        return o.seconds > this.seconds ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Order order = (Order) obj;
        return this.clientId == order.clientId;
    }

    @Override
    public int hashCode() {
        int hash = getClass().hashCode();
        hash = 31 * hash + this.clientId;
        return hash;
    }
}
