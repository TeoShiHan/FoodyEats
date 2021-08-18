package Classes;

import java.util.ArrayList;

public class Seller {
    private String sellerID;
    private String name;
    private String address;
    private String state;
    private String NRIC;
    private String licenseNumber;
    private String bankAcc;
    private String accountID;
    private String shopID;
    private ArrayList<Order> order;

    //  CONSTRUCTOR FOR DATABASE LOAD
    public Seller(
        Object sellerID,
        Object name,
        Object address,
        Object state,
        Object NRIC, 
        Object licenseNumber,
        Object bankAcc,
        Object accountID,
        Object shopID,
        ArrayList<Order> order
    ){
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;
        this.state = (String) state;
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
    }

    //  CONSTRUCTOR FOR NORMAL USAGE
    public Seller(
        String sellerID,
        String name,
        String address,
        String state,
        String NRIC, 
        String licenseNumber,
        String bankAcc,
        String accountID,
        String shopID,
        ArrayList<Order> order
    ){
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;
        this.state = (String) state;
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
    }

    // GETTERS AND SETTERS
    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNRIC() {
        return NRIC;
    }

    public void setNRIC(String nRIC) {
        NRIC = nRIC;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getBankAcc() {
        return bankAcc;
    }

    public void setBankAcc(String bankAcc) {
        this.bankAcc = bankAcc;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public ArrayList<Order> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Order> order) {
        this.order = order;
    }

    //  METHODS
    
}
