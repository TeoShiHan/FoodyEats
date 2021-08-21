package Classes;

import java.util.ArrayList;

public class Seller extends Account{
    private String sellerID;
    private String name;
    private String address;    
    private String NRIC;
    private String licenseNumber;
    private String bankAcc;
    private String accountID;
    private String shopID;
    private ArrayList<Order> order = new ArrayList<>();

    public Seller(){}
    
    //  CONSTRUCTOR FOR DATABASE LOAD
    public Seller(
        Object sellerID,
        Object name,
        Object address,        
        Object NRIC, 
        Object licenseNumber,
        Object bankAcc,
        Object accountID,
        Object shopID
    ){
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;        
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
    }
    public Seller(
        Object accountID, 
        Object username, 
        Object password, 
        Object name, 
        Object email, 
        Object mobileNo, 
        Object accType,
        Object sellerID,        
        Object address,        
        Object NRIC, 
        Object licenseNumber,
        Object bankAcc,        
        Object shopID
    ){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;        
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
        String NRIC, 
        String licenseNumber,
        String bankAcc,
        String accountID,
        String shopID      
    ){
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;        
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
    }

    public Seller(
        String accountID, 
        String username, 
        String password, 
        String name, 
        String email, 
        String mobileNo, 
        String accType,
        String sellerID,        
        String address,        
        String NRIC, 
        String licenseNumber,
        String bankAcc,
        String shopID
    ){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.sellerID = (String) sellerID;
        this.name = (String) name;
        this.address = (String) address;        
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
