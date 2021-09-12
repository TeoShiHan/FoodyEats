package Classes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Cache.GUI;

public class Food {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private String foodID,name,desc,imgPath,category,shopID;
    private double price;        

    public Food(){
        this("","","","",0.0,"","");
    }

    public Food(HashMap<String,Object> foodMap){
        
    }

    // get from database table
    public Food(Object foodID, Object name, Object desc, Object imgPath, Object price, Object category, Object shopID){
        this.foodID = (String)foodID;
        this.name = (String)name;
        this.desc = (String)desc;
        this.imgPath = (String)imgPath;
        this.price = (double)price;    
        this.category = (String)category;
        this.shopID = (String)shopID;                    
    }    
    // for creating food
    public Food(String name, String desc, double price, String category, String shopID){
        this.name = name;
        this.desc = desc;        
        this.price = price;
        this.category = category;
        this.shopID = shopID;        
    }

    //  GETTER AND SETTER
    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
  
    public String getShopID() {
        return shopID;
    }
    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void create() throws SQLException{                        
        db.executeCUD(String.format("INSERT INTO `Food` VALUES ('%s','%s','%s','%s',%.2f,'%s','%s')",foodID,name,desc,imgPath,price,category,shopID),gui);
    }    

    public void edit(String name, String desc, double price, String category, String imgPath){        
        this.name = name;
        this.desc = desc;        
        this.price = price;
        this.category = category;
        this.imgPath = imgPath;
        db.executeCUD(String.format("UPDATE `Food` SET foodName='%s', foodDesc='%s', price=%.2f, category='%s',imgPath='%s' WHERE foodID='%s'",name,desc,price,category,imgPath,foodID),gui);
    }    

    public void delete(){
        db.executeCUD(String.format("DELETE FROM `Food` WHERE foodID='%s'",foodID),gui);
    }

    public void addThisFoodToCart(Buyer buyer, int quantity){
        db.executeCUD(String.format("INSERT INTO `CartItem` VALUES ('%s','%s','%s')",buyer.getCartID(), this.foodID, quantity),gui);
        buyer.getCart().setShopID(this.shopID);
    }

    public int getFoodQtyAddedByUserIntoCart(Buyer buyer){
        HashMap<String, Object> purchasedQuantity;
        
        String sqlStatement = String.format(
            "SELECT quantity "     +
            "FROM CartItem " +
            "WHERE cartID = '%s' AND foodID = '%s';",
            buyer.getCartID(),this.foodID
        );
        /*DEBUG MSG*/System.out.println("SQL STATEMENT IS: " + sqlStatement);

        purchasedQuantity = db.readOne(sqlStatement);

        System.out.println(purchasedQuantity);

        if(purchasedQuantity == null){
            return 0;
        }
        else{
            return (int)purchasedQuantity.get("quantity");
        }
    }
}
