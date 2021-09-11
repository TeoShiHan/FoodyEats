package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import Cache.*;

public class BuyerOrder extends Order {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    
    private Shop shop;
    private Rider rider;        
    
    public BuyerOrder() {
                
    }

    public BuyerOrder(Order order) {
        super(order.orderID, order.status, order.dateCreated, order.timeCreated, order.buyerID, order.riderID, order.shopID, order.paymentID, order.reviewID);
    }    

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
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
        loadShop();
        if(!(riderID==null || riderID.isEmpty())){            
            loadRider();
        }
        if(!(reviewID==null || reviewID.isEmpty())){
            loadReview();
        }
        data.setOrder(this);
    }
    
    public void loadShop() {                        
        HashMap<String,Object> s = db.readOne(String.format("SELECT * FROM Shop WHERE shopID='%s'",this.shopID));        
        this.shop=new Shop(s.get("shopID"), s.get("shopName"), s.get("address"), s.get("tel"), s.get("startHour"), s.get("endHour"), s.get("status"), s.get("dateCreated"), s.get("deliveryFee"),s.get("imgPath"));
    }

    public void loadRider() {
        HashMap<String,Object> r = db.readOne(String.format("SELECT r.*,v.*,a.* FROM `Rider` r, `Vehicle` v, `Account` a WHERE r.riderID='%s' AND r.vehicleID=v.vehicleID AND r.accountID=a.accountID",riderID));
        System.out.println(r);
        Vehicle vehicle = new Vehicle(r.get("vehicleID"), r.get("type"), r.get("plateNo"), r.get("brand"), r.get("model"), r.get("color"));
        Rider rider = new Rider(r.get("accountID"), r.get("username"), r.get("password"), r.get("name"), r.get("email"), r.get("mobileNo"), r.get("accType"), r.get("regDate"), r.get("riderID"), r.get("vehicleID"), r.get("status"));
        rider.setVehicle(vehicle);
        this.rider = rider;        
    }
}
