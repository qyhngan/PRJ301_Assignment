/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngannq.orderDetail;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class OrderDetailDTO implements Serializable{
    private String id; 
    private int quantity; 
    private double price;
    private double total;
    private int orderID;
    
    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String id, int quantity, double price, int orderID) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.total = quantity*price;
        this.orderID = orderID;
    }
    
    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
