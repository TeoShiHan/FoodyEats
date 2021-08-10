package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    String id,status,buyerId,riderId,shopId,paymentId,reviewId;
    LocalDate dateCreated;
    Review review;
    ArrayList<OrderDetails> orderDetails;

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

    public ArrayList<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    

}
