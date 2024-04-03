/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Timestamp;

/**
 *
 * @author Nguyen Minh Khoa
 */
public class OrderHistory {
    private int id;
    private int orderID;
    private String order_status;
    private String description;
    private int create_by;
    private Timestamp create_At;

    public OrderHistory() {
    }

    public OrderHistory(int id, int orderID, String order_status, String description, int create_by, Timestamp create_At) {
        this.id = id;
        this.orderID = orderID;
        this.order_status = order_status;
        this.description = description;
        this.create_by = create_by;
        this.create_At = create_At;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreate_by() {
        return create_by;
    }

    public void setCreate_by(int create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Timestamp create_At) {
        this.create_At = create_At;
    }

    @Override
    public String toString() {
        return "OrderHistory{" + "id=" + id + ", orderID=" + orderID + ", order_status=" + order_status + ", description=" + description + ", create_by=" + create_by + ", create_At=" + create_At + '}';
    }
    
}
