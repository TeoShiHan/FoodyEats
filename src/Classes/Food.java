package Classes;

public class Food {
    private String 
    foodID, name, desc, imgPath, category, shopID;

    private Double price;

    //  CONSTRUCTOR FOR DATABASE
    public Food(
        Object FoodID, 
        Object name, 
        Object desc, 
        Object imgPath, 
        Object category, 
        Object shopID, 
        Object price) 
    {
        this.foodID = (String)foodID;
        this.name = (String)name;
        this.desc = (String)desc;
        this.imgPath = (String)imgPath;
        this.category = (String)category;
        this.shopID = (String)shopID;
        this.price = (Double)price;
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
}
