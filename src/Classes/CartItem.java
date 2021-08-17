package Classes;

public class CartItem {
    private String foodId, orderId;
    private int qty;
    private Food food; //indigo area

    public CartItem() {  //No-arg Constructor
    	this("","",0,new Food(""));
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
