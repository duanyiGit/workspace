package com.duanyi.spring_kafka;

public class Order {
    private int orderId;
    private int num;
    private int price;

    public Order(int orderId, int num, int price) {
        this.orderId = orderId;
        this.num = num;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", num=" + num +
                ", price=" + price +
                '}';
    }
}
