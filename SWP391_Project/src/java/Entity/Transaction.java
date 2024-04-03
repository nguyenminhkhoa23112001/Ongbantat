/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
public class Transaction {
    private int ID;
    private int userID;
    private int orderID;
    private double amount;
    private String paymentCode;

    public Transaction() {
    }

    public Transaction(int ID, int userID, int orderID, double amount) {
        this.ID = ID;
        this.userID = userID;
        this.orderID = orderID;
        this.amount = amount;
    }
    
     public Transaction(int ID, int userID, String paymentCode, double amount) {
        this.ID = ID;
        this.userID = userID;
        this.paymentCode = paymentCode;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    

}
