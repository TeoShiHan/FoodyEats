package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import Cache.*;

public class SellerOrder extends Order {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    
    private Buyer buyer;
    private Rider rider;        
    
    public SellerOrder() {
        
    }

    public SellerOrder(Order order) {
        super(order.orderID, order.status, order.dateCreated, order.timeCreated, order.buyerID, order.riderID, order.shopID, order.paymentID, order.reviewID);
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    @Override
    public void loadAllDetails() {            
        super.loadAllDetails();
        loadBuyer();
        System.out.println("riderID - "+riderID);
        if(!(riderID==null||riderID.isEmpty()||riderID.isBlank())){
            loadRider();
        }
        data.setOrder(this);
    }
    
    public void loadBuyer() {
        HashMap<String,Object> b = db.readOne(String.format("SELECT * FROM Buyer b, Account a WHERE b.buyerID='%s' AND a.accountID=b.accountID",this.buyerID));
        this.buyer=new Buyer(b.get("accountID"), b.get("username"), b.get("password"), b.get("name"), b.get("email"), b.get("mobileNo"), b.get("accType"), b.get("regDate"), b.get("buyerID"), b.get("address"), b.get("cartID"));
    }

    public void loadRider() {
        HashMap<String,Object> r = db.readOne(String.format("SELECT a.type AS accType,a.*,r.*,v.* FROM `Rider` r, `Vehicle` v, `Account` a WHERE r.riderID='%s' AND r.vehicleID=v.vehicleID AND r.accountID=a.accountID",riderID));
        System.out.println(r);
        Vehicle vehicle = new Vehicle(r.get("vehicleID"), r.get("type"), r.get("plateNo"), r.get("brand"), r.get("model"), r.get("color"));
        Rider rider = new Rider(r.get("accountID"), r.get("username"), r.get("password"), r.get("name"), r.get("email"), r.get("mobileNo"), r.get("accType"), r.get("regDate"), r.get("riderID"), r.get("vehicleID"), r.get("status"));
        rider.setVehicle(vehicle);
        this.rider = rider;
        // HashMap<String,Object> r = db.readOne(String.format("SELECT * FROM Rider r, Account a WHERE r.riderID='%s' AND a.accountID=r.accountID",this.riderID));        
        // this.rider = new Rider(r.get("accountID"), r.get("username"), r.get("password"), r.get("name"), r.get("email"), r.get("mobileNo"), r.get("type"), r.get("riderID"), r.get("vehicleID"), r.get("status"));
    }
}
