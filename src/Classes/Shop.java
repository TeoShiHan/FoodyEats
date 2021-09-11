package Classes;
import Cache.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Cache.DataHolder;
import ListenerInterfaces.TableDataProcessing;
import SQL.CreateTableQuery.SQL;
import javafx.concurrent.Task;

public class Shop implements TableDataProcessing{
    private SQL sql = SQL.getInstance();
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();
    
    //region  : CLASS FIELD
    private String shopID, name, address, tel, imgPath;
    private LocalTime startHour, endHour;
    private double deliveryFee;
    private int status;
    private LocalDate dateCreated;
    private ArrayList<Food> foods;
    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;
    private ArrayList<String> availableFoodCategories;
    //#endregion
    
    //region  : CONSTRUCTORS
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

    public Shop(HashMap<String,Object>shop){
        this.shopID = (String) shop.get("shopID");
        this.name = (String) shop.get("shopName");
        this.address = (String) shop.get("address");
        this.tel = (String) shop.get("tel");
        this.startHour = LocalTime.parse((String)shop.get("startHour"));
        this.endHour = LocalTime.parse((String)shop.get("endHour"));
        this.status = (int) shop.get("status");
        this.dateCreated = LocalDate.parse((String)shop.get("dateCreated"));
        this.deliveryFee = (double) shop.get("deliveryFee");
        this.imgPath = (String) shop.get("imgPath");
    }
    //#endregion

    //#region ：GETTER　AND SETTER
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

    public static String getNextID() {
        JDBC db = new JDBC();
        String nextShopID = "";
        try {
            nextShopID = db.getNextId("Shop");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextShopID;
    }

    public ArrayList<String> getAvailableFoodCategoryInShop(){
        return this.availableFoodCategories;
    };
    //#endregion

    //#region : METHODS
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
        db.executeCUD(String.format("UPDATE `Shop` SET status=1 WHERE shopID='%s'",shopID),gui);
    }

