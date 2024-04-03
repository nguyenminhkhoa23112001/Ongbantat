/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.sql.Timestamp;

/**
 *
 * @author acer
 */
public class Feedback {
   public int id;
   public String title ;
   public String content;
   public Timestamp create_at;
   public int  user_id;
   public int  intermediary_order_id;
   public String username;

    public Feedback() {
    }

    public Feedback(int id, String title, String content, Timestamp create_at, int user_id, int intermediary_order_id,String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.create_at = create_at;
        this.user_id = user_id;
        this.intermediary_order_id = intermediary_order_id;
         this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIntermediary_order_id() {
        return intermediary_order_id;
    }

    public void setIntermediary_order_id(int intermediary_order_id) {
        this.intermediary_order_id = intermediary_order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 
   
   
}
