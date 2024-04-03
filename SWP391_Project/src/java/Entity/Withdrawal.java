/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Timestamp;

/**
 *
 * @author tudo7
 */
public class Withdrawal {
    private int id,created_by,updated_by;
    private String code,status,account_number,account_holder,bankname,bankbranch,response;
    private double amount;
 private Timestamp created_at, updated_at;

    public Withdrawal() {
    }

    public Withdrawal(int id, String code, String status, double amount, String account_number, String account_holder, String bankname, String bankbranch, String response, int created_by, Timestamp created_at, Timestamp updated_at, int updated_by) {
        this.id = id;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.code = code;
        this.status = status;
        this.account_number = account_number;
        this.account_holder = account_holder;
        this.bankname = bankname;
        this.bankbranch = bankbranch;
        this.response = response;
        this.amount = amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public int getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(int updated_by) {
        this.updated_by = updated_by;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getAccount_holder() {
        return account_holder;
    }

    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBankbranch() {
        return bankbranch;
    }

    public void setBankbranch(String bankbranch) {
        this.bankbranch = bankbranch;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Withdrawal{" + "id=" + id + ", created_by=" + created_by + ", updated_by=" + updated_by + ", code=" + code + ", status=" + status + ", account_number=" + account_number + ", account_holder=" + account_holder + ", bankname=" + bankname + ", bankbranch=" + bankbranch + ", response=" + response + ", amount=" + amount + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
 
}
