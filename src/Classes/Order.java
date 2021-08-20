package Classes;
import Cache.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();

    private String orderID,status,buyerID,riderID,shopID,paymentID,reviewID;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private Review review;
    // private Button button = new Button("Action");
    private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();    

    public Order(){
        this("","",LocalDate.now(),LocalTime.now(),"","","","","");
    }
    
    public Order(String orderID, String status, LocalDate dateCreated, LocalTime timeCreated, String buyerID, String riderID,
                 String shopID, String paymentID, String reviewID) {
        this.orderID = orderID;
        this.status = status;
        this.dateCreated = dateCreated;  
        this.timeCreated = timeCreated;  
        this.buyerID = buyerID;
        this.riderID = riderID;
        this.shopID = shopID;
        this.paymentID = paymentID;
        this.reviewID = reviewID;              
        // this.button.setOnAction(e->{
        //     System.out.println("ID:"+this.ID+"; status:"+this.status);
        // });
    }    

    public Order(Object orderID, Object status, Object dateCreated,  Object timeCreated, Object buyerID, Object riderID, 
                Object shopID, Object paymentID, Object reviewID) {
        this.orderID = (String)orderID;
        this.status = (String)status;
        this.dateCreated = LocalDate.parse((String)dateCreated);  
        this.timeCreated = LocalTime.parse((String)timeCreated,DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.buyerID = (String)buyerID;
        this.riderID = (String)riderID;
        this.shopID = (String)shopID;
        this.paymentID = (String)paymentID;
        this.reviewID = (String)reviewID;
        // this.button.setOnAction(e->{
        //     System.out.println("ID:"+this.ID+"; status:"+this.status);
        // });
    }

    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public String getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getRiderID() {
        return riderID;
    }
    public void setRiderID(String riderID) {
        this.riderID = riderID;
    }

    public String getShopID() {
        return shopID;
    }
    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getPaymentID() {
        return paymentID;
    }
    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getReviewID() {
        return reviewID;
    }
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
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
            ArrayList<HashMap<String,Object>> ois = db.readAll(String.format("SELECT * FROM `OrderItem` WHERE orderID='%s'",orderID));
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
