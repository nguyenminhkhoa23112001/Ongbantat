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
public class Category {
    public int id;
    public String name;
    public Timestamp create_At;
    public Timestamp update_At;
    public boolean is_delete;

    public Category() {
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(int id, String name, Timestamp create_At, Timestamp update_At, boolean is_delete) {
        this.id = id;
        this.name = name;
        this.create_At = create_At;
        this.update_At = update_At;
        this.is_delete = is_delete;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isIs_delete() {
        return is_delete;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", create_At=" + create_At + ", update_At=" + update_At + ", is_delete=" + is_delete + '}';
    }
    
    
    
}
