package Classes;

public class Vehicle {
    private String vehicleID;
    private String plateNo;
    private String brand;
    private String model;
    private String color;
    private String type;

    public Vehicle(){}

    public Vehicle(String vehicleID, String type, String plateNo, String brand, String model, String color){
        this.vehicleID = vehicleID;
        this.type = type;
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Vehicle(Object vehicleID, Object type, Object plateNo, Object brand, Object model, Object color){
        this.vehicleID = (String)vehicleID;
        this.type = (String)type;
        this.plateNo = (String)plateNo;
        this.brand = (String)brand;
        this.model = (String)model;
        this.color = (String)color;
    }

    
    public String getVehicleID() {
        return vehicleID;
    }
    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getPlateNo() {
        return plateNo;
    }
    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }   
}
