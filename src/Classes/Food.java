package Classes;

import java.time.LocalDate;

public class Food {
    private String id,name,desc,imgPath,category,shopId;
    private double price;    
    private LocalDate dateCreated; //is it needed?

    public Food(){
        this("","","","","","",0.0,LocalDate.now());
    }        
    public Food(String id, String name, String desc, String imgPath, String category, String shopId, double price,
                LocalDate dateCreated){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.imgPath = imgPath;
        this.category = category;
        this.shopId = shopId;
        this.price = price;        
        this.dateCreated = dateCreated;            
    }
    public Food(Object id, Object name, Object desc, Object imgPath, Object category, Object shopId, Object price,
                LocalDate dateCreated){
        this.id = (String)id;
        this.name = (String)name;
        this.desc = (String)desc;
        this.imgPath = (String)imgPath;
        this.category = (String)category;
        this.shopId = (String)shopId;
        this.price = (double)price;        
        this.dateCreated = (LocalDate)dateCreated;
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
    public String getShopId() {
        return shopId;
    }
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }    
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
