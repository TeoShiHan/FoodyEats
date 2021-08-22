package Classes;

import Cache.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    private String buyerID, address, cartID;
    private Cart cart;
    private ArrayList<Order> orders = new ArrayList<>();
    
    public Buyer() {  //No-arg Constructor        
    	this("","","","");
    }    
    public Buyer(String buyerID, String address, String accountID, String cartID) {        
        this.buyerID = buyerID;
        this.address = address;
        this.accountID = accountID;
        this.cartID = cartID;        
    }      
    public Buyer(Object buyerID, Object address, Object accountID, Object cartID) {        
        this.buyerID =(String)buyerID;
        this.address =(String)address;
        this.accountID =(String)accountID;
        this.cartID = (String)cartID;        
    }
    public Buyer(String accountID, String username, String password, String name, String email, String mobileNo, String accType, String buyerID, String address, String cartID) {        
        super(accountID, username, password, name, email, mobileNo, accType);
        this.buyerID = buyerID;
        this.address = address;        
        this.cartID = cartID;                
    }
    public Buyer(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo, Object accType, Object buyerID, Object address, Object cartID) {
        super(accountID, username, password, name, email, mobileNo, accType);
        this.buyerID =(String)buyerID;
        this.address =(String)address;        
        this.cartID = (String)cartID;        
    }

    public String getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCartID() {
        return cartID;
    }
    public void setCartID(String cartID) {
        this.cartID = cartID;
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
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE buyerID='%s'",buyerID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }   
        data.setOrders(this.orders);
    } 

    @Override
    public void register() throws IOException{
        // TODO Auto-generated method stub
        try {            
            String nextBuyerID = db.getNextId("Buyer");
            String nextAccountID = db.getNextId("Account");
            String nextCartID = db.getNextId("Cart");
            db.executeCUD(String.format("INSERT INTO Account VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",nextAccountID,username,password,name,email,mobileNo,LocalDate.now().toString(),accType));
            db.executeCUD(String.format("INSERT INTO Buyer VALUES ('%s','%s','%s','%s')",nextBuyerID,address,nextAccountID,nextCartID));
            this.accountID = nextAccountID;
            this.buyerID = nextBuyerID;
            this.cartID = nextCartID;
            gui.toNextScene("View/BuyerHome.fxml");            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            gui.informationPopup("Something wrong", "There is an error when inserting to database");
        }
    }

    // public static String getCart(String BuyerID) {
    //     return BuyerID;
    // }
}