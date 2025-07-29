package ao.jose.modell;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Sales {
    private int saleId;
    private int clientId;
    private int productId;
    private int staffId;
    private int quantity;
    private BigDecimal priceEach;
    private BigDecimal totalPrice; // STORED GENERATED (não precisa inserir manualmente)
    private LocalDateTime saleDate;
    private String paymentMethod;


    public Sales() {
        // Default constructor
    }
    // Constructor for creating a new sale with minimum details
    public Sales(int clientId, int productId, int staffId, int quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.staffId = staffId;
        this.quantity = quantity;
    }
    // Constructor for creating a new sale with all details
    public Sales(int saleId, int clientId, int productId, int staffId, int quantity,
                BigDecimal priceEach, BigDecimal totalPrice, LocalDateTime saleDate, String paymentMethod) {
        this.saleId = saleId;
        this.clientId = clientId;
        this.productId = productId;
        this.staffId = staffId;
        this.quantity = quantity;
        this.priceEach = priceEach;
        this.totalPrice = totalPrice;
        this.saleDate = saleDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
