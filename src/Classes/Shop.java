package Classes;
import Cache.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    private String 
    shopID, name, address, tel, 
    imgPath, reviewID;
    private LocalTime startHour, endHour;
    private double deliveryFee;
    private int status;
    private LocalDate dateCreated;
    private ArrayList<Food> foods;
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
        this.startHour = LocalTime.parse((String)startHour);
        this.endHour = LocalTime.parse((String)endHour);
        this.status = (int)status;
        this.dateCreated = LocalDate.parse((String)dateCreated);
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

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
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

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
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
    
    public void loadFoods(){
        this.foods = new ArrayList<>();
        ArrayList<HashMap<String,Object>> fs = db.readAll(String.format("SELECT * FROM `Food` WHERE shopID='%s'",shopID));
        for(HashMap<String,Object> f : fs){
            this.foods.add(new Food(f.get("foodID"), f.get("foodName"), f.get("foodDesc"), f.get("imgPath"), f.get("price"), f.get("category"), f.get("shopID")));
        }
        try{
            data.setFoods(this.foods);
        }catch(Exception e){
            System.out.println("something wrong to set foods to dataholder");
        }
    }

    public void openShop(){
        this.status=1;
        db.executeCUD(String.format("UPDATE `Shop` SET status=1 WHERE shopID='%s'",shopID));
    }

    public void closeShop(){
        this.status=0;
        db.executeCUD(String.format("UPDATE `Shop` SET status=0 WHERE shopID='%s'",shopID));
    }

    public void loadPendingOrders(){
        this.orders = new ArrayList<>();
        // https://stackoverflow.com/questions/19196475/custom-order-by-in-sql-server-like-p-a-l-h (select data order by the custom sequence)
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE shopID='%s' AND status='Pending'",shopID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }
    }

    public void loadAcceptedOrders(){
        this.orders = new ArrayList<>();
        // https://stackoverflow.com/questions/19196475/custom-order-by-in-sql-server-like-p-a-l-h (select data order by the custom sequence)
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE shopID='%s' AND status<>'Pending' ORDER BY CASE WHEN status='Rider Accepted' THEN 1 WHEN status='Seller Accepted' THEN 2 WHEN status='Seller Ready' THEN 3 WHEN status='Rider Collected' THEN 4 WHEN status='Completed' THEN 5 ELSE 6 END ASC",shopID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }
    }

    public void acceptOrder(String oRDerID){        
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Accepted' WHERE orderID='%s'",oRDerID));        
    }

    public void declineOrder(String oRDerID){
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Declined' WHERE orderID='%s'",oRDerID));
    }

    public void readyOrder(String oRDerID){        
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Ready' WHERE orderID='%s'",oRDerID));
    }
    
    public void loadReviews(){
        this.reviews = new ArrayList<>();
        ArrayList<HashMap<String,Object>> rs = db.readAll(String.format("SELECT r.*,a.username FROM `Reviews` r,`Order` o,`Buyer` b,`Account` a WHERE r.shopID='%s' AND r.orderID=o.orderID AND o.buyerID=b.buyerID AND b.accountID=a.accountID",shopID));
        for(HashMap<String,Object> r : rs){
            Review review = new Review(r.get("reviewID"), r.get("rating"), r.get("comment"), r.get("dateCreated"), r.get("timeCreated"), r.get("orderID"), r.get("shopID"));
            review.setBuyerUserName((String)r.get("Username"));
            this.reviews.add(review);
        }
    }
    public double getAverageRating(){
        if(!this.reviews.isEmpty()){
            double total = 0;            
            for(Review r : reviews){
                total+=r.getRating();                
            }
            return total/reviews.size();
        }else{
            return 0.0;
        }
    }

    public void edit(String name, String address, String tel, LocalTime startHour, LocalTime endHour, double deliveryFee, String imgPath){        
        this.name = name;
        this.address = address;
        this.tel = tel;                
        this.startHour = startHour;
        this.endHour = endHour;        
        this.deliveryFee =  deliveryFee;
        this.imgPath =  imgPath;
        db.executeCUD(String.format("UPDATE `Shop` SET shopName='%s', address='%s', tel='%s', startHour='%s', endHour='%s', deliveryFee=%.2f, imgPath='%s' WHERE shopID='%s'",name,address,tel,startHour,endHour,deliveryFee,imgPath,shopID));
    } 
}

class tester{
    public static void main(String[] args) {
        Shop test = new Shop();
    }
}