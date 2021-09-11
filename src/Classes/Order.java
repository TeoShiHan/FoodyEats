package Classes;
import Cache.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();

    protected String orderID,status,buyerID,riderID,shopID,paymentID,reviewID;
    protected LocalDate dateCreated;
    protected LocalTime timeCreated;
    protected Review review;    
    protected ArrayList<OrderItem> orderItems;        
    private static boolean ordersHaveChange = false;

    public Order(){
        
    }

    // for converting cart to order and insert into mysql database
    public Order(String status, LocalDate dateCreated, LocalTime timeCreated, String buyerID, String shopID){
        this.status = status;
        this.dateCreated = dateCreated;
        this.timeCreated = LocalTime.parse(timeCreated.format(DateTimeFormatter.ofPattern("HH:mm:ss"))); 
        this.buyerID = buyerID;
        this.shopID = shopID;
    }

    public Order(Object orderID, Object status, Object dateCreated,  Object timeCreated, Object buyerID, Object riderID, 
                Object shopID, Object paymentID, Object reviewID) {
        this.orderID = (String)orderID;
        this.status = (String)status;
        this.dateCreated = LocalDate.parse(dateCreated.toString());  
        this.timeCreated = LocalTime.parse(timeCreated.toString(),DateTimeFormatter.ofPattern("HH:mm:ss")); //Default format is like HH:mm:ss.ns, example: 13:28:04.177786900
        this.buyerID = (String)buyerID;
        this.riderID = (String)riderID;
        this.shopID = (String)shopID;
        this.paymentID = (String)paymentID;
        this.reviewID = (String)reviewID;        
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
        this.timeCreated = LocalTime.parse(timeCreated.format(DateTimeFormatter.ofPattern("HH:mm:ss"))); 
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
    
    // public Buyer getBuyer() {
    //     return buyer;
    // }

    // public void setBuyer(Buyer buyer) {
    //     this.buyer = buyer;
    // }

    // public Rider getRider() {
    //     return rider;
    // }

    // public void setRider(Rider rider) {
    //     this.rider = rider;
    // }

    // public Shop getShop() {
    //     return shop;
    // }

    // public void setShop(Shop shop) {
    //     this.shop = shop;
    // }
    
    public static boolean isOrdersHaveChange() {
        return ordersHaveChange;
    }

    public static void setOrdersHaveChange(boolean ordersHaveChange) {
        Order.ordersHaveChange = ordersHaveChange;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(ArrayList<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
    
    public void loadOrderItems() {        
        orderItems = new ArrayList<OrderItem>();
        // if(loadOrderItemCount++==0 && orderItems.isEmpty()){
            ArrayList<HashMap<String,Object>> ois = db.readAll(String.format("SELECT * FROM `OrderItem` WHERE orderID='%s'",orderID));
            for(HashMap<String,Object> oi : ois){            
                OrderItem o = new OrderItem(oi.get("orderID"),oi.get("foodID"),oi.get("quantity"));
                o.loadFood();
                orderItems.add(o);
            }                    
        // }        
        data.setOrderItems(this.orderItems);
    }

    public void loadReview() {
        HashMap<String,Object> r = db.readOne(String.format("SELECT r.* FROM `Review` r, `Order` o WHERE r.reviewID='%s' AND r.reviewID=o.reviewID",reviewID));
        System.out.println(r);
        Review review = new Review(r.get("reviewID"), r.get("rating"), r.get("comment"), r.get("dateCreated"), r.get("timeCreated"), r.get("orderID"), r.get("shopID"));
        this.review = review;        
    }

    public void loadAllDetails(){        
        // if(loadAllDetailsCount++==0){
        // orderItems = new ArrayList<OrderItem>();
        // ArrayList<HashMap<String,Object>> ods = db.readAll(String.format("SELECT s.address AS shopAddress,s.imgPath AS shopImagePath,a.name AS buyerName,b.address AS buyerAddress,f.imgPath AS foodImagePath,s.*,a.*,b.*,oi.*,f.* FROM `Order` o, `Shop` s, `Buyer` b, `OrderItem` oi, `Food` f, `Account` a WHERE o.orderID='%s' AND o.orderID=oi.orderID AND o.shopID=s.shopID AND o.buyerID=b.buyerID AND oi.foodID=f.foodID AND b.accountID=a.accountID",data.getOrder().getOrderID()));
        // System.out.println(ods);
        // this.buyer=new Buyer(ods.get(0).get("accountID"), ods.get(0).get("username"), ods.get(0).get("password"), ods.get(0).get("buyerName"), ods.get(0).get("email"), ods.get(0).get("mobileNo"), ods.get(0).get("accType"), ods.get(0).get("buyerID"), ods.get(0).get("buyerAddress"), ods.get(0).get("cartID"));
        // this.shop=new Shop(ods.get(0).get("shopID"), ods.get(0).get("shopName"), ods.get(0).get("shopAddress"), ods.get(0).get("tel"), ods.get(0).get("startHour"), ods.get(0).get("endHour"), ods.get(0).get("status"), ods.get(0).get("dateCreated"), ods.get(0).get("deliveryFee"),ods.get(0).get("shopImagePath"));
        // if(!(riderID==null||riderID.isEmpty()||riderID.isBlank())){
        //     HashMap<String,Object> r = db.readOne(String.format("SELECT r.*,v.*,a.* FROM `Rider` r, `Vehicle` v, `Account` a WHERE r.riderID='%s' AND r.vehicleID=v.vehicleID AND r.accountID=a.accountID",riderID));
        //     System.out.println(r);
        //     Vehicle vehicle = new Vehicle(r.get("vehicleID"), r.get("type"), r.get("plateNo"), r.get("brand"), r.get("model"), r.get("color"));
        //     Rider rider = new Rider(r.get("accountID"), r.get("username"), r.get("password"), r.get("name"), r.get("email"), r.get("mobileNo"), r.get("accType"), r.get("riderID"), r.get("vehicleID"), r.get("status"));
        //     rider.setVehicle(vehicle);
        //     this.rider = rider;
        // }
        // for(HashMap<String,Object> od : ods){
        //     this.buyer=new Buyer(od.get("accountID"), od.get("username"), od.get("password"), od.get("buyerName"), od.get("email"), od.get("mobileNo"), od.get("accType"), od.get("buyerID"), od.get("buyerAddress"), od.get("cartID"));
        //     this.shop=new Shop(od.get("shopID"), od.get("shopName"), od.get("shopAddress"), od.get("tel"), od.get("startHour"), od.get("endHour"), od.get("status"), od.get("dateCreated"), od.get("deliveryFee"),od.get("shopImagePath"));
        //     Food food=new Food(od.get("foodID"), od.get("foodName"), od.get("foodDesc"), od.get("foodImagePath"), od.get("price"), od.get("category"), od.get("shopID"));            
        //     this.orderItems.add(new OrderItem(od.get("orderID"), od.get("foodID"), od.get("quantity"), food));                            
        // }
        // }

        loadOrderItems();
        data.setOrder(this);
    }

    public double calcTotalAmount(){        
        double totalAmount = 0.0;
        for(OrderItem orderItem: orderItems){
            totalAmount += orderItem.calcAmount();
        }
        return totalAmount;
    }

    public void create() throws SQLException{
        String nextOrderID = db.getNextId("Order");
        String nextPaymentID = db.getNextId("Payment");
        this.orderID = nextOrderID;
        this.paymentID = nextPaymentID;
        // https://stackoverflow.com/questions/5178697/mysql-insert-into-multiple-tables-database-normalization    
        String deleteOrderItemQuery = "";
        String query = "START TRANSACTION; ";        
        query += String.format("INSERT INTO `Order` (orderID,status,dateCreated,timeCreated,buyerID,shopID,paymentID) VALUES ('%s','%s','%s','%s','%s','%s','%s'); ",orderID,status,dateCreated.toString(),timeCreated.toString(),buyerID,shopID,paymentID);        
        query += String.format("INSERT INTO `Payment` (paymentID,method,orderID) VALUES ('%s','%s','%s'); ",paymentID,data.getPayment().getMethod(),orderID);        
        for(CartItem c : data.getCartItems()){
            query += String.format("INSERT INTO `OrderItem` (orderID,foodID,quantity) VALUES ('%s','%s',%d); ",orderID,c.getFood().getFoodID(),c.getQuantity());
            deleteOrderItemQuery += String.format("DELETE FROM `CartItem` WHERE cartID='%s' AND foodID='%s'; ",c.getCartID(),c.getFood().getFoodID());
        }
        query += "COMMIT;";
        System.out.println(query);
        System.out.println(deleteOrderItemQuery);
        db.executeCUD(query,gui);
        db.executeCUD(deleteOrderItemQuery,gui);
        Order.ordersHaveChange = true;
        Cart.setCartHaveChange(true);
    }

    public void delete(){
        db.executeCUD(String.format("DELETE FROM `Order` WHERE orderID='%s'; DELETE FROM `OrderItem` WHERE orderID='%s'",orderID,orderID),gui);
    }
}
