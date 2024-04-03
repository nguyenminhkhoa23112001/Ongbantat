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
public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    public String display_name;
    public boolean is_Admin;
    public boolean is_verify;
    public boolean is_Active;
    public Timestamp create_At;
    public Timestamp update_At;

    public User() {
        
    }
        

    public User(int id, String username, String password, String email, String display_name, boolean is_Admin, boolean is_verify, boolean is_Active, Timestamp create_At, Timestamp update_At) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.display_name = display_name;
        this.is_Admin = is_Admin;
        this.is_verify = is_verify;
        this.is_Active = is_Active;
        this.create_At = create_At;
        this.update_At = update_At;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public boolean isIs_Admin() {
        return is_Admin;
    }

    public void setIs_Admin(boolean is_Admin) {
        this.is_Admin = is_Admin;
    }

    public boolean isIs_Active() {
        return is_Active;
    }

    public void setIs_Active(boolean is_Active) {
        this.is_Active = is_Active;
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

    public boolean isIs_verify() {
        return is_verify;
    }

    public void setIs_verify(boolean is_verify) {
        this.is_verify = is_verify;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", display_name=" + display_name + ", is_Admin=" + is_Admin + ", is_verify=" + is_verify + ", is_Active=" + is_Active + ", create_At=" + create_At + ", update_At=" + update_At + '}';
    }

    
}
