package Classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Review {    
    private String reviewID,comment,orderID,shopID;
    private LocalDate dateCreated;
    private LocalTime timeCreated;

    public Review() {
        this("","",LocalDate.now(),LocalTime.now(),"","");
    }

    public Review(String reviewID, String comment, LocalDate dateCreated, LocalTime timeCreated, String orderID, String shopID) {
        this.reviewID = reviewID;
        this.comment = comment;
        this.dateCreated = dateCreated;
        this.timeCreated = timeCreated;
        this.orderID = orderID;
        this.shopID = shopID;
    }

    public String getReviewID() {
        return reviewID;
    }
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public String getShopID() {
        return shopID;
    }
    public void setShopID(String shopID) {
        this.shopID = shopID;
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
    public void displayReviewImage() {
        // show normal image
    }
    
}
