package Classes;

import java.util.ArrayList;
import java.util.HashMap;

import Cache.DataHolder;
import SQL.CreateTableQuery.SQL;

public class Cart {
    
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private String cartID, buyerID, shopID;
    private ArrayList<CartItem> cartItems;
    private static boolean cartHaveChange = false;
    private static SQL sql = SQL.getInstance();

    public Cart() {  //No-arg Constructor
            	
    }    

    public Cart(Object cartID, Object buyerID, Object shopID) {
        this.cartID = (String)cartID;
        this.buyerID = (String)buyerID;
        this.shopID = (String)shopID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }            

    public static boolean isCartHaveChange() {
        return cartHaveChange;
    }

    public static void setCartHaveChange(boolean cartHaveChange) {
        Cart.cartHaveChange = cartHaveChange;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void loadCartItems(){        
        this.cartItems = new ArrayList<>();
        ArrayList<HashMap<String,Object>> cs = sql.fetchCartItems(cartID);        
        System.out.println(cs);
        System.out.println("cartID: "+cartID);
        if(!cs.isEmpty()){
            for(HashMap<String,Object> c : cs){
                if(!c.isEmpty()){
                    Food food = new Food(c.get("foodID"),c.get("foodName"),c.get("foodDesc"),c.get("imgPath"),c.get("price"),c.get("category"),c.get("shopID"));
                    CartItem cartItem = new CartItem(c.get("cartID"), c.get("foodID"), c.get("quantity"));
                    cartItem.setFood(food);
                    this.cartItems.add(cartItem);
                }
            }
        }
    }

    public double calcTotalAmount(){        
        double totalAmount = 0.0;
        for(CartItem cartItem : cartItems){
            totalAmount += cartItem.calcAmount();
        }
        return totalAmount;
    }

    public static String addProductToCart(String ProductID) {
        return ProductID;
    }
}
