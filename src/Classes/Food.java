package Classes;

public class Food {
    private String foodID,name,desc,imgPath,category,shopID;
    private double price;        

    public Food(){
        this("","","","",0.0,"","");
    }     
    public Food(String foodID, String name, String desc, String imgPath, double price, String category, String shopID){
        this.foodID = foodID;
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.price = price;
        this.category = category;
        this.shopID = shopID;        
    }
    public Food(Object foodID, Object name, Object desc, Object imgPath, Object price, Object category, Object shopID){
        this.foodID = (String)foodID;
        this.name = (String)name;
        this.desc = (String)desc;
        this.imgPath = (String)imgPath;
        this.price = (double)price;    
        this.category = (String)category;
        this.shopID = (String)shopID;                    
    }    

    //  GETTER AND SETTER
    public String getFoodId() {
        return foodID;
    }

    public void setId(String foodID) {
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
}
