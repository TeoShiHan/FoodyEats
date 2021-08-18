package Classes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Review {
    // rating should be int or string???
    private String id,comment,orderId,shopId;
    private LocalDate date;
    private LocalTime time;

    public Review() {
        this("","","","",LocalDate.now(),LocalTime.now());
    }

    public Review(String id, String comment, String orderId, String shopId, LocalDate date, LocalTime time) {
        this.id = id;
        this.comment = comment;
        this.orderId = orderId;
        this.shopId = shopId;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
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
