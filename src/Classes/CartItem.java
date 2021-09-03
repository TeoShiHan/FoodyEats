package Classes;

public class CartItem {
    private JDBC db = new JDBC();

    private String cartID,foodID;
    private int quantity;
    private Food food; //indigo area
    private boolean isChanged = false;    

    public CartItem() {  //No-arg Constructor
    	this("","",0, new Food());
    }

    public CartItem(String cartID, String foodID, int quantity, Food food) {
        this.cartID = cartID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.food = food;
    }

    public CartItem(Object cartID, Object foodID, Object quantity) {
        this.cartID = (String)cartID;
        this.foodID = (String)foodID;
        this.quantity = (int)quantity;
    }    

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public static double getPrice(double price) {
        return price;
    }

    public static double getAmount(double amount) {
        return amount;
    }        

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }

    public double getAmount(){
        return quantity*food.getPrice();
    }

    public void delete(){
        db.executeCUD(String.format("DELETE FROM `CartItem` WHERE cartID='%s' AND foodID='%s'",cartID,foodID));
    }

    public void update(){        
        db.executeCUD(String.format("UPDATE `CartItem` SET quantity=%d WHERE cartID='%s' AND foodID='%s'",quantity,cartID,foodID));
    }
}
