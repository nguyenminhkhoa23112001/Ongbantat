/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;
import java.sql.Timestamp;


/**
 *
 * @author My pc
 */
public class HistoryTransaction {
    public int ID;
    public double Money_Transaction;
    public String Transaction_Type;
    public boolean Status;
    public String Note;  
    public int created_by;
    public Timestamp create_at;   
    public Timestamp Update_at;
    public int receiver;
    public HistoryTransaction() {
    }

    public HistoryTransaction(int ID, double Money_Transaction, String Transaction_Type, boolean Status, String Note,int created_by, Timestamp create_at, int receiver ,  Timestamp Update_at) {
        this.ID = ID;
        this.Money_Transaction = Money_Transaction;
        this.Transaction_Type = Transaction_Type;
        this.Status = Status;
        this.Note = Note;
        this.create_at = create_at;
        this.created_by = created_by;
        this.Update_at = Update_at;
        this.receiver = receiver;
    }

    public int getreceiver() {
        return receiver;
    }

    public void setreceiver(int receiver) {
        this.receiver = receiver;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMoney_Transaction() {
        return Money_Transaction;
    }

    public void setMoney_Transaction(double Money_Transaction) {
        this.Money_Transaction = Money_Transaction;
    }

    public String getTransaction_Type() {
        return Transaction_Type;
    }

    public void setTransaction_Type(String Transaction_Type) {
        this.Transaction_Type = Transaction_Type;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return Update_at;
    }

    public void setUpdate_at(Timestamp Update_at) {
        this.Update_at = Update_at;
    }

    @Override
    public String toString() {
        return "History_Transaction{" + "ID=" + ID + ", Money_Transaction=" + Money_Transaction + ", Transaction_Type=" + Transaction_Type + ", Status=" + Status + ", Note=" + Note + ", create_at=" + create_at + ", created_by=" + created_by + ", Update_at=" + Update_at + '}';
    }
    
}
