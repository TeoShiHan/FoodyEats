package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.Button;

public class Order {
    private String id,status,buyerId,riderId,shopId,paymentId,reviewId;
    private LocalDate dateCreated;
    private Review review;
    // private Button button = new Button("Action");
    private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();

    public Order(){
        this("","","","","","","",LocalDate.now());
    }
    
    public Order(String id, String status, String buyerId, String riderId, String shopId, String paymentId,
            String reviewId, LocalDate dateCreated) {
        this.id = id;
        this.status = status;
        this.buyerId = buyerId;
        this.riderId = riderId;
        this.shopId = shopId;
        this.paymentId = paymentId;
        this.reviewId = reviewId;
        this.dateCreated = dateCreated;        
        // this.button.setOnAction(e->{
        //     System.out.println("id:"+this.id+"; status:"+this.status);
        // });
    }    

    public Order(Object id, Object status, Object buyerId, Object riderId, Object shopId, Object paymentId,
            Object reviewId, Object dateCreated) {
        this.id = (String)id;
        this.status = (String)status;
        this.buyerId = (String)buyerId;
        this.riderId = (String)riderId;
        this.shopId = (String)shopId;
        this.paymentId = (String)paymentId;
        this.reviewId = (String)reviewId;
        this.dateCreated = (LocalDate)dateCreated;
        // this.button.setOnAction(e->{
        //     System.out.println("id:"+this.id+"; status:"+this.status);
        // });
    }

    public String getId() {
        return id;
    }    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
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
