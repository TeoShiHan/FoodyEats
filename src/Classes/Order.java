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

    public Order(Order order){
        this.orderID = order.orderID;
        this.status = order.status;
        this.dateCreated = order.dateCreated;
        this.timeCreated = order.timeCreated;
        this.buyerID = order.buyerID;
        this.riderID =  order.riderID;
        this.shopID = order.shopID;
        this.paymentID = order.paymentID;
        this.reviewID = order.reviewID;
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

    public Order(HashMap<String,Object>orderTable) {
        this.orderID = (String)orderTable.get("orderID");
        this.status = (String)orderTable.get("status");
        this.dateCreated = LocalDate.parse(orderTable.get("dateCreated").toString());
        this.timeCreated = LocalTime.parse(orderTable.get("timeCreated").toString());
        this.buyerID = (String)orderTable.get("buyerID");
        this.riderID = (String)orderTable.get("riderID");
        this.shopID = (String)orderTable.get("shopID");
        this.paymentID = (String)orderTable.get("paymentID");
        this.reviewID = (String)orderTable.get("reviewID");
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
        Review review = new Review(r.get("reviewID"), r.get("rating"), r.get("comment"), r.get("dateCreated"), r.get("timeCreated"), r.get("orderID"), r.get("shopID"));
        this.review = review;        
    }

    public void loadAllDetails(){
        loadOrderItems();
        data.setOrder(this);
    }

    public double calcTotalAmount(){        
        double totalAmount = 0.0;
        for(OrderItem orderItem: orderItems){
            totalAmount += orderItem.calcAmount();
        }        
        for(HashMap<String,Object> shopHashMap : data.getShopTable()){
            if(shopHashMap.get("shopID").equals(shopID)){                
                totalAmount += (double)shopHashMap.get("deliveryFee");
                break;
            }
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
        
        System.out.println("DEBUG PRINT SQL STMT>>>>" + query);

        query += String.format("INSERT INTO `Payment` (paymentID,method,orderID) VALUES ('%s','%s','%s'); ",paymentID,data.getPayment().getMethod(),orderID);        
        for(CartItem c : data.getCartItems()){
            query += String.format("INSERT INTO `OrderItem` (orderID,foodID,quantity) VALUES ('%s','%s',%d); ",orderID,c.getFood().getFoodID(),c.getQuantity());
            deleteOrderItemQuery += String.format("DELETE FROM `CartItem` WHERE cartID='%s' AND foodID='%s'; ",c.getCartID(),c.getFood().getFoodID());
        }
        query += String.format("UPDATE `Cart` SET shopID='' WHERE cartID='%s'; ",data.getBuyer().getCart().getCartID());        
        query += "COMMIT;";        
        db.executeCUD(query,gui);
        db.executeCUD(deleteOrderItemQuery,gui);
        Order.ordersHaveChange = true;
        Cart.setCartHaveChange(true);
    }

    public void delete(){
        db.executeCUD(String.format("DELETE FROM `Order` WHERE orderID='%s'; DELETE FROM `OrderItem` WHERE orderID='%s'",orderID,orderID),gui);
    }
}