    public void closeShop(){
        this.status=0;
        db.executeCUD(String.format("UPDATE `Shop` SET status=0 WHERE shopID='%s'",shopID),gui);
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

    public void acceptOrder(String orderID){        
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Accepted' WHERE orderID='%s'",orderID),gui);        
    }

    public void declineOrder(String orderID){
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Declined' WHERE orderID='%s'",orderID),gui);
    }

    public void readyOrder(String orderID){        
        db.executeCUD(String.format("UPDATE `Order` SET status='Seller Ready' WHERE orderID='%s'",orderID),gui);
    }
    
    public void loadReviews(){
        this.reviews = new ArrayList<>();
        ArrayList<HashMap<String,Object>> rs = db.readAll(String.format("SELECT r.*,a.username FROM `Review` r,`Order` o,`Buyer` b,`Account` a WHERE r.shopID='%s' AND r.orderID=o.orderID AND o.buyerID=b.buyerID AND b.accountID=a.accountID",shopID));
        for(HashMap<String,Object> r : rs){
            Review review = new Review(r.get("reviewID"), r.get("rating"), r.get("comment"), r.get("dateCreated"), r.get("timeCreated"), r.get("orderID"), r.get("shopID"));
            review.setBuyerUserName((String)r.get("username"));
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
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException, SQLException {                                
                db.executeCUD(String.format("UPDATE `Shop` SET shopName='%s', address='%s', tel='%s', startHour='%s', endHour='%s', deliveryFee=%.2f, imgPath='%s' WHERE shopID='%s'",name,address,tel,startHour,endHour,deliveryFee,imgPath,shopID),gui);
                return null;
            }
        };        
        new Thread(task).start();
    }     

    public void initializeAvailableFoodCategoryInShop(){
        /*DEBUG MSG*/System.out.println("RUNNED INITIALIZE AVAILABLE FOOD CATEGORY");
        /*DEBUG MSG*/System.out.println("RUNNED CATEGORY TABLE");

        /*DEBUG MSG*/System.out.println("FOOD CATEGORY DATA IS : " + data.getFoodCategoriesTable());
        
        HashMap<String,ArrayList<String>> keyMapCategoryArr = getArrMapToKey(data.getFoodCategoriesTable(), "shopID", "category");
        /*DEBUG MSG*/System.out.println("keyMapCategoryArr IS " + keyMapCategoryArr);        

        /*DEBUG MSG*/System.out.println("SHOP ID IS " + this.shopID);        
        this.availableFoodCategories = keyMapCategoryArr.get(this.shopID);
            /*DEBUG MSG*/System.out.println("FOOD CATEGORY : " + this.getAvailableFoodCategoryInShop());
    }

    public HashMap<String,ArrayList<Food>> getFoodObjArrThatMapWithCategory(){
        JDBC db = new JDBC();
        ArrayList<String> categoryArr = new ArrayList<String>();
        HashMap<String,ArrayList<Food>> categorizedFood = new HashMap<String,ArrayList<Food>>();
        
        ArrayList<HashMap<String,Object>> foodTable = db.readAll(String.format(
            "SELECT foodID, foodName, foodDesc, price,  F.imgPath, category, S.shopID "  +
            "FROM Food F, Shop S "      +
            "WHERE S.shopID = F.shopID AND S.shopID = '%s'", this.shopID)
        );

        System.out.println(foodTable);
        System.out.println("test fetch category : " + foodTable.get(0).get("category"));
        
        categoryArr = getAvailableFoodCategoryInShop();
        
        System.out.println("test fetch categoryV2 : " + categoryArr.get(0));

        for(int i = 0 ; i < categoryArr.size() ; i++){
            
            ArrayList<Food> tempFoodArr = new ArrayList<Food>();
            
            for(int t = 0 ; t < foodTable.size() ; t++){
    
                Object foodCategoryObj = foodTable.get(t).get("category");
                String foodCategoryStr = categoryArr.get(i);

                if (foodCategoryObj.toString().equals(foodCategoryStr.toString())){
                    
                    System.out.println("go in if clause");
                    
                    Food tempFoodObj = new Food();
                    tempFoodObj.setFoodID((String)foodTable.get(t).get("foodID"));
                    tempFoodObj.setName((String)foodTable.get(t).get("foodName"));
                    tempFoodObj.setDesc((String)foodTable.get(t).get("foodDesc"));
                    tempFoodObj.setImgPath((String)foodTable.get(t).get("imgPath"));
                    tempFoodObj.setPrice((Double)foodTable.get(t).get("price"));
                    tempFoodObj.setCategory((String)foodTable.get(t).get("category"));
                    tempFoodObj.setShopID((String)foodTable.get(t).get("shopID"));
                    tempFoodArr.add(tempFoodObj);
                }
                categorizedFood.put(categoryArr.get(i),tempFoodArr);
            }
        }
        return categorizedFood;
    }
    

    //#region : TABLE PROCESSING METHODS
    public ArrayList<String> getAllKeysInTable(ArrayList<HashMap<String,Object>>shopTable){
        ArrayList<String> shopKeysArr = new ArrayList<String>();
        for(int i = 0 ; i < shopTable.size() ; i++){
            String tempShopID = (String)shopTable.get(i).get("shopID");
            shopKeysArr.add(tempShopID);
        }
        return shopKeysArr;
    }
    
    public boolean isMatch(String str1, String str2){
        return str1.equals(str2);
    }
    
    public HashMap<String,ArrayList<String>> getArrMapToKey(ArrayList<HashMap<String,Object>>table, String keyName, String ...fieldNames){
        
        //#region : VARIABLES
        
        /*DEBUG MSG*/System.out.println("TABLE IS: " + table);

        /*DEBUG MSG*/System.out.println("RUNNED getArrMapToKey()");
        int tableSize = table.size();
            /*DEBUG MSG*/System.out.println("TABLE SIZE IS : " + tableSize);
        int colNum = fieldNames.length;
        
        ArrayList<String> tempDataArr = new ArrayList<String>();
        HashMap<String,ArrayList<String>> arrMapToKey = new HashMap<String,ArrayList<String>>();
        String tempFieldData = "";
        String tempKeyNew = "";
        String tempKeyOld = "";
        //#endregion

        for(int i = 0 ; i < tableSize  ; i++){
            
            tempKeyNew = (String)table.get(i).get(keyName);
                /*DEBUG MSG*/System.out.println("tempKeyNewE IS : " + tempKeyNew);

            if (i == 0) {
                
                tempKeyOld = tempKeyNew;
                    /*DEBUG MSG*/System.out.println("RUNNED INTO LOGIC I == 0");
                    /*DEBUG MSG*/System.out.println("tempKeyNew : " + tempKeyNew);
                    /*DEBUG MSG*/System.out.println("tempKeyOld : " + tempKeyOld);
                            
            }else{
                tempKeyOld = (String)table.get(i - 1).get(keyName);
                /*DEBUG MSG*/System.out.println("NOT RUN INTO LOGIC I == 0");
                /*DEBUG MSG*/System.out.println("tempKeyOld : " + tempKeyOld);
            }

            if(!isMatch(tempKeyNew,tempKeyOld)){
                arrMapToKey.put(tempKeyOld, tempDataArr);
                /*DEBUG MSG*/System.out.println("arrMapToKey : " + arrMapToKey);

                tempDataArr = new ArrayList<String>();
            }

            for(int r = 0 ; r < colNum ; r++){
                tempFieldData = (String) table.get(i).get(fieldNames[r]);
                    /*DEBUG MSG*/System.out.println("KEY = " + tempKeyOld);
                    /*DEBUG MSG*/System.out.println("tempFieldData = " + tempFieldData);

                tempDataArr.add(tempFieldData);
                    /*DEBUG MSG*/System.out.println("ADDED");
            }
            /*DEBUG MSG*/System.out.println("ARRAY ELEMENT : " + tempDataArr);
        }
        arrMapToKey.put(tempKeyOld, tempDataArr);
                /*DEBUG MSG*/System.out.println("ARR MAP TO KEY IS : " + arrMapToKey);
        return arrMapToKey;
    }

    public void saveSelectedShopIdToDataHolder(){
        data.setSelectedShopID(this.shopID);
    }

    public ArrayList<Shop> createShopObjectCollection(){
        ArrayList<Shop> shopObjectCollection = new ArrayList<Shop>();
        ArrayList<HashMap<String,Object>> shopTable = data.getShopTable();
        for(int i = 0; i < shopTable.size(); i++){
                /*DEBUG OUTPUT*/System.out.println("Runned");

            String shopID      = (String)  shopTable.get(i).get("shopID");
            String shopName    = (String)  shopTable.get(i).get("shopName");
            double deliveryFee = (double)  shopTable.get(i).get("deliveryFee");
            String imgPath     = (String)  shopTable.get(i).get("imgPath");
            System.out.println(imgPath);

            System.out.println("pass through getting value assignatoin");

            Shop shopInstance = new Shop();
                /*DEBUG OUTPUT*/System.out.println("successfully create shop instance");

            shopInstance.setShopID(shopID);
            shopInstance.setName(shopName);
            shopInstance.setDeliveryFee(deliveryFee);
            shopInstance.setImgPath(imgPath);
            shopInstance.initializeAvailableFoodCategoryInShop();
                /*DEBUG MSG*/System.out.println("SHOP INSTANCE : " + shopInstance.getAvailableFoodCategoryInShop());

            shopObjectCollection.add(shopInstance);
            System.out.println(shopObjectCollection);
        }
        return shopObjectCollection;
    }
    //#endregion
    
    //#endregion

    public static void main(String[] args) {
        
    }
}