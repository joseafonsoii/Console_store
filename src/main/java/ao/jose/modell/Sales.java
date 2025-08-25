package ao.jose.modell;


import java.sql.Timestamp;
import java.math.BigDecimal;

public class Sales{
    private int id;
    private int product_id;
    private int client_id;
    private int quantity;
    private Timestamp sale_date;
    private PaymentMethod payment_method;
    private BigDecimal total_price;


    public Sales(int product_id, int client_id, int quantity,BigDecimal total_price, PaymentMethod payment_method) {
        this.product_id = product_id;
        this.client_id = client_id;
        this.quantity = quantity;
        this.total_price = total_price;
        this.payment_method = payment_method;   
        
    }

    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD, EXPRESS
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; } 
    public int getProductId() { return product_id; }
    public void setProductId(int product_id) { this.product_id = product_id; }
    public int getClientId() { return client_id; }
    public void setClientId(int client_id) { this.client_id = client_id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getTotalPrice() { return total_price; }
    public void setTotalPrice(BigDecimal total_price) { this.total_price = total_price;}
    public Timestamp getSaleDate() { return sale_date; }
    public void setSaleDate(Timestamp sale_date) { this.sale_date = sale_date;}
    public PaymentMethod getPaymentMethod() { return payment_method; }
    public void setPaymentMethod(PaymentMethod payment_method) { this.payment_method = payment_method;}
}