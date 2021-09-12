package Classes;

import java.util.HashMap;

import Cache.*;
import SQL.CreateTableQuery.SQL;

public class BuyerOrder extends Order {
    private DataHolder data = DataHolder.getInstance();
    private SQL sql = SQL.getInstance();

    private Shop shop;
    private Rider rider;

    public BuyerOrder() {

    }

    public BuyerOrder(Order order) {
        super(order);
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
        if (!(riderID == null || riderID.isEmpty())) {
            loadRider();
        }
        if (!(reviewID == null || reviewID.isEmpty())) {
            loadReview();
        }
        data.setOrder(this);
    }

    public void loadShop() {
        HashMap<String, Object> shopDetails = sql.fetchSpecificShopDetails(this.shopID);
        this.shop = new Shop(shopDetails);
    }

    public void loadRider() {
        HashMap<String, Object> riderDetails = sql.fetchSpecificRiderDetails(riderID);
        System.out.println(riderDetails);
        Vehicle vehicle = new Vehicle(riderDetails);
        Rider rider = new Rider(riderDetails);
        rider.setVehicle(vehicle);
        this.rider = rider;
    }
}
