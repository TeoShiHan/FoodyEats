package Classes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Shop {
    private String 
    shopID, name, address, tel, 
    imgPath, foodID, reviewListID;
    private LocalTime startHour, endHour;
    private double deliveryFee;
    private int status;
    private LocalDate dateCreated;
    private ArrayList<Food> food;
    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;
    
     //  CONSTRUCTORS FOR DATABASE OBJECT
     public Shop(){
         
     }

    public Shop(
        Object shopID, 
        Object name,
        Object address, 
        Object tel, 
        Object startHour, 
        Object endHour, 
        Object status,
        Object dateCreated,
        Object deliveryFee,
        Object imgPath
    ){
        this.shopID = (String)shopID;
        this.name = (String)name;
        this.address = (String)address;
        this.tel = (String)tel;
        this.startHour = LocalTime.parse(startHour.toString());
        this.endHour = LocalTime.parse(endHour.toString());
        this.status = (int)status;
        this.dateCreated = LocalDate.parse(dateCreated.toString());
        this.deliveryFee = (double) deliveryFee;
        this.imgPath = (String) imgPath;
    }

    public Shop(
        String shopID, 
        String name,
        String address, 
        String tel, 
        LocalTime startHour, 
        LocalTime endHour, 
        int status,
        LocalDate dateCreated,
        double deliveryFee,
        String imgPath
    ){
        this.shopID = (String)shopID;
        this.name = (String)name;
        this.address = (String)address;
        this.tel = (String)tel;
        this.startHour = (LocalTime)startHour;
        this.endHour = (LocalTime)endHour;
        this.status = (int)status;
        this.dateCreated = (LocalDate)dateCreated;
        this.deliveryFee = (double) deliveryFee;
        this.imgPath = (String) imgPath;
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

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
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
    
    public double getDeliveryFee() {
        return deliveryFee;
    }
    
    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
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

class tester{
    public static void main(String[] args) {
        Shop test = new Shop();
    }
}