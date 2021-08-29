package Classes;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Shop {
    
    //region  : VARIABLES
    private String 
    shopID, name, address, tel, imgPath, 
    startHour, endHour, foodID, reviewListID;
    private Double deliveryFee;
    private boolean status;
    private LocalDate dateCreated;
    private ArrayList<Food> food;
    private ArrayList<Review> reviews;
    private ArrayList<Order> orders;
    //#endregion
    
    //region  : CONSTRUCTORS
     public Shop(){
         this(null,null,null,null,null,false,null,null);
     }

    public Shop(
        Object shopID, 
        Object address, 
        Object tel, 
        Object startHour, 
        Object endHour, 
        Object status,
        Object dateCreated,
        Object deliveryFee
    ){
        this.shopID = (String)shopID;
        this.address = (String)address;
        this.tel = (String)tel;
        this.startHour = (String)startHour;
        this.endHour = (String)endHour;
        this.status = (Boolean)status;
        this.dateCreated = (LocalDate)dateCreated;
        this.deliveryFee = (Double) deliveryFee;
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

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
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

    public boolean isStatus() {
        return status;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setStatus(boolean status) {
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