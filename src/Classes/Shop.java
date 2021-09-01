package Classes;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    
    //region  : VARIABLES
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
    //#endregion
    
    //region  : CONSTRUCTORS
     public Shop(){
         this("","","","",null, null,0,null,0.0,"");
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

    public Shop(HashMap<String,Object>shop){
        this.shopID = (String) shop.get("shopID");
        this.name = (String) shop.get("shopName");
        this.address = (String) shop.get("address");
        this.tel = (String) shop.get("tel");
        this.startHour = (LocalTime) shop.get("startHour");
        this.endHour = (LocalTime) shop.get("endHour");
        this.status = (int) shop.get("status");
        this.dateCreated = (LocalDate) shop.get("dateCreated");
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
    //#endregion

    //#region : METHODS
    public void addFood(){

    }

    public static ArrayList<String> getAvailableFoodCategoryInShop(String shopID){
        JDBC db = new JDBC();
        ArrayList<String> categoryArr = new ArrayList<String>();
        ArrayList<HashMap<String,Object>> categoryList = db.readAll(String.format(
            "SELECT DISTINCT category " +
            "FROM Food F, Shop S "      +
            "WHERE S.shopID = F.shopID AND S.shopID = '%s'", shopID)
        );
        for(int i = 0 ; i < categoryList.size() ; i++){
            categoryArr.add((String)categoryList.get(i).get("category"));
        }
        return categoryArr;
    }

    public static HashMap<String,ArrayList<Food>> getFoodObjArrThatMapWithCategory(String shopID){
        JDBC db = new JDBC();
        ArrayList<String> categoryArr = new ArrayList<String>();
        HashMap<String,ArrayList<Food>> categorizedFood = new HashMap<String,ArrayList<Food>>();
        
        ArrayList<HashMap<String,Object>> foodTable = db.readAll(String.format(
            "SELECT foodID, foodName, foodDesc, price,  F.imgPath, category " +
            "FROM Food F, Shop S "      +
            "WHERE S.shopID = F.shopID AND S.shopID = '%s'", shopID)
        );

        System.out.println(foodTable);
        System.out.println("test fetch category : " + foodTable.get(0).get("category"));
        categoryArr = getAvailableFoodCategoryInShop(shopID);
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
                    tempFoodArr.add(tempFoodObj);
                }
                categorizedFood.put(categoryArr.get(i),tempFoodArr);
            }
        }
        return categorizedFood;
    }
    //#endregion

}


class tester{
    public static void main(String[] args) {
        System.out.println(Shop.getFoodObjArrThatMapWithCategory("S00001"));
    }
}