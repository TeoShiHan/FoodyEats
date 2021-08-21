package Classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Review {    
    private String reviewID,comment,orderID,shopID;
    private LocalDate date;
    private LocalTime time;

    public Review() {
        this("","","","",LocalDate.now(),LocalTime.now());
    }

    public Review(String reviewID, String comment, String orderID, String shopID, LocalDate date, LocalTime time) {
        this.reviewID = reviewID;
        this.comment = comment;
        this.orderID = orderID;
        this.shopID = shopID;
        this.date = date;
        this.time = time;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public void displayReviewImage() {
        // show normal image
    }
    
}
