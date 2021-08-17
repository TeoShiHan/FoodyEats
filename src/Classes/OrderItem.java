package Classes;

public class OrderItem {
    private String orderId,foodId;
    private int qty;
    private Food food = new Food();

    public OrderItem(){
        this("","",0);
    }
    
    public OrderItem(String orderId, String foodId, int qty) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.qty = qty;        
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getFoodId() {
        return foodId;
    }
    public void setFoodId(String foodId) {
        this.foodId = foodId;
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
    public double getAmount(){        
        return qty*food.getPrice();
    }    
}
