package Classes;

import java.util.HashMap;

import Cache.DataHolder;

public class OrderItem {
    private JDBC db = new JDBC();    
    private DataHolder data = DataHolder.getInstance();

    // private SimpleStringProperty orderId,foodId;
    // private SimpleIntegerProperty quantity;
    // this.orderId = new SimpleStringProperty(orderId);
    // this.foodId = new SimpleStringProperty(foodId);
    // this.quantity = new SimpleIntegerProperty(quantity);  

    private String orderId,foodId;
    private int quantity;
    private Food food = new Food();

    public OrderItem(){
        this("","",0);
    }    
    public OrderItem(String orderId, String foodId, int quantity) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;        
    }
    public OrderItem(String orderId, String foodId, int quantity, Food food) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;               
        this.food = food;
    }
    public OrderItem(Object orderId, Object foodId, Object quantity) {
        this.orderId = (String)orderId;
        this.foodId = (String)foodId;
        this.quantity = (int)quantity;                
    }
    public OrderItem(Object orderId, Object foodId, Object quantity, Food food) {
        this.orderId = (String)orderId;
        this.foodId = (String)foodId;
        this.quantity = (int)quantity;        
        this.food = food;
    }

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getFoodId() {
        return foodId;
    }
    public void setFoodId(String foodId) {
        this.foodId = foodId;
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
        HashMap<String,Object> f = db.readOne(String.format("SELECT * FROM `Food` WHERE foodID='%s'",this.foodId));
        this.food = new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID"));        
        data.setFood(this.food);        
    }    
    public double getAmount(){        
        return quantity*food.getPrice();
    }    
}
