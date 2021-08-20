package Classes;

public class CartItem {
    private String foodID, orderID;
    private int qty;
    private Food food; //indigo area

    public CartItem() {  //No-arg Constructor
    	this("","",0, new Food());
    }

    public CartItem(String string, String string2, int qty, Food food2) {

    }

    public CartItem(Object string, Object string2, Object qty, Object food2) {
        
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
}
