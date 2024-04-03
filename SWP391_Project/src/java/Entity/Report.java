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
public class Report {

    private int id;
    private int type_report;
    private int orderID;
    private int recivedID;
    private boolean status;
    private String description;
    private int create_by;
    private Timestamp create_At;
    private Timestamp update_At;
    private int update_by;
    private boolean is_delete;

    public Report() {
    }

    public Report(int id, int type_report, int orderID, int recivedID, boolean status, String description, int create_by, Timestamp create_At, int update_by, Timestamp update_At, boolean is_delete) {
        this.id = id;
        this.type_report = type_report;
        this.orderID = orderID;
        this.recivedID = recivedID;
        this.status = status;
        this.description = description;
        this.create_by = create_by;
        this.create_At = create_At;
        this.update_At = update_At;
        this.update_by = update_by;
        this.is_delete = is_delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_report() {
        return type_report;
    }

    public void setType_report(int type_report) {
        this.type_report = type_report;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDesciption(String description) {
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

    public Timestamp getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Timestamp update_At) {
        this.update_At = update_At;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public int getRecivedID() {
        return recivedID;
    }

    public void setRecivedID(int recivedID) {
        this.recivedID = recivedID;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public boolean isIs_delete() {
        return is_delete;
    }
    
    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Report{" + "id=" + id + ", type_report=" + type_report + ", orderID=" + orderID + ", status=" + status + ", desciption=" + description + ", create_by=" + create_by + ", create_At=" + create_At + ", update_At=" + update_At + ", update_by=" + update_by + ", is_delete=" + is_delete + '}';
    }

    
}
