/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class Wallet {

    private int id;
    private double balance;
    private int create_by;
    private int update_by;
    private Timestamp create_At;
    private Timestamp update_At;

    public Wallet() {
    }

    public Wallet(int id, double balance, int create_by, Timestamp create_At, int update_by, Timestamp update_At) {
        this.id = id;
        this.balance = balance;
        this.create_by = create_by;
        this.update_by = update_by;
        this.create_At = create_At;
        this.update_At = update_At;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCreate_by() {
        return create_by;
    }

    public void setCreate_by(int create_by) {
        this.create_by = create_by;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public Timestamp getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Timestamp create_At) {
        this.create_At = create_At;
    }

    public Timestamp getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Timestamp update_At) {
        this.update_At = update_At;
    }

    @Override
    public String toString() {
        return "Wallet{" + "id=" + id + ", balance=" + balance + ", create_by=" + create_by + ", update_by=" + update_by + ", create_At=" + create_At + ", update_At=" + update_At + '}';
    }

}
