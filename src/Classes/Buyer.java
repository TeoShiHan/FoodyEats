package Classes;

import Cache.*;
import javafx.concurrent.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account{
    
    //#region : COMPOSITION / AGGREGATION VARIABLES
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();
    private Cart cart;
    //#endregion

    //#region : PROGRAM VARIABLES
    private String buyerID, address, cartID;    
    private ArrayList<Order> orders;    
    //#endregion
    
    //#region : CONSTRUCTORS
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

    public Buyer(HashMap<String,Object> account, HashMap<String,Object> buyer){
        super(
            (String)account.get("accountID"),
            (String)account.get("username"),
            (String)account.get("password"),
            (String)account.get("name"),
            (String)account.get("email"),
            (String)account.get("mobileNo"),
            (String)account.get("type")
        );
        this.buyerID = (String)buyer.get("buyerID");
        this.address = (String)buyer.get("address");
        this.cartID  = (String)buyer.get("cartID");
    }
    //#endregion

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
    public void loadCart() {
        HashMap<String,Object> c = db.readOne(String.format("SELECT * FROM `Cart` WHERE buyerID='%s'",buyerID));
        this.cart = new Cart(c.get("cartID"), c.get("buyerID"), c.get("shopID"));        
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    } 
    public void loadOrders() {
        this.orders = new ArrayList<>();
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE buyerID='%s'",buyerID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }   
        data.setOrders(this.orders);
    } 

    @Override
    public void register() throws IOException{
        super.register();
        // TODO Auto-generated method stub
        try {                                                
            String nextBuyerID = db.getNextId("Buyer");            
            String nextCartID = db.getNextId("Cart");                        
            db.executeCUD(String.format("INSERT INTO Cart VALUES ('%s','%s','%s')",nextCartID,nextBuyerID,null),gui);            
            this.cart = new Cart(nextCartID, nextBuyerID);            
            db.executeCUD(String.format("INSERT INTO Buyer VALUES ('%s','%s','%s','%s')",nextBuyerID,address,accountID,nextCartID),gui);            
            this.buyerID = nextBuyerID;
            this.cartID = nextCartID;
            data.setAccount(this);
            data.setBuyer(this);
            gui.toNextScene("View/BuyerHome.fxml");
            gui.notAlertInProgress();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            gui.informationPopup("Something wrong", "There is an error when inserting to database");
        }
    }
    
    public void edit(String username, String password, String name, String email, String mobileNo, String address) {        
        super.edit(username, password, name, email, mobileNo);
        this.address = address;
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException, SQLException {                                
                db.executeCUD(String.format("UPDATE `Account` a, `Buyer` b SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', b.address='%s' WHERE a.accountID='%s' AND a.accountID=b.accountID",username,password,name,email,mobileNo,address,accountID),gui);
                return null ;
            }
        };        
        new Thread(task).start();        
    }

    @Override
    public String toString() {

        return super.toString() + "Buyer [address=" + address + ", buyerID=" + buyerID + ", cart=" + cart + ", cartID=" + cartID + "]";
    }

    

    // public static String getCart(String BuyerID) {
    //     return BuyerID;
    // }
}