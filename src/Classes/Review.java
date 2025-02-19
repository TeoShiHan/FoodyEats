package Classes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import Cache.GUI;

public class Review {  
    private JDBC db = new JDBC();    
    private GUI gui = GUI.getInstance();
    
    private String reviewID,comment,orderID,shopID;
    private int rating;
    private LocalDate dateCreated;
    private LocalTime timeCreated;
    private String buyerUserName; // for see reviews purpose

    public Review() {
        
    }    

    public Review(Object reviewID, Object rating, Object comment, Object dateCreated, Object timeCreated, Object orderID, Object shopID) {
        this.reviewID = (String)reviewID;
        this.rating = (int)rating;
        this.comment = (String)comment;
        this.dateCreated = LocalDate.parse((String)dateCreated);  
        this.timeCreated = LocalTime.parse((String)timeCreated); //,DateTimeFormatter.ofPattern("HH:mm:ss")
        this.orderID = (String)orderID;
        this.shopID = (String)shopID;
    }

    // To create new review
    public Review(int rating, String comment, String orderID, String shopID){
        this.rating = rating;
        this.comment = comment;
        this.orderID = orderID;
        this.shopID = shopID;        
    }

    public String getReviewID() {
        return reviewID;
    }
    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }    
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
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
    public String getBuyerUserName() {
        return buyerUserName;
    }
    public void setBuyerUserName(String buyerUserName) {
        this.buyerUserName = buyerUserName;
    }

    public void displayReviewImage() {
        // show normal image
    }    

    public void create() throws SQLException{
        this.dateCreated = LocalDate.now();
        this.timeCreated = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));        
        this.reviewID = db.getNextId("Review");            
        String query = "START TRANSACTION; ";
        query += String.format("INSERT INTO `Review`(reviewID,rating,comment,dateCreated,timeCreated,orderID,shopID) VALUES('%s',%d,'%s','%s','%s','%s','%s'); ",reviewID,rating,comment,dateCreated.toString(),timeCreated.toString(),orderID,shopID);
        query += String.format("UPDATE `Order` SET reviewID='%s' WHERE orderID='%s'; ",reviewID,orderID);
        query += "COMMIT; ";
        db.executeCUD(query,gui);
    }
    
}
