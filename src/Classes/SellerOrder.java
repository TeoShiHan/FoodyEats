package Classes;
import java.util.HashMap;

import Cache.*;

public class SellerOrder extends Order {
    private JDBC db = new JDBC();
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
        HashMap<String,Object> buyerMap = db.readOne(String.format("SELECT * FROM Buyer b, Account a WHERE b.buyerID='%s' AND a.accountID=b.accountID",this.buyerID));
        this.buyer=new Buyer(buyerMap);
    }

    public void loadRider() {
        HashMap<String,Object> riderMap = db.readOne(String.format("SELECT a.type AS accType,a.*,r.*,v.* FROM `Rider` r, `Vehicle` v, `Account` a WHERE r.riderID='%s' AND r.vehicleID=v.vehicleID AND r.accountID=a.accountID",riderID));
        Vehicle vehicle = new Vehicle(riderMap);
        Rider rider = new Rider(riderMap);
        rider.setVehicle(vehicle);
        this.rider = rider;
    }
}
