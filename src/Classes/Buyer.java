package Classes;

import Cache.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    private String buyerId, address, accountId, cartId;
    private Cart cart;
    private ArrayList<Order> orders = new ArrayList<>();

    public Buyer() {  //No-arg Constructor        
    	this("","","","");
    }    
    public Buyer(String buyerId, String address, String accountId, String cartId) {        
        this.buyerId = buyerId;
        this.address = address;
        this.accountId = accountId;
        this.cartId = cartId;        
    }      
    public Buyer(Object buyerId, Object address, Object accountId, Object cartId) {        
        this.buyerId =(String)buyerId;
        this.address =(String)address;
        this.accountId =(String)accountId;
        this.cartId = (String)cartId;        
    }
    public Buyer(String accId, String username, String password, String email, String mobileNo, String accType, String buyerId, String address, String accountId, String cartId) {        
        super(accId, username, password, email, mobileNo, accType);
        this.buyerId = buyerId;
        this.address = address;
        this.accountId = accountId;
        this.cartId = cartId;                
    }
    public Buyer(Object accId, Object username, Object password, Object email, Object mobileNo, Object accType, Object buyerId, Object address, Object accountId, Object cartId) {
        super(accId, username, password, email, mobileNo, accType);
        this.buyerId =(String)buyerId;
        this.address =(String)address;
        this.accountId =(String)accountId;
        this.cartId = (String)cartId;        
    }

    public String getBuyerId() {
        return buyerId;
    }
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    } 
    public void loadOrders() {
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE buyerID='%s'",buyerId));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }   
        data.setOrders(this.orders);
    } 

    // public static String getCart(String BuyerId) {
    //     return BuyerId;
    // }
}





