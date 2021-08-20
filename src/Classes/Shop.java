package Classes;
import java.time.LocalDate;
import java.util.ArrayList;

public class Shop {
    private String 
    shopID, name, address, tel, imgPath, 
    startHour, endHour, foodID, reviewListID;
    private Double deliveryFee;
    private boolean status;
    private LocalDate dateCreated;
    private ArrayList<Food> food;
    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;
    
     //  CONSTRUCTORS FOR DATABASE OBJECT
    public Shop(
        Object shopID, 
        Object address, 
        Object tel, 
        Object startHour, 
        Object endHour, 
        Object status,
        Object dateCreated,
        Object deliveryFee
    ){
        this.shopID = (String)shopID;
        this.address = (String)address;
        this.tel = (String)tel;
        this.startHour = (String)startHour;
        this.endHour = (String)endHour;
        this.status = (Boolean)status;
        this.dateCreated = (LocalDate)dateCreated;
        this.deliveryFee = (Double) deliveryFee;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getReviewListID() {
        return reviewListID;
    }

    public void setReviewListID(String reviewListID) {
        this.reviewListID = reviewListID;
    }

    public boolean isStatus() {
        return status;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public ArrayList<Food> getFood() {
        return food;
    }

    public void setFood(ArrayList<Food> food) {
        this.food = food;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    //  METHODS
    public void addFood(){

    }    
}
