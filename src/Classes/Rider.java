package Classes;

import java.util.ArrayList;

public class Rider extends Account{
    private String riderId;
    private String name;
    private String vehicleId;
    private String accountId;
    private Vehicle vehicle;
    private ArrayList<Order> orders;

    public Rider(){}
    public Rider(String riderId, String name, String vehicleId, String accountId){
        this.riderId = riderId;
        this.name = name;
        this.vehicleId = vehicleId;
        this.accountId = accountId;        
    }
    public Rider(Object riderId, Object name, Object vehicleId, Object accountId){
        this.riderId = (String)riderId;
        this.name = (String)name;
        this.vehicleId = (String)vehicleId;
        this.accountId = (String)accountId;
        this.vehicle = (Vehicle)vehicle;
    }
    public Rider(String accId, String username, String password, String email, String mobileNo, String accType, String riderId, String name, String vehicleId, String accountId){
        super(accId, username, password, email, mobileNo, accType);
        this.riderId = riderId;
        this.name = name;
        this.vehicleId = vehicleId;
        this.accountId = accountId;        
    }
    public Rider(Object accId, Object username, Object password, Object email, Object mobileNo, Object accType, Object riderId, Object name, Object vehicleId, Object accountId){
        super(accId, username, password, email, mobileNo, accType);
        this.riderId = (String)riderId;
        this.name = (String)name;
        this.vehicleId = (String)vehicleId;
        this.accountId = (String)accountId;
        this.vehicle = (Vehicle)vehicle;
    }
    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
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
