/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author tudo7
 */
public class ProductOrderPair {
    private Product product;
    private intermediateOrders order;

    public ProductOrderPair() {
    }

    public ProductOrderPair(Product product, intermediateOrders order) {
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public intermediateOrders getOrder() {
        return order;
    }

    public void setOrder(intermediateOrders order) {
        this.order = order;
    }

  
}
