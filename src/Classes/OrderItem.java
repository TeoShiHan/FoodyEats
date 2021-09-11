package Classes;

import java.util.HashMap;

import Cache.DataHolder;

public class OrderItem {
    private JDBC db = new JDBC();    
    private DataHolder data = DataHolder.getInstance();

    // private SimpleStringProperty orderID,foodID;
    // private SimpleIntegerProperty quantity;
    // this.orderID = new SimpleStringProperty(orderID);
    // this.foodID = new SimpleStringProperty(foodID);
    // this.quantity = new SimpleIntegerProperty(quantity);  

    private String orderID,foodID;
    private int quantity;
    private Food food = new Food();

    public OrderItem(){
        
    }            
    public OrderItem(Object orderID, Object foodID, Object quantity) {
        this.orderID = (String)orderID;
        this.foodID = (String)foodID;
        this.quantity = (int)quantity;                
    }    

    public String getOrderID() {
        return orderID;
    }
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }
    public String getFoodID() {
        return foodID;
    }
    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }
    public void loadFood() {
        HashMap<String,Object> f = db.readOne(String.format("SELECT * FROM `Food` WHERE foodID='%s'",this.foodID));
        this.food = new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID"));        
        data.setFood(this.food);        
    }    
    public double calcAmount(){        
        return quantity*food.getPrice();
    }    
}
