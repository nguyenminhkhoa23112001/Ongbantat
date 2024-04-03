/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ADMIN
 */
import dao.DAO;
import dao.VNPAYDAO;
import java.util.Queue;
import java.util.LinkedList;

// Class to represent the transaction queue
public class TransactionQueue {
    private Queue<Transaction> queue;
    private DAO dao;
    private VNPAYDAO vnpayDao;
    public TransactionQueue() {
        this.queue = new LinkedList<>();
        this.dao = new DAO(); // Initialize DAO instance
        this.vnpayDao = new VNPAYDAO();
    }

    // Method to add a transaction to the queue
    public void addTransaction(Transaction transaction) {
        
        queue.add(transaction);
    }

    // Method to process transactions in the queue
    public void processTransactions() {
        while (!queue.isEmpty()) {
            Transaction transaction = queue.poll();
            // Process the transaction here (e.g., update database)
            processTransaction(transaction);
        }
    }

    public void processTransactionsComlete() {
        while (!queue.isEmpty()) {
            Transaction transaction = queue.poll();
            // Process the transaction here (e.g., update database)
            processTransactionComplete(transaction);
        }
    }
    
   public void processTransactionsvnpay() {
        while (!queue.isEmpty()) {
            Transaction transaction = queue.poll();
            // Process the transaction here (e.g., update database)
            processTransactionVnpay(transaction);
        }
    }
   
   public void processTransactionsWithdraw() {
        while (!queue.isEmpty()) {
            Transaction transaction = queue.poll();
            // Process the transaction here (e.g., update database)
            processTransactionWithdrawl(transaction);
        }
    }
   
    // Method to process a single transaction
    private void processTransaction(Transaction transaction) {
        // Retrieve user's wallet balance from the database
        double currentBalance = dao.getWallet(transaction.getUserID()).getBalance();
        
        // Retrieve product price from the database
        //double productPrice = dao.getProductByID(transaction.getOrderID()).getPrice();

        // Update wallet balance after deducting product price
        double newBalance = currentBalance - transaction.getAmount();
        
        // Update the user's wallet balance in the database
        dao.updateAmount(newBalance, transaction.getUserID());
        
        // Update transaction status in transaction table
        dao.updateTransactionStatus(transaction.getID(), "Completed");
    }
    
     private void processTransactionVnpay(Transaction transaction) {
        // Retrieve user's wallet balance from the database
        double currentBalance = dao.getWallet(transaction.getUserID()).getBalance();
        
        // Retrieve product price from the database
        //double productPrice = dao.getProductByID(transaction.getOrderID()).getPrice();

        // Update wallet balance after deducting product price
        double newBalance = currentBalance + transaction.getAmount();
        
        // Update the user's wallet balance in the database
        dao.updateAmount(newBalance, transaction.getUserID());
        
        // Update transaction status in transaction table
        dao.updateTransactionStatus(transaction.getID(), "Completed");
        
        vnpayDao.updateStatusVnpay(transaction.getPaymentCode(),dao.getWallet(transaction.getUserID()).getId(), "Completed");
        
    }
     
     private void processTransactionWithdrawl(Transaction transaction) {
        // Retrieve user's wallet balance from the database
        double currentBalance = dao.getWallet(transaction.getUserID()).getBalance();
        
        // Retrieve product price from the database
        //double productPrice = dao.getProductByID(transaction.getOrderID()).getPrice();

        // Update wallet balance after deducting product price
        double newBalance = currentBalance - transaction.getAmount();
        
        // Update the user's wallet balance in the database
        dao.updateAmount(newBalance, transaction.getUserID());
        
        // Update transaction status in transaction table
        dao.updateTransactionStatus(transaction.getID(), "Completed");  
    }
     
     private void processTransactionComplete(Transaction transaction) {
        // Retrieve user's wallet balance from the database
        double currentBalance = dao.getWallet(transaction.getUserID()).getBalance();
        
        // Retrieve product price from the database
        //double productPrice = dao.getProductByID(transaction.getOrderID()).getPrice();

        // Update wallet balance after deducting product price
        double newBalance = currentBalance + transaction.getAmount();
        
        // Update the user's wallet balance in the database
        dao.updateAmount(newBalance, transaction.getUserID());
        
        // Update transaction status in transaction table
        dao.updateTransactionStatus(transaction.getID(), "Completed");
    }
    
}

