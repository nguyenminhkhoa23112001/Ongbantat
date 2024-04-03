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
public class intermediateOrders {
    private int id;
    private String code;
    private int productId;
    private int buyer_id;
    private double total_received_amount, total_paid_amount, intermediary_fee;
    private String status;
    private int create_by;
    private Timestamp create_at;
    private int update_by;
    private Timestamp update_at;
    private boolean is_delete;

    public intermediateOrders() {
    }

    public intermediateOrders(int id, String code, int productId, int buyer_id, double total_received_amount, double total_paid_amount, double intermediary_fee, String status, int create_by, Timestamp create_at, int update_by, Timestamp update_at, boolean is_delete) {
        this.id = id;
        this.code = code;
        this.productId = productId;
        this.buyer_id = buyer_id;
        this.total_received_amount = total_received_amount;
        this.total_paid_amount = total_paid_amount;
        this.intermediary_fee = intermediary_fee;
        this.status = status;
        this.create_by = create_by;
        this.create_at = create_at;
        this.update_by = update_by;
        this.update_at = update_at;
        this.is_delete = is_delete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getTotal_received_amount() {
        return total_received_amount;
    }

    public void setTotal_received_amount(double total_received_amount) {
        this.total_received_amount = total_received_amount;
    }

    public double getTotal_paid_amount() {
        return total_paid_amount;
    }

    public void setTotal_paid_amount(double total_paid_amount) {
        this.total_paid_amount = total_paid_amount;
    }

    public double getIntermediary_fee() {
        return intermediary_fee;
    }

    public void setIntermediary_fee(double intermediary_fee) {
        this.intermediary_fee = intermediary_fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCreate_by() {
        return create_by;
    }

    public void setCreate_by(int create_by) {
        this.create_by = create_by;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    public int getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(int buyer_id) {
        this.buyer_id = buyer_id;
    }

    @Override
    public String toString() {
        return "intermediateOrders{" + "id=" + id + ", code=" + code + ", productId=" + productId + ", buyer_id=" + buyer_id + ", total_received_amount=" + total_received_amount + ", total_paid_amount=" + total_paid_amount + ", intermediary_fee=" + intermediary_fee + ", status=" + status + ", create_by=" + create_by + ", create_at=" + create_at + ", update_by=" + update_by + ", update_at=" + update_at + ", is_delete=" + is_delete + '}';
    }
    
}
