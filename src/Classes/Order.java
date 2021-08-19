package Classes;
import Cache.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.DateFormatter;

import javafx.scene.control.Button;

public class Order {
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();

    private String id,status,buyerId,riderId,shopId,paymentId,reviewId;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private Review review;
    // private Button button = new Button("Action");
    private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();    

    public Order(){
        this("","",LocalDate.now(),LocalTime.now(),"","","","","");
    }
    
    public Order(String id, String status, LocalDate dateCreated, LocalTime timeCreated, String buyerId, String riderId,
                 String shopId, String paymentId, String reviewId) {
        this.id = id;
        this.status = status;
        this.dateCreated = dateCreated;  
        this.timeCreated = timeCreated;  
        this.buyerId = buyerId;
        this.riderId = riderId;
        this.shopId = shopId;
        this.paymentId = paymentId;
        this.reviewId = reviewId;              
        // this.button.setOnAction(e->{
        //     System.out.println("id:"+this.id+"; status:"+this.status);
        // });
    }    

    public Order(Object id, Object status, Object dateCreated,  Object timeCreated, Object buyerId, Object riderId, 
                Object shopId, Object paymentId, Object reviewId) {
        this.id = (String)id;
        this.status = (String)status;
        this.dateCreated = LocalDate.parse((String)dateCreated);  
        this.timeCreated = LocalTime.parse((String)timeCreated,DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.buyerId = (String)buyerId;
        this.riderId = (String)riderId;
        this.shopId = (String)shopId;
        this.paymentId = (String)paymentId;
        this.reviewId = (String)reviewId;
        // this.button.setOnAction(e->{
        //     System.out.println("id:"+this.id+"; status:"+this.status);
        // });
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }    

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalTime getTimeCreated() {
        return timeCreated;
    }
    public void setTimeCreated(LocalTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getReviewId() {
        return reviewId;
    }
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }    
    
    public Review getReview() {
        return review;
    }
    public void setReview(Review review) {
        this.review = review;
    }       

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    public void loadOrderItems() {
        if(orderItems.isEmpty()){
            ArrayList<HashMap<String,Object>> ois = db.readAll(String.format("SELECT * FROM `OrderItem` WHERE orderID='%s'",id));
            for(HashMap<String,Object> oi : ois){            
                OrderItem o = new OrderItem(oi.get("orderID"),oi.get("foodID"),oi.get("quantity"));
                o.loadFood();
                orderItems.add(o);
            }                    
        }        
        data.setOrderItems(this.orderItems);
    }

    // public Button getButton() {
    //     return button;
    // }

    // public void setButton(Button button) {
    //     this.button = button;
    // }    

    public double getTotalAmount(){        
        double totalAmount = 0.0;
        for(OrderItem orderItem: orderItems){
            totalAmount += orderItem.getAmount();
        }
        return totalAmount;
    }
}
