package Cache;
import Classes.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class DataHolder {  
    
    JDBC db = new JDBC();

    private Account account = new Account();  // used for stored logged in user account, whether it is buyer/rider/seller    
    private Buyer buyer = new Buyer();  // used for get the info of specified buyer
    private Rider rider = new Rider();  // used for get the info of specified rider
    private List<Rider> riders = new ArrayList<>();  // used for display the list of rider used for admin
    private Seller seller = new Seller();  // used for get the info of specified seller
    private List<Seller> sellers = new ArrayList<>();  // used for display the list of rider used for admin
    private Admin admin = new Admin();
    private Vehicle vehicle = new Vehicle();
    private Payment payment = new Payment();
    private List<Cart> carts = new ArrayList<>(); 
    private Cart cart = new Cart();
    private List<CartItem> cartItems = new ArrayList<>(); 
    private CartItem cartItem = new CartItem();
    private List<Shop> shops = new ArrayList<>();
    private Shop shop = new Shop();    
    private List<Order> orders = new ArrayList<>();
    private Order order = new Order();
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderItem orderItem = new OrderItem();
    private List<Food> foods = new ArrayList<>();
    private Food food = new Food();
    private List<Review> reviews = new ArrayList<>();
    private ArrayList<HashMap<String,Object>> loginInfo;
    
    
    private ArrayList<HashMap<String,Object>> accountTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> buyerTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> sellerTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> riderTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> adminTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> shopTable = new ArrayList<HashMap<String,Object>>();
    private ArrayList<HashMap<String,Object>> foodCategoriesTable = new ArrayList<HashMap<String,Object>>();


    private HashMap<String,Object> objectHolder = new HashMap<String,Object>();
    private HashMap<String,String> stringHolder = new HashMap<String,String>();
    private HashMap<String,Double> doubleHolder = new HashMap<String,Double>();
    private HashMap<String,Boolean> booleanHolder = new HashMap<String,Boolean>();
    private final static DataHolder INSTANCE = new DataHolder();
    private DataHolder() {}
    
    //#region : GETTER AND SETTER
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

    public List<Rider> getRiders() {
      return riders;
    }
    public void setRiders(List<Rider> riders) {
      this.riders = riders;
    }
    
    public void setRider(Rider rider) {
      this.rider = rider;
    }    
    public Rider getRider() {
      return this.rider;
    }  

    public List<Seller> getSellers() {
      return sellers;
    }
    public void setSellers(List<Seller> sellers) {
      this.sellers = sellers;
    }
    
    public void setSeller(Seller seller) {
      this.seller = seller;
    }    
    public Seller getSeller() {
      return this.seller;
    }
    
    public Admin getAdmin() {
      return admin;
    }
    public void setAdmin(Admin admin) {
      this.admin = admin;
    }

    public Vehicle getVehicle() {
      return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
      this.vehicle = vehicle;
    }    

    public Payment getPayment() {
      return payment;
    }

    public void setPayment(Payment payment) {
      this.payment = payment;
    }

    public List<Cart> getCarts() {
      return carts;
    }
    public void setCarts(List<Cart> carts) {
      this.carts = carts;
    }

    public Cart getCart() {
      return cart;
    }
    public void setCart(Cart cart) {
      this.cart = cart;
    }

    public List<CartItem> getCartItems() {
      return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
      this.cartItems = cartItems;
    }

    public CartItem getCartItem() {
      return cartItem;
    }
    public void setCartItem(CartItem cartItem) {
      this.cartItem = cartItem;
    }

    public List<Shop> getShops() {
      return shops;
    }
    public void setShops(List<Shop> shops) {
      this.shops = shops;
    }

    public Shop getShop() {
      return shop;
    }
    public void setShop(Shop shop) {
      this.shop = shop;
    }

    public List<Order> getOrders(){
      return orders;
    }    
    public void setOrders(List<Order> orders) {
      this.orders = orders;
    }

    public Order getOrder(){
      return order;
    }
    public void setOrder(Order order){
      this.order = order;
    }

    //#region : TABLES
    public ArrayList<HashMap<String, Object>> getSellerTable() {
      return sellerTable;
    }

    public void setSellerTable(ArrayList<HashMap<String, Object>> sellerTable) {
      this.sellerTable = sellerTable;
    }

    public ArrayList<HashMap<String, Object>> getRiderTable() {
      return riderTable;
    }

    public void setRiderTable(ArrayList<HashMap<String, Object>> riderTable) {
      this.riderTable = riderTable;
    }

    public ArrayList<HashMap<String, Object>> getAdminTable() {
      return adminTable;
    }

    public void setAdminTable(ArrayList<HashMap<String, Object>> adminTable) {
      this.adminTable = adminTable;
    }

    public ArrayList<HashMap<String,Object>> getAccountTable() {
      return accountTable;
    }

    public void setAccountTable(ArrayList<HashMap<String,Object>> accountTable) {
      this.accountTable = accountTable;
    }

    public ArrayList<HashMap<String,Object>> getBuyerTable() {
      return buyerTable;
    }

    public void setBuyerTable(ArrayList<HashMap<String,Object>> buyerTable) {
      this.buyerTable = buyerTable;
    }

    public ArrayList<HashMap<String, Object>> getShopTable() {
      return shopTable;
    }

    public void setShopTable(ArrayList<HashMap<String, Object>> shopTable) {
      this.shopTable = shopTable;
    }

    public void setFoodCategoriesTable(ArrayList<HashMap<String, Object>> foodCategoriesTable) {
      this.foodCategoriesTable = foodCategoriesTable;
    }

    public ArrayList<HashMap<String, Object>> getFoodCategoriesTable() {
      return this.foodCategoriesTable;
    }
    //#endregion

    public List<OrderItem> getOrderItems(){
      return orderItems;
    }    
    public void setOrderItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
    }

    public OrderItem getOrderItem(){
      return orderItem;
    }
    public void setOrderItem(OrderItem orderItem){
      this.orderItem = orderItem;
    }    

    public List<Food> getFoods(){
      return foods;
    }
    public void setFoods(ArrayList<Food> foods){
      this.foods = foods;
    }

    public Food getFood(){
      return food;
    }
    public void setFood(Food Food){
      this.food = Food;
    }    

    public List<Review> getReviews() {
      return reviews;
    }
    public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
    }

    public void addObjectHolder(String key,Object value){
      this.objectHolder.put(key, value);
    }

    public void addWholeObjectHolder(HashMap<String,Object> items){
      this.objectHolder.putAll(items);
    }
    public Object getObjectHolder(String key){      
      return objectHolder.get(key);
    }
    public HashMap<String,Object> getWholeObjectHolder(){
      return this.objectHolder;
    }
    //#endregion
    
    //#region StringHolder,DoubleHolder,BooleanHolder, not sure is it needed, maybe ObjectHolder enough?
    // public void addStringHolder(String key,String value){
    //   this.stringHolder.put(key, value);
    // }
    // public String getStringHolder(String key){
    //   String stringValue= stringHolder.get(key);      
    //   return stringValue;
    // }

    // public void addDoubleHolder(String key,double value){
    //   this.doubleHolder.put(key, value);
    // }
    // public double getDoubleHolder(String key){
    //   double doubleValue= doubleHolder.get(key);      
    //   return doubleValue;
    // }

    // public void addBooleanHolder(String key,boolean value){
    //   this.booleanHolder.put(key, value);
    // }
    // public boolean getBooleanHolder(String key){
    //   boolean booleanValue = booleanHolder.get(key);      
    //   return booleanValue;
    // }
    //#endregion
    
    //#region not sure is it needed in here,...
    // since load from database can be done in the classes,
    // can refer to Buyer Class loadOrders() method
    
    // public void loadOrders(){
    //   ArrayList<HashMap<String,Object>> os = db.readAll("SELECT * FROM `Order`");
    //   for(HashMap<String,Object> o : os){
    //     orders.add(new Order(o.get("orderID"),o.get("status"),o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
    //   }
    // }

    // public void loadOrder(String id){
    //   HashMap<String,Object> o = db.readOne(String.format("SELECT * FROM `Order` WHERE orderID='%s'",id));
    //   this.order = new Order(o.get("orderID"),o.get("status"),o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID"));
    // }

    
    // public void loadOrderItems(String id){
    //   ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `OrderItem` WHERE orderID='%s'",id));
    //   for(HashMap<String,Object> o : os){                
    //     orderItems.add(new OrderItem(o.get("orderID"),o.get("foodID"),o.get("quantity")));
    //   }
    // }
         
    // public void loadOrderItem(String id){
    //   HashMap<String,Object> o = db.readOne("SELECT * FROM `OrderItem` WHERE ");
    //   this.orderItem = new OrderItem(o.get("orderID"),o.get("foodID"),o.get("quantity"));
    // }

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
        
    // public void loadFood(String id){
    //   HashMap<String,Object> f = db.readOne(String.format("SELECT * FROM `Food` WHERE foodID='%s'",id));
    //   this.food = new Food(f.get("foodID"),f.get("foodName"),f.get("foodDesc"),f.get("imgPath"),f.get("price"),f.get("category"),f.get("shopID"));
    // }
    //#endregion
    
    
    public void clear(){
      DataHolder.getInstance();
      this.account = new Account();
      this.account = new Admin();
      this.buyer = new Buyer();
      this.rider = new Rider();
      this.seller = new Seller();      
    }

    public void fetchDataFromDatabase() {

    }
  }
