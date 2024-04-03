/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import Entity.Cart;
import Entity.Category;
import Entity.Feedback;
import Entity.HistoryTransaction;
import Entity.OrderHistory;
import Entity.Product;
import Entity.Report;
import Entity.ReportOrderProduct;
import Entity.User;
import Entity.Wallet;
import Entity.Withdrawal;
import Entity.intermediateOrders;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.text.Normalizer;

/**
 *
 * @author ADMIN
 */
public class DAO extends DBContext {

    public Connection con = null; //connect to sql
    public PreparedStatement ps = null; //ném câu lệnh query sang sql
    public ResultSet rs = null; //nhận kết quả trả về

    public static String removeDiacritics(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        return str;
    }

    // Bach 
    public void signup(String user, String pass, String email) {
        String query = "INSERT users (username, password, email, display_Name, is_admin, is_verify, is_active) VALUES (?, ?, ?, ?, 0, 0, 1)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);
            ps.setString(4, user);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void setVerifyTrue(String email) {
        String query = "Update swp_demo.users set is_verify = 1 WHERE email = ?; ";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String query = "select * from swp_demo.users";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10)));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public Report getReport(int oid) {
        String query = "select * from swp_demo.Report where orderID = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Report getReportByID(int rid) {
        String query = "select * from swp_demo.Report where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, rid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public Report getReportByType(int type, int oid) {
        String query = "select * from swp_demo.Report where type_report = ? and orderID = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, type);
            ps.setInt(2, oid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<Report> getListReport(int uid) {
        List<Report> list = new ArrayList<>();
        String query = "select * from swp_demo.Report where create_by = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Report> getAllReport() {
        List<Report> list = new ArrayList<>();
        String query = "select * from swp_demo.Report";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Report> getAllReportAdmin() {
        List<Report> list = new ArrayList<>();
        String query = "select * from swp_demo.Report where type_report = 7";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public void editReportStatus(int id, boolean status) {
        String sql = "Update report set status=? where id =? ";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public void deleteProductCartAfterBuy(int productID) {
        String sql = "DELETE FROM cart WHERE productID = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public void deleteProductFromCart(int userID, int productID) {
        String sql = "DELETE FROM cart WHERE userID = ? AND productID = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public User getUser(String username) {

        String query = "select * from swp_demo.users where username = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public User getUserById(int id) {

        String query = "select * from swp_demo.users where id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public List<OrderHistory> getOrderHistory(int orderID) {
        List<OrderHistory> list = new ArrayList<>();
        String query = """
                       select * from swp_demo.Order_History
                       where orderID =?""";

        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, orderID);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new OrderHistory(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getTimestamp(6)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public void insertOrderHistory(int orderID, String order_status, String description, int create_by) {
        String query = "INSERT INTO order_history (orderID, order_status, description, create_by)\n"
                + "VALUES (?, ?, ?, ?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, orderID);
            ps.setString(2, order_status);
            ps.setString(3, description);
            ps.setInt(4, create_by);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public User getEmail(String email) {
        String query = "select * from swp_demo.users where email = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    public String getUsername(String username) {
        String query = "select * from swp_demo.users where username = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(2);
            }
        } catch (Exception e) {

        }
        return null;
    }

    public void updateOrder(int buyer_id, String status, int pid) {
        String query = "UPDATE intermediate_Orders\n"
                + "SET \n"
                + "    buyer_id = ?,\n"
                + "    status = ?,\n"
                + "    updated_at = CURRENT_TIMESTAMP\n"
                + "WHERE\n"
                + "    productID = ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, buyer_id);
            ps.setString(2, status);
            ps.setInt(3, pid);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void updateOrderStatus(int uid, String status, int oid) {
        String query = "UPDATE intermediate_Orders\n"
                + "SET \n"
                + "    updated_by = ?,\n"
                + "    status = ?,\n"
                + "    updated_at = CURRENT_TIMESTAMP\n"
                + "WHERE\n"
                + "    id = ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, uid);
            ps.setString(2, status);
            ps.setInt(3, oid);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public Product getProductByID(int id) {

        String sql = "SELECT * FROM swp_demo.product where id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public void insertReport(int type, int orderID, int recivedID, boolean status, String description, int userID, boolean is_delete) {
        String query = "INSERT INTO Report (type_report, orderID, recivedID, status, description, create_by, updated_by, is_delete)\n"
                + "VALUES (?, ?, ?,?, ?, ?, ?, ?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, type);
            ps.setInt(2, orderID);
            ps.setInt(3, recivedID);
            ps.setBoolean(4, status);
            ps.setString(5, description);
            ps.setInt(6, userID);
            ps.setInt(7, userID);
            ps.setBoolean(8, is_delete);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void insertCart(int userID, int productID) {
        String query = "INSERT INTO cart (userID, productID)\n"
                + "VALUES (?, ?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void deleteProduct(int id, boolean is_delete) {
        String query = "Update Product set is_delete = ? where id =? ";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setBoolean(1, is_delete);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void deleteOrder(int id, boolean is_delete) {
        String query = "Update intermediate_Orders set is_delete = ? where id =? ";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setBoolean(1, is_delete);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void insertWallet(double balance, int uid) {
        String query = "INSERT INTO Wallet (balance, create_by, updated_by)\n"
                + "VALUES (?, ?, ?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setDouble(1, balance);
            ps.setInt(2, uid);
            ps.setInt(3, uid);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public List<intermediateOrders> getOrderBuy(int bid) {
        List<intermediateOrders> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where buyer_id =? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new intermediateOrders(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13)));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public intermediateOrders getOrderByUser(int uid) {
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where create_by = ? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new intermediateOrders(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void updateAmount(double balance, int uid) {
        String sql = "UPDATE Wallet SET balance = ? WHERE create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setDouble(1, balance);
            ps.setInt(2, uid);
            ps.executeUpdate();
            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateTransactionStatus(int tid, String status) {
        String sql = "UPDATE transactions SET status = ? WHERE id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(2, tid);
            ps.setString(1, status);

            ps.executeUpdate();
            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int insertTransactionBuy(int uid, int pid, String status) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được sinh ra
        String query = "INSERT INTO transactions (user_id, product_id, status)\n"
                + "VALUES (?, ?, ?)";
        try {
            con = new DBContext().connection; // Kết nối với cơ sở dữ liệu
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, uid);
            ps.setInt(2, pid);
            ps.setString(3, status);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Lấy ID được sinh ra
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
        } finally {
            // Đóng tài nguyên (PreparedStatement, ResultSet, Connection)
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
            }
        }
        return generatedId;
    }

    public int insertTransactionVnpay(int uid, String paymentCode, String status) {
        int generatedId = -1; // Giá trị mặc định nếu không có ID được sinh ra
        String query = "INSERT INTO transactions (user_id, paymentCode, status)\n"
                + "VALUES (?, ?, ?)";
        try {
            con = new DBContext().connection; // Kết nối với cơ sở dữ liệu
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, uid);
            ps.setString(2, paymentCode);
            ps.setString(3, status);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Lấy ID được sinh ra
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
        } finally {
            // Đóng tài nguyên (PreparedStatement, ResultSet, Connection)
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Xử lý ngoại lệ một cách thích hợp
            }
        }
        return generatedId;
    }

    //HUY
    public User Login(String username, String pass) {

        String query = "select * from swp_demo.users where username = ? and password = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10));
            }
        } catch (Exception e) {

        }
        return null;
    }

    //BINH
    public boolean isEmailExists(String email) {
        boolean emailExists = false;

        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, email);

            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                emailExists = count > 0;
            }

        } catch (Exception e) {

        }

        return emailExists;
    }

    public User isEmail(String email) {
        String sql = "select * from users where email = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6),
                        rs.getBoolean(7),
                        rs.getBoolean(8),
                        rs.getTimestamp(9),
                        rs.getTimestamp(10));
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public User updatePassword(String pass, int id) {
        String sql = "Update users set password=? where id =? ";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;

    }

    //HUY
    public void insertProduct(Product product) {
        String sql = "INSERT INTO swp_demo.Product (name,"
                + " price, "
                + "categoryID, "
                + "description, "
                + "image1, "
                + "image2, "
                + "image3, "
                + "image4, "
                + "transaction_Fees,"
                + "contact_Method, "
                + "create_by, "
                + "hidden_content, "
                + "updated_by, "
                + "is_delete) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getCategoryID());
            ps.setString(4, product.getDescription());
            ps.setString(5, product.getImage1());
            ps.setString(6, product.getImage2());
            ps.setString(7, product.getImage3());
            ps.setString(8, product.getImage4());
            ps.setBoolean(9, product.isTransaction_fee());
            ps.setString(10, product.getContact_Method());
            ps.setInt(11, product.getCreate_by());
            ps.setString(12, product.getHidden_content());
            ps.setInt(13, product.getCreate_by());
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public void insertOrder(intermediateOrders order) {
        String sql = "INSERT INTO swp_demo.intermediate_Orders (code, "
                + "productID, "
                + "total_received_amount, "
                + "total_paid_amount, "
                + "intermediary_fee, "
                + "status,"
                + " create_by, "
                + "updated_by, "
                + "is_delete) \n"
                + "VALUES \n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, 0)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);
            ps.setString(1, order.getCode());
            ps.setInt(2, order.getProductId());
            ps.setDouble(3, order.getTotal_received_amount());
            ps.setDouble(4, order.getTotal_paid_amount());
            ps.setDouble(5, order.getIntermediary_fee());
            ps.setString(6, order.getStatus());
            ps.setInt(7, order.getCreate_by());
            ps.setInt(8, order.getUpdate_by());
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public int getIdProduct() {
        String query = "SELECT MAX(id) AS max_id FROM swp_demo.Product";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("max_id"); // Lấy giá trị id từ cột max_id trong ResultSet
                return id;
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public List<Product> getProductByUserID(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product where create_by = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Product> getProductByBuyerID(int bid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product where buyer_id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, bid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return null;
    }

    public List<Product> getProductByName(String name, int uid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product WHERE LOWER(REPLACE(name, ' ', '')) LIKE ? AND create_by = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + removeDiacritics(name.toLowerCase()) + "%");
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return list;
    }

    public List<Product> getProductByFee(int fee, int uid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product WHERE transaction_Fees = ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, fee);
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return list;
    }

    public List<Product> getProductByPrice(double price, int uid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product WHERE price <= ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return list;
    }

    public List<Product> getProductByHigherPrice(double price, int uid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product WHERE price > ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setDouble(1, price);
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        return list;
    }

    public Product getProductById(int id) {
        String sql = "SELECT * FROM swp_demo.product\n"
                + "where id =? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17));
            }

        } catch (Exception e) {

        }
        return null;

    }

    public List<intermediateOrders> getListOrderByCode(String code, int uid) {
        List<intermediateOrders> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where code LIKE ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + code + "%");
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new intermediateOrders(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13)
                ));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public List<intermediateOrders> getOrderByStatus(String status, int uid) {
        List<intermediateOrders> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where status LIKE ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + status + "%");
            ps.setInt(2, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new intermediateOrders(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13)
                ));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public List<intermediateOrders> getAllOrderByStatus(String status) {
        List<intermediateOrders> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where status LIKE ? AND create_by =?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + status + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new intermediateOrders(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13)
                ));
            }
        } catch (Exception e) {

        }
        return list;

    }

    public intermediateOrders getOrderByProductID(int id) {
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where productID =? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new intermediateOrders(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13)
                );
            }
        } catch (Exception e) {

        }
        return null;

    }

    public intermediateOrders getOrderByID(int id) {
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where id =? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new intermediateOrders(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public intermediateOrders getOrderByCode(String code) {
        String sql = "SELECT * FROM swp_demo.intermediate_orders\n"
                + "where code =? ;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new intermediateOrders(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getInt(11),
                        rs.getTimestamp(12),
                        rs.getBoolean(13));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void UpdateProductByProductID(Product product) {
        String sql = "UPDATE Product\n"
                + "SET name = ?,\n"
                + "    price = ?,\n"
                + "    description = ?,\n"
                + "    image1 = ?,\n"
                + "    image2 = ?,\n"
                + "    image3 = ?,\n"
                + "    image4 = ?,\n"
                + "    transaction_Fees = ?, -- or 0 depending on the value\n"
                + "    contact_Method = ?,\n"
                + "    hidden_content =?,\n"
                + "    updated_by = ?,\n"
                + "    updated_at = CURRENT_TIMESTAMP,\n"
                + "    is_delete = ? -- or 1 depending on the status\n"
                + "WHERE id = ?;";
        try {
            ps = con.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImage1());
            ps.setString(5, product.getImage2());
            ps.setString(6, product.getImage3());
            ps.setString(7, product.getImage4());
            ps.setBoolean(8, product.isTransaction_fee());
            ps.setString(9, product.getContact_Method());
            ps.setString(10, product.getHidden_content());
            ps.setInt(11, product.getUpdate_by());
            ps.setBoolean(12, product.isIs_delete());
            ps.setInt(13, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
        }

    }

    public void UpdateOrdersByID(int id, intermediateOrders Order) {
        String sql = "UPDATE swp_demo.intermediate_Orders\n"
                + "SET\n"
                + "    total_received_amount = ?, \n"
                + "    total_paid_amount = ?, \n"
                + "    intermediary_fee = ?, \n"
                + "    updated_by = ?,\n"
                + "    updated_at = CURRENT_TIMESTAMP \n"
                + "WHERE\n"
                + "    id = ?;";
        try {
            ps = con.prepareStatement(sql);
            ps.setDouble(1, Order.getTotal_received_amount());
            ps.setDouble(2, Order.getTotal_paid_amount());
            ps.setDouble(3, Order.getIntermediary_fee());
            ps.setInt(4, Order.getUpdate_by());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    //BINH
    //HUE
    public void updateProfile(String email, String displayName, int id) {
        String sql = "UPDATE users SET display_name=?, email=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, displayName);
            // st.setString(2, password);
            st.setString(2, email);
            st.setInt(3, id);
            st.executeUpdate();
            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public Product getProductDetailsById(int id) {
        Product p = null;
        String sql = "SELECT p.*, u.username\n"
                + "FROM swp_demo.product p\n"
                + "JOIN swp_demo.users u ON p.create_by = u.id\n"
                + "WHERE p.id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                p = (new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17), rs.getString(18)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return p;
    }

    public List<Product> getAllProductByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.product where is_delete = false AND categoryID = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public HashMap<Integer, Double> getDataForChart(int user_id, int year) {
        try {
            HashMap<Integer, Double> map = new HashMap<>();

            for (int i = 1; i <= 12; i++) {
                String sqlCount = "SELECT COUNT(id) FROM swp_demo.product where  MONTH(create_at) = ?";
                PreparedStatement countStatement = connection.prepareStatement(sqlCount);
                countStatement.setInt(1, i);

                ResultSet countResultSet = countStatement.executeQuery();
                countResultSet.next(); // Move to the first row
                int productCount = countResultSet.getInt(1);

                String sql = "SELECT "
                        + "    io.create_by AS user_id, "
                        + "    u.is_admin, "
                        + "    (CASE WHEN u.is_admin = 1 THEN 0.5 * ? ELSE 0 END )+ SUM(io.total_received_amount) AS total_received_amount_by_user "
                        + "FROM "
                        + "    swp_demo.intermediate_orders io "
                        + "JOIN "
                        + "    swp_demo.users u ON io.create_by = u.id "
                        + "WHERE "
                        + "    YEAR(io.create_at) = ? "
                        + "    AND io.create_by = ? "
                        + "    AND io.status = 'Đơn hàng đã hoàn thành' "
                        + "    AND MONTH(io.create_at) = ? "
                        + "GROUP BY "
                        + "    io.create_by, u.is_admin";

                PreparedStatement st = connection.prepareStatement(sql);
                st.setDouble(1, productCount);
                st.setInt(2, year);
                st.setInt(3, user_id);
                st.setInt(4, i);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    map.put(i, rs.getDouble("total_received_amount_by_user"));
                } else {
                    map.put(i, 0.0);
                }

                // Close result set and statements
                rs.close();
                st.close();
                countResultSet.close();
                countStatement.close();
            }

            return map;
        } catch (SQLException e) {
            System.out.println(e + "hihi");
        }
        return null;
    }

    public double totalMoneyDay(int day, int user_id) {
        String sql = "SELECT SUM(total_received_amount) FROM swp_demo.intermediate_orders WHERE DAYOFWEEK(create_at) = ? AND create_by = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, day);
            st.setInt(2, user_id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // It's important to log or handle exceptions appropriately
        }
        return 0;
    }

    public int getQuantityAll(int user_id) {
        try {
            String sql = "SELECT COUNT(id) FROM swp_demo.product WHERE create_by = ?;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getRevenueData(int user_id) {
        try {
            String sql = "SELECT io.create_by AS user_id,\n"
                    + "    u.is_admin,\n"
                    + "    (CASE WHEN u.is_admin = 1 THEN 0.5 * (SELECT COUNT(id) FROM swp_demo.product) ELSE 0 END )+ SUM(io.total_received_amount) AS total_received_amount_by_user\n"
                    + "FROM \n"
                    + "    swp_demo.intermediate_orders io\n"
                    + "JOIN \n"
                    + "    swp_demo.users u ON io.create_by = u.id\n"
                    + "WHERE \n"
                    + "    io.create_by = ?\n"
                    + "    AND io.status = 'Đơn hàng đã hoàn thành'\n"
                    + "    AND YEAR(io.create_at) = YEAR(CURDATE())\n"
                    + "GROUP BY \n"
                    + "    io.create_by, u.is_admin;";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                // Thay đổi ở đây, vì cột total_received_amount_by_user được đặt tên trong truy vấn SQL
                return rs.getDouble("total_received_amount_by_user");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

//    public ArrayList<Feedback> getFeedbackList() {
//        ArrayList<Feedback> list = new ArrayList<>();
//        try {
//            
//            String sql = "SELECT f.*, u.username FROM swp_demo.feedback f JOIN swp_demo.users u ON f.user_id = u.id ORDER BY f.id DESC";
//
//            PreparedStatement st = connection.prepareStatement(sql);
////            st.setInt(1, userId);
//            ResultSet rs = st.executeQuery();
//            while (true) {
//                if (rs.next()) {
//                    list.add(new Feedback(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
//                } else {
//                    break;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return list;
//    }
    public int getTotalFeedbackCount() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM swp_demo.feedback";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public ArrayList<Feedback> getFeedbackList(int currentPage, int itemsPerPage) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            // Calculate the offset for pagination
            int offset = (currentPage - 1) * itemsPerPage;
            String sql = "SELECT f.*, u.username FROM swp_demo.feedback f JOIN swp_demo.users u ON f.user_id = u.id ORDER BY f.id DESC LIMIT ?, ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, offset);
            st.setInt(2, itemsPerPage);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Feedback(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getInt(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void createFeedback(int user_id, String title, String content) {
        try {
            String sql = "INSERT INTO swp_demo.feedback (user_id, title, content, create_at) VALUES (?, ?, ?, NOW());";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setString(2, title);
            st.setString(3, content);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateFeedback(int id, int user_id, String title, String content) {
        try {
            String sql = "UPDATE swp_demo.feedback\n"
                    + "SET user_id = ?\n"
                    + "    ,title = ?\n"
                    + "    ,content = ?\n"
                    + "    ,create_at = NOW() \n"
                    + "WHERE id = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setString(2, title);
            st.setString(3, content);
            st.setInt(4, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteFeedback(int id) {
        try {
            String sql = "DELETE FROM  swp_demo.feedback\n"
                    + "      WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void editUserByAdmin(int id, boolean is_Active) {
        String sql = "Update users set is_Active=? where id =? ";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, is_Active);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (Exception e) {

        }

    }

    public void changePassword(String password, String username) {
        String sql = "UPDATE users SET password=? WHERE username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, username);

            st.executeUpdate();

            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public User getUser(String username, String password) {
        String sql = "Select * from users where username = ? and password=? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("password"));
                return u;
            }
            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean isEmailAlreadyExists(String email, int id) {

        String sql = "Select * from users where email = ? AND id != ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setInt(2, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    //BINH
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from product as p inner join intermediate_orders i on p.id=i.productID\n"
                + "where i.status='Sẵn sàng giao dịch' and p.is_delete=false";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM swp_demo.category";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3),
                        rs.getTimestamp(4),
                        rs.getBoolean(5)));

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Product> getAllProductbyName(String name) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product p\n"
                + "JOIN intermediate_orders io ON p.id = io.productID where name like ? and io.status = 'Sẵn sàng giao dịch' and p.is_delete=false";
        try {
            con = new DBContext().connection;

            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Category getCategoryById(int cid) {
        String sql = "SELECT * FROM swp_demo.category\n"
                + "where id=?;";
        try {
            con = new DBContext().connection;

            ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Category(rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3),
                        rs.getTimestamp(4),
                        rs.getBoolean(5));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<Product> getProductbyCategoryID(String id) {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from product as p inner join category as c\n"
                + "          on p.categoryID=c.id\n"
                + "          join intermediate_orders i on p.id=i.productID\n"
                + "          where p.categoryID= ? and i.status='Sẵn sàng giao dịch' and p.is_delete=false";
        try {
            con = new DBContext().connection;

            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getString(13),
                        rs.getTimestamp(14),
                        rs.getInt(15),
                        rs.getTimestamp(16),
                        rs.getBoolean(17)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public Wallet getWallet(int uid) {
        String query = "Select * from Wallet where create_by = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Wallet(rs.getInt(1),
                        rs.getDouble(2),
                        rs.getInt(3),
                        rs.getTimestamp(4),
                        rs.getInt(5),
                        rs.getTimestamp(6));
            }
        } catch (Exception e) {

        }
        return null;

    }

    public void insertCategory(String name) {
        String query = "INSERT INTO `swp_demo`.`category` (`name`) VALUES (?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void DeleteCategory(String id) {
        String query = "DELETE FROM `swp_demo`.`category` WHERE id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public void Updatecategory(Category c) {
        String query = "Update `swp_demo`.`category` SET name=? WHERE id = ?";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getId());
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public List<Report> getTopNext3Report(int uid, int amount) {
        List<Report> list = new ArrayList<>();
        String query = "SELECT * FROM Report where recivedID = ? || create_by = ? ORDER BY id desc LIMIT 3 OFFSET ?;";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            ps.setInt(1, uid);
            ps.setInt(2, uid);
            ps.setInt(3, amount);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Report(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getBoolean(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getTimestamp(8),
                        rs.getInt(9),
                        rs.getTimestamp(10),
                        rs.getBoolean(11)));
            }
        } catch (Exception e) {

        }
        return list;
    }

    public List<Withdrawal> getWitdrawalbyUser(int id) {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "SELECT * FROM withdrawals WHERE created_by = ? ORDER BY withdrawal_id DESC";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            //  if(status.equals("")){
            ps.setInt(1, id);
            // }//else{

            //   ps.setString(2,"AND status = " + status);    
            // }
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Withdrawal(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getTimestamp(11),
                        rs.getTimestamp(12),
                        rs.getInt(13)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Withdrawal> getAllWithdrawal() {
        List<Withdrawal> list = new ArrayList<>();
        String sql = "SELECT * FROM withdrawals ORDER BY withdrawal_id DESC ";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Withdrawal(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getTimestamp(11),
                        rs.getTimestamp(12),
                        rs.getInt(13)));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public void InsertWithdrawal(Withdrawal withdrawal) {
        String sql = "INSERT INTO withdrawals (withdrawal_code, status, amount, account_number, account_holder, bank_name, bank_branch, response, created_by, updated_by)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(sql);

            ps.setString(1, withdrawal.getCode());
            ps.setString(2, withdrawal.getStatus());
            ps.setDouble(3, withdrawal.getAmount());
            ps.setString(4, withdrawal.getAccount_number());
            ps.setString(5, withdrawal.getAccount_holder());
            ps.setString(6, withdrawal.getBankname());
            ps.setString(7, withdrawal.getBankbranch());
            ps.setString(8, withdrawal.getResponse());
            ps.setInt(9, withdrawal.getCreated_by());
            ps.setInt(10, withdrawal.getUpdated_by());
            ps.executeUpdate();

        } catch (Exception e) {

        }
    }

    public int getIdWithdrawal() {
        String query = "SELECT MAX(withdrawal_id) AS max_id FROM swp_demo.withdrawals";
        try {
            con = new DBContext().connection; //connect sql
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("max_id"); // Lấy giá trị id từ cột max_id trong ResultSet
                return id;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public void UpdateWithdrawal(String status, String response, int wid) {
        String sql = "UPDATE withdrawals SET status = ?, response = ? WHERE withdrawal_id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setString(2, response);
            ps.setInt(3, wid);
            ps.executeUpdate();
            // Execute the update query         
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Withdrawal getWithdrawalByID(int wid) {
        String sql = "SELECT * FROM swp_demo.withdrawals where withdrawal_id = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, wid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Withdrawal(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getTimestamp(11),
                        rs.getTimestamp(12),
                        rs.getInt(13));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Withdrawal getWithdrawalByCode(String code) {
        String sql = "SELECT * FROM swp_demo.withdrawals where withdrawal_code = ?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Withdrawal(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getTimestamp(11),
                        rs.getTimestamp(12),
                        rs.getInt(13));

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public HistoryTransaction InsertHistory_Transaction(double money, String Transaction_type, boolean status, String note, int create_by, int receiver) {
        String query = "INSERT INTO `swp_demo`.`history_transaction` (`Money_Transaction`, `Transaction_Type`, `Status`,`Note`, `Create_by`, `receiver`) \n"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(query);
            ps.setDouble(1, money);
            ps.setString(2, Transaction_type);
            ps.setBoolean(3, status);
            ps.setString(4, note);
            ps.setInt(5, create_by);
            ps.setInt(6, receiver);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public List<HistoryTransaction> GetHistory_TransactionbyID(int uid) {
        List<HistoryTransaction> list = new ArrayList<>();
        String sql = "SELECT *FROM swp_demo.history_transaction h \n"
                + "WHERE h.receiver = ?;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HistoryTransaction(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getTimestamp(7),
                        rs.getInt(8),
                        rs.getTimestamp(9)
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Cart> getCartByUserID(int userID) {
        List<Cart> list = new ArrayList<>();
        String sql = "SELECT *FROM swp_demo.cart\n"
                + "WHERE userID = ?;";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Cart(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return list;
    }

    public HistoryTransaction GetHistoryby_ID(int uid) {
        String sql = "SELECT *FROM swp_demo.history_transaction h \n"
                + "WHERE h.id=?";
        try {
            con = new DBContext().connection;
            ps = con.prepareStatement(sql);
            ps.setInt(1, uid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new HistoryTransaction(
                        rs.getInt(1),
                        rs.getDouble(2),
                        rs.getString(3),
                        rs.getBoolean(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getTimestamp(7),
                        rs.getInt(8),
                        rs.getTimestamp(9)
                );
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public boolean isProductInCart(int userID, int productID) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            con = new DBContext().connection; // Phương thức này cần được triển khai để lấy kết nối đến cơ sở dữ liệu
            String sql = "SELECT COUNT(*) AS count FROM cart WHERE userID = ? AND productID = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, userID);
            stmt.setInt(2, productID);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                if (count > 0) {
                    // Sản phẩm đã có trong giỏ hàng của người dùng
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ SQL ở đây
        } finally {
            // Đóng ResultSet, PreparedStatement và Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý ngoại lệ khi đóng kết nối ở đây
            }
        }

        return result;
    }

    public int getQuantityProductInCart(int userID) {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT COUNT(*) AS count FROM cart WHERE userID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return count;

    }

    public static void main(String[] args) {
        DAO dao = new DAO();

        List<Withdrawal> listWithdrawal = dao.getAllWithdrawal();
        for (Withdrawal withdrawal : listWithdrawal) {
            System.out.println(dao.getUserById(withdrawal.getCreated_by()).getDisplay_name());
        }
    }
}
