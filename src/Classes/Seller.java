package Classes;

import Cache.DataHolder;
import Cache.GUI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Account{
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();

    private String sellerID;
    private String name;
    private String address;    
    private String NRIC;
    private String licenseNumber;
    private String bankAcc;
    private String accountID;
    private String shopID;
    private Shop shop;
    // private List<Shop> shop = new ArrayList<>();
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    //  METHODS
    @Override
    public void register() throws IOException {
        // TODO Auto-generated method stub
        super.register();
        try {                        
            String nextSellerID = db.getNextId("Seller");
            String nextShopID = db.getNextId("Shop");
            db.executeCUD(String.format("INSERT INTO Seller VALUES ('%s','%s','%s','%s','%s','%s','%s',%d)",nextSellerID,address,NRIC,licenseNumber,bankAcc,accountID,nextShopID,0));                                    
            shop.setImgPath("Images/"+nextShopID+shop.getImgPath());
            db.executeCUD(String.format("INSERT INTO Shop VALUES ('%s','%s','%s','%s','%s','%s','%s',%.2f,%d,'%s')",nextShopID,shop.getName(),LocalDate.now(),shop.getStartHour().toString(),shop.getEndHour().toString(),shop.getTel(),shop.getAddress(),shop.getDeliveryFee(),shop.getStatus(),shop.getImgPath()));  //shop.getDeliveryFee()

            this.sellerID = nextSellerID;
            this.shop.setShopID(nextShopID);
            gui.toNextScene("View/Login.fxml");

            Path source = Paths.get(Paths.get("").toAbsolutePath().toString()+"/src/Images/temp"+shop.getImgPath().substring(shop.getImgPath().indexOf(".")).replaceAll("\\\\", "/"));            
            // https://stackoverflow.com/questions/1158777/rename-a-file-using-java/20260300#20260300
            Files.move(source, source.resolveSibling(nextShopID+shop.getImgPath().substring(shop.getImgPath().indexOf("."))));            
            gui.informationPopup("Account created successfully", "You have to wait about 48hours to verify your account. Thank you!");
            gui.notAlertInProgress();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            gui.informationPopup("Something wrong", "There is an error when inserting to database");
        }        
    }

    public void loadShop(){
        HashMap<String,Object> s = db.readOne(String.format("SELECT * FROM `Shop` WHERE shopID='%s'",shopID));
        this.shop = new Shop(s.get("shopID"), s.get("shopName"), s.get("address"), s.get("tel"), s.get("startHour"), s.get("endHour"), s.get("status"), s.get("dateCreated"), s.get("deliveryFee"), s.get("imgPath"));
    }
    
    public void edit(String username, String password, String name, String email, String mobileNo, String address, String bankAcc, String NRIC, String licenseNumber) {
        super.edit(username, password, name, email, mobileNo);
        this.address = address;
        this.bankAcc = bankAcc;
        this.NRIC = NRIC;
        this.licenseNumber = licenseNumber;
        db.executeCUD(String.format("UPDATE `Account` a, `Seller` s SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', s.address='%s', s.bankAcc='%s', s.NRIC='%s', s.licenseNumber='%s' WHERE a.accountID='%s' AND a.accountID=s.accountID",username,password,name,email,mobileNo,address,bankAcc,NRIC,licenseNumber,accountID));
    }
}
