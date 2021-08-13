package Classes;

import java.util.ArrayList;

public class Rider {
    private String id;
    private String name;
    private String vehicleId;
    private String accountId;
    private Vehicle vehicle;
    private ArrayList <Order> orders;

    public Rider(){}
    public Rider(String id, String name, String vehicleId, String accountId, Vehicle vehicle){
        this.id = id;
        this.name = name;
        this.vehicleId = vehicleId;
        this.accountId = accountId;
        this.vehicle = vehicle;
    }
    public Rider(Object id, Object name, Object vehicleId, Object accountId, Object vehicle){
        this.id = (String)id;
        this.name = (String)name;
        this.vehicleId = (String)vehicleId;
        this.accountId = (String)accountId;
        this.vehicle = (Vehicle)vehicle;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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


}
