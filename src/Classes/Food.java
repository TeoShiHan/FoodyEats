package Classes;

import java.time.LocalDate;

public class Food {
    private String id,name,desc,imgPath,category,shopID;
    private double price;        

    public Food(){
        this("","","","",0.0,"","");
    }     
    public Food(String id, String name, String desc, String imgPath, double price, String category, String shopID){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.price = price;
        this.category = category;
        this.shopID = shopID;        
    }
    public Food(Object id, Object name, Object desc, Object imgPath, Object price, Object category, Object shopID){
        this.id = (String)id;
        this.name = (String)name;
        this.desc = (String)desc;
        this.imgPath = (String)imgPath;
        this.price = (double)price;    
        this.category = (String)category;
        this.shopID = (String)shopID;                    
    }    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
