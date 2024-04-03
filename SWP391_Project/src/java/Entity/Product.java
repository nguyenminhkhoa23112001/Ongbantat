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
public class Product {

    public int id;
    public String name;
    public double price;
    public int CategoryID;
    public String Description;
    public String image1;
    public String image2;
    public String image3;
    public String image4;
    public boolean Transaction_fee;
    public String Contact_Method;
    public int create_by;
    public String hidden_content;
    public Timestamp create_At;
    public int update_by;

    public Timestamp update_At;
    public boolean is_delete;
    public String Username;
    public Product() {
        
    }

    public Product(int id, String name, double price, int CategoryID, String Description, String image1, String image2, String image3, String image4, boolean Transaction_fee, String Contact_Method, int create_by, String hidden_content, Timestamp create_At, int update_by, Timestamp update_At, boolean is_delete, String Username) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.CategoryID = CategoryID;
        this.Description = Description;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.Transaction_fee = Transaction_fee;
        this.Contact_Method = Contact_Method;
        this.create_by = create_by;
        this.hidden_content = hidden_content;
        this.create_At = create_At;
        this.update_by = update_by;
        this.update_At = update_At;
        this.is_delete = is_delete;
        this.Username = Username;
    }

    public Product(int id, String name, double price, int CategoryID, String Description, String image1, String image2, String image3, String image4, boolean Transaction_fee, String Contact_Method, int create_by, String hidden_content, Timestamp create_At, int update_by, Timestamp update_At, boolean is_delete) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.CategoryID = CategoryID;
        this.Description = Description;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.Transaction_fee = Transaction_fee;
        this.Contact_Method = Contact_Method;
        this.create_by = create_by;
        this.hidden_content = hidden_content;
        this.create_At = create_At;
        this.update_by = update_by;
        this.update_At = update_At;
        this.is_delete = is_delete;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getContact_Method() {
        return Contact_Method;
    }

    public void setContact_Method(String Contact_Method) {
        this.Contact_Method = Contact_Method;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public boolean isTransaction_fee() {
        return Transaction_fee;
    }

    public void setTransaction_fee(boolean Transaction_fee) {
        this.Transaction_fee = Transaction_fee;
    }

    public int getCreate_by() {
        return create_by;
    }

    public void setCreate_by(int create_by) {
        this.create_by = create_by;
    }

    public String getHidden_content() {
        return hidden_content;
    }

    public void setHidden_content(String hidden_content) {
        this.hidden_content = hidden_content;
    }

    public Timestamp getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Timestamp create_At) {
        this.create_At = create_At;
    }

    public int getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(int update_by) {
        this.update_by = update_by;
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
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", CategoryID=" + CategoryID + ", Description=" + Description + ", image1=" + image1 + ", image2=" + image2 + ", image3=" + image3 + ", image4=" + image4 + ", Transaction_fee=" + Transaction_fee + ", Contact_Method=" + Contact_Method + ", create_by=" + create_by + ", hidden_content=" + hidden_content + ", create_At=" + create_At + ", update_by=" + update_by + ", update_At=" + update_At + ", is_delete=" + is_delete + '}';
    }

    public int getCategoryId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

}
