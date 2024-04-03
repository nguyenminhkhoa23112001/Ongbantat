/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Nguyen Minh Khoa
 */
public class ReportOrderProduct {
    private Report report;
    private intermediateOrders order;
    private Product product;

    public ReportOrderProduct() {
    }

    public ReportOrderProduct(Report report, intermediateOrders order, Product product) {
        this.report = report;
        this.order = order;
        this.product = product;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public intermediateOrders getOrder() {
        return order;
    }

    public void setOrder(intermediateOrders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
