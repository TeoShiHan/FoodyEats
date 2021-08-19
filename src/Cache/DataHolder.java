package Cache;
import Classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public final class DataHolder {  
    JDBC db = new JDBC();

    private Account account;
    private Buyer buyer;
    private Rider rider;
    // private Seller seller;
    private List<Order> orders = new ArrayList<>();
    private Order order = new Order();
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderItem orderItem = new OrderItem();
    private List<Food> foods = new ArrayList<>();
    private Food food = new Food();
    private HashMap<String,Object> objectHolder = new HashMap<String,Object>();
    private HashMap<String,String> stringHolder = new HashMap<String,String>();
    private HashMap<String,Double> doubleHolder = new HashMap<String,Double>();
    private HashMap<String,Boolean> booleanHolder = new HashMap<String,Boolean>();

    private final static DataHolder INSTANCE = new DataHolder();
    
    private DataHolder() {}
    
    public static DataHolder getInstance() {
      return INSTANCE;
    }
    
    public void setAccount(Account acc) {
      this.account = acc;
    }    
    public Account getAccount() {
      return this.account;
    }

    public void setBuyer(Buyer buyer) {
      this.buyer = buyer;
    }    
    public Buyer getBuyer() {
      return this.buyer;
    }    

    public void setRider(Rider rider) {
      this.rider = rider;
    }    
    public Rider getRider() {
      return this.rider;
    }  

    // public void setSeller(Seller seller) {
    //   this.seller = seller;
    // }    
    // public Seller getSeller() {
    //   return this.seller;
    // }

    public void addObjectHolder(String key,Object value){
      this.objectHolder.put(key, value);
    }
    public Object getObjectHolder(String key){
      Object objectValue= objectHolder.get(key);      
      return objectValue;
    }

    public void addStringHolder(String key,String value){
      this.stringHolder.put(key, value);
    }
    public String getStringHolder(String key){
      String stringValue= stringHolder.get(key);      
      return stringValue;
    }

    public void addDoubleHolder(String key,double value){
      this.doubleHolder.put(key, value);
    }
    public double getDoubleHolder(String key){
      double doubleValue= doubleHolder.get(key);      
      return doubleValue;
    }

    public void addBooleanHolder(String key,boolean value){
      this.booleanHolder.put(key, value);
    }
    public boolean getBooleanHolder(String key){
      boolean booleanValue = booleanHolder.get(key);      
      return booleanValue;
    }

    public List<Order> getOrders(){
      return orders;
    }
    public void setOrders(ArrayList<Order> orders){
      this.orders = orders;
    }
    // public void loadOrders(){
    //   ArrayList<HashMap<String,Object>> os = db.readAll("SELECT * FROM `Order`");
    //   for(HashMap<String,Object> o : os){
    //     orders.add(new Order(o.get("orderID"),o.get("status"),o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
    //   }
    // }
     
    public Order getOrder(){
      return order;
    }
    public void setOrder(Order order){
      this.order = order;
    }
    // public void loadOrder(String id){
    //   HashMap<String,Object> o = db.readOne(String.format("SELECT * FROM `Order` WHERE orderID='%s'",id));
    //   this.order = new Order(o.get("orderID"),o.get("status"),o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID"));
    // }

    public List<OrderItem> getOrderItems(){
      return orderItems;
    }
    public void setOrderItems(ArrayList<OrderItem> orderItems){
      this.orderItems = orderItems;
    }
    // public void loadOrderItems(String id){
    //   ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `OrderItem` WHERE orderID='%s'",id));
    //   for(HashMap<String,Object> o : os){                
    //     orderItems.add(new OrderItem(o.get("orderID"),o.get("foodID"),o.get("quantity")));
    //   }
    // }
     
    public OrderItem getOrderItem(){
      return orderItem;
    }
    public void setOrderItem(OrderItem orderItem){
      this.orderItem = orderItem;
    }
    // public void loadOrderItem(String id){
    //   HashMap<String,Object> o = db.readOne("SELECT * FROM `OrderItem` WHERE ");
    //   this.orderItem = new OrderItem(o.get("orderID"),o.get("foodID"),o.get("quantity"));
    // }

    public List<Food> getFoods(){
      return foods;
    }
    public void setFoods(ArrayList<Food> foods){
      this.foods = foods;
    }
    // public void loadFoods(){
    //   ArrayList<HashMap<String,Object>> fs = db.readAll("SELECT * FROM `Food`");
    //   for(HashMap<String,Object> f : fs){
    //     foods.add(new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID")));
    //   }
    // }
    // public void loadFoods(String whereClause){
    //   ArrayList<HashMap<String,Object>> fs = db.readAll("SELECT * FROM `Food`");
    //   for(HashMap<String,Object> f : fs){
    //     foods.add(new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID")));
    //   }
    // }
     
    public Food getFood(){
      return food;
    }
    public void setFood(Food Food){
      this.food = Food;
    }
    // public void loadFood(String id){
    //   HashMap<String,Object> f = db.readOne(String.format("SELECT * FROM `Food` WHERE foodID='%s'",id));
    //   this.food = new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID"));
    // }
    
    
    public void clear(){
      DataHolder.getInstance();
      this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
    }
  }
