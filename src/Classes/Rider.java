package Classes;

import java.util.ArrayList;
import java.util.HashMap;

import Cache.DataHolder;

public class Rider extends Account{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();

    private String riderID;    
    private String vehicleID;
    private Vehicle vehicle;
    private ArrayList<Order> orders = new ArrayList<>();

    public Rider(){}
    public Rider(String riderID, String vehicleID, String accountID){
        this.riderID = riderID;        
        this.vehicleID = vehicleID;
        this.accountID = accountID;        
    }
    public Rider(Object riderID, Object vehicleID, Object accountID){
        this.riderID = (String)riderID;        
        this.vehicleID = (String)vehicleID;
        this.accountID = (String)accountID;        
    }
    public Rider(String accountID, String username, String password, String name, String email, String mobileNo, String accType, String riderID, String vehicleID){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.riderID = riderID;        
        this.vehicleID = vehicleID;                
    }
    public Rider(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo, Object accType, Object riderID, Object vehicleID){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.riderID = (String)riderID;        
        this.vehicleID = (String)vehicleID;        
        this.vehicle = (Vehicle)vehicle;
    }
    public String getRiderID() {
        return riderID;
    }
    public void setRiderID(String rIDerID) {
        this.riderID = rIDerID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleID() {
        return vehicleID;
    }
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }
    public void loadOrders() {
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE riderID='%s'",riderID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }   
        data.setOrders(this.orders);
    } 
}
