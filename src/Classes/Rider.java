package Classes;
import Cache.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
public class Rider extends Account{
    
    //#region : AGGREGATED / COMPOSITION VARIABLES
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();
    //#endregion

    //#region : CLASS FIELDS
    private String riderID;    
    private String vehicleID;
    private int status;
    private Vehicle vehicle;
    private ArrayList<Order> orders = new ArrayList<>();
    //#endregion

    //#region : CONSTRUCTORS
    public Rider(){
        
    }        
    public Rider(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo, Object accType, Object regDate, Object riderID, Object vehicleID, Object status){
        super(accountID, username, password, name, email, mobileNo, accType, regDate);
        this.riderID = (String)riderID;        
        this.vehicleID = (String)vehicleID;        
        this.vehicle = (Vehicle)vehicle;
        this.status = (int)status;
    }

    public Rider(HashMap<String,Object>account, HashMap<String,Object>rider){
        super(
            (String)account.get("accountID"),
            (String)account.get("username"),
            (String)account.get("password"),
            (String)account.get("name"), 
            (String)account.get("email"), 
            (String)account.get("mobileNo"), 
            (String)account.get("type"),
            (String)account.get("regDate")
        );        
        this.riderID = (String)rider.get("riderID");
        this.vehicleID = (String)rider.get("vehicleID");
        this.status = (int)rider.get("status");
    }

    public Rider(HashMap<String,Object>fullRiderDetails){
        super(
            (String)fullRiderDetails.get("accountID"),
            (String)fullRiderDetails.get("username"),
            (String)fullRiderDetails.get("password"),
            (String)fullRiderDetails.get("name"), 
            (String)fullRiderDetails.get("email"), 
            (String)fullRiderDetails.get("mobileNo"), 
            (String)fullRiderDetails.get("type"),
            (String)fullRiderDetails.get("regDate")
        );        
        this.riderID = (String)fullRiderDetails.get("riderID");
        this.vehicleID = (String)fullRiderDetails.get("vehicleID");
        this.status = (int)fullRiderDetails.get("status");
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

    public void loadOrders() {
        ArrayList<HashMap<String,Object>> os = db.readAll(String.format("SELECT * FROM `Order` WHERE riderID='%s'",riderID));
        for(HashMap<String,Object> o : os){
            this.orders.add(new Order(o.get("orderID"),o.get("status"), o.get("dateCreated"),o.get("timeCreated"),o.get("buyerID"),o.get("riderID"),o.get("shopID"),o.get("paymentID"),o.get("reviewID")));
        }   
        data.setOrders(this.orders);
    } 

    public void acceptOrder(String oRDerID){
        data.getOrder().setRiderID(this.riderID);
        if(db.executeCUD(String.format("UPDATE `Order` SET status='Rider Accepted', riderID='%s' WHERE orderID='%s' AND (riderID='' OR riderID IS NULL)",this.riderID,oRDerID),gui)==0){
            data.getOrder().setRiderID("");
            try {
                gui.informationPopup("Attention", "This order has been accepted by other rider. Try harder next time. 😉");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            gui.miniPopup("Order Accepted, please go to Order History to see the order");
        }
    }

    public void collectOrder(String oRDerID){
        db.executeCUD(String.format("UPDATE `Order` SET status='Rider Collected', riderID='%s' WHERE orderID='%s'",this.riderID,oRDerID),gui);
    }

    public void deliveredOrder(String oRDerID){        
        db.executeCUD(String.format("UPDATE `Order` SET status='Completed', riderID='%s' WHERE orderID='%s'",this.riderID,oRDerID),gui);
    }

    @Override
    public void register() throws IOException{
        // TODO Auto-generated method stub
        super.register();
        try {            
            String nextRiderID = db.getNextId("Rider");            
            String nextVehicleID = db.getNextId("Vehicle");                     
            db.executeCUD(String.format("INSERT INTO Rider VALUES ('%s','%s','%s',%d)",nextRiderID,accountID,nextVehicleID, 0),gui);            
            db.executeCUD(String.format("INSERT INTO Vehicle VALUES ('%s','%s','%s','%s','%s','%s')",nextVehicleID, vehicle.getType(), vehicle.getBrand(), vehicle.getModel(), vehicle.getPlateNo(), vehicle.getColor()),gui);
            this.riderID = nextRiderID;
            this.vehicleID = nextVehicleID;
            this.vehicle.setVehicleID(nextVehicleID);            
            gui.toNextScene("View/Login.fxml");
            gui.informationPopup("Account created successfully", "You have to wait about 48hours to verify your account. Thank you!");
            gui.notAlertInProgress();
            data.setRider(this);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            gui.informationPopup("Something wrong", "There is an error when inserting to database");
        }
    }

    @Override
    public void edit(String username, String password, String name, String email, String mobileNo) {        
        super.edit(username, password, name, email, mobileNo);
        db.executeCUD(String.format("UPDATE `Account` SET username='%s', password='%s', name='%s', email='%s', mobileNo='%s' WHERE accountID='%s'",username,password,name,email,mobileNo,accountID),gui);
    }

    public void edit(Account account) {        
        super.edit(account.username, account.password, account.name, account.email, account.mobileNo);
        db.executeCUD(String.format("UPDATE `Account` SET username='%s', password='%s', name='%s', email='%s', mobileNo='%s' WHERE accountID='%s'",username,password,name,email,mobileNo,accountID),gui);
    }

    public boolean isApprovedRider(){
        return (this.status == 1);
    }
}
