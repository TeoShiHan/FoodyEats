package Classes;

public class Vehicle {
    private String plateNo;
    private String brand;
    private String model;
    private String color;

    public Vehicle(){}

    public Vehicle(String plateNo, String brand, String model, String color){
        this.plateNo = plateNo;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public Vehicle(Object plateNo, Object brand, Object model, Object color){
        this.plateNo = (String)plateNo;
        this.brand = (String)brand;
        this.model = (String)model;
        this.color = (String)color;
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
