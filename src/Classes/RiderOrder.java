package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import Cache.*;

public class RiderOrder extends Order {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private DataHolder data = DataHolder.getInstance();
    
    private Shop shop;
    private Buyer buyer;            

    public RiderOrder() {        
    }

    public RiderOrder(Order order) {
        super(order.orderID, order.status, order.dateCreated, order.timeCreated, order.buyerID, order.riderID, order.shopID, order.paymentID, order.reviewID);
    }

    public RiderOrder(Shop shop, Buyer buyer) {
        this.shop = shop;
        this.buyer = buyer;
    }

    public RiderOrder(String orderID, String status, LocalDate dateCreated, LocalTime timeCreated, String buyerID,
            String riderID, String shopID, String paymentID, String reviewID, Shop shop, Buyer buyer) {
        super(orderID, status, dateCreated, timeCreated, buyerID, riderID, shopID, paymentID, reviewID);
        this.shop = shop;
        this.buyer = buyer;
    }

    public RiderOrder(Object orderID, Object status, Object dateCreated, Object timeCreated, Object buyerID,
            Object riderID, Object shopID, Object paymentID, Object reviewID, Shop shop, Buyer buyer) {
        super(orderID, status, dateCreated, timeCreated, buyerID, riderID, shopID, paymentID, reviewID);
        this.shop = shop;
        this.buyer = buyer;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }    

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void loadAllDetails() {            
        super.loadAllDetails();
        loadShop();
        loadBuyer();
        data.setOrder(this);
    }
    
    public void loadShop() {                        
        HashMap<String,Object> s = db.readOne(String.format("SELECT * FROM Shop WHERE shopID='%s'",this.shopID));        
        this.shop=new Shop(s.get("shopID"), s.get("shopName"), s.get("address"), s.get("tel"), s.get("startHour"), s.get("endHour"), s.get("status"), s.get("dateCreated"), s.get("deliveryFee"),s.get("imgPath"));
    }

    public void loadBuyer() {
        HashMap<String,Object> b = db.readOne(String.format("SELECT * FROM Buyer b, Account a WHERE b.buyerID='%s' AND a.accountID=b.accountID",this.buyerID));
        this.buyer=new Buyer(b.get("accountID"), b.get("username"), b.get("password"), b.get("name"), b.get("email"), b.get("mobileNo"), b.get("accType"), b.get("buyerID"), b.get("address"), b.get("cartID"));
    }
}
