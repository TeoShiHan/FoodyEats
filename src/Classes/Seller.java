package Classes;
import Cache.GUI;
import SQL.CreateTableQuery.SQL;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Account{

    //#region ：COMPOSITION / AGGREGATION VARIABLES
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private Shop shop;
    private SQL sql = SQL.getInstance();
    //#endregion
    
    //#region : CLASS FIELD
    private String sellerID;
    private String address;    
    private String NRIC;
    private String licenseNumber;
    private String bankAcc;
    private String shopID;
    private int status;
    private ArrayList<Order> order = new ArrayList<>();
    //#endregion

    //#region : CONSTRUCTORS
    public Seller(){
        this("","","","","","","","","","","","","",0);
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
        Object shopID,
        Object status
    ){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.sellerID = (String) sellerID;
        this.address = (String) address;        
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
        this.status = (int)status;
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
        String shopID,
        int status,
        Shop shop
    ){
        super(accountID, username, password, name, email, mobileNo, accType);
        this.sellerID = (String) sellerID;
        this.address = (String) address;        
        this.NRIC = (String) NRIC;
        this.licenseNumber = (String) licenseNumber;
        this.bankAcc = (String) bankAcc;
        this.accountID = (String) accountID;
        this.shopID = (String) shopID;
        this.status = (int) status;
        this.shop = shop;
    }

    public Seller(HashMap<String,Object>account, HashMap<String,Object>seller, Shop shop){
        super(
            (String)account.get("accountID"), 
            (String)account.get("username"), 
            (String)account.get("password"),
            (String)account.get("name"), 
            (String)account.get("email"), 
            (String)account.get("mobileNo"), 
            (String)account.get("type")
        );
        this.sellerID      = (String)seller.get("sellerID");
        this.address       = (String)seller.get("address");
        this.NRIC          = (String)seller.get("NRIC");
        this.licenseNumber = (String)seller.get("licenseNumber");
        this.bankAcc       = (String)seller.get("bankAcc");
        this.shopID        = (String)seller.get("shopID");
        this.status        = (int)seller.get("status");
        this.shop = shop;
    }
    //#endregion

    //#region : GETTER AND SETTER
    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
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
    //#endregion

    //#region : METHODS
    @Override
    public void register() throws IOException {
        
        super.register();

        // #region : SELLER OBJ
        this.sellerID = Seller.getNextID();
        db.executeCUD(sql.insertNewSeller(this), gui);
        // #endregion

        // #region : SHOP OBJ
        String nextShopID = Shop.getNextID();
        this.shop.setShopID(nextShopID);
        shop.setImgPath("Images/" + nextShopID + shop.getImgPath());
        db.executeCUD(sql.insertNewShop(this.shop), gui);
        // #endregion

        // #region : SCENE CHANGING AND PUT
        gui.toNextScene("View/Login.fxml");
        Path source = getShopImgPath(shop);
        Files.move(source,
                source.resolveSibling(nextShopID + shop.getImgPath().substring(shop.getImgPath().indexOf("."))));
        // #endregion

        this.notifyAccCreated(gui);
    }

    public static String getNextID() {
        JDBC db = new JDBC();
        String nextSellerID = "";
        try {
            nextSellerID = db.getNextId("Seller");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextSellerID;
    }

    public Path getShopImgPath(Shop shop) {
        // https://stackoverflow.com/questions/1158777/rename-a-file-using-java/20260300#20260300
        return Paths.get(Paths.get("").toAbsolutePath().toString()+
               "/src/Images/temp"+
               shop.getImgPath().substring(shop.getImgPath().indexOf(".")).replaceAll("\\\\", "/"));           
    }

    @Override
    public void notifyAccCreated(GUI gui) throws IOException{
        gui.informationPopup("Account created successfully", "You have to wait about 48hours to verify your account. Thank you!");
        gui.notAlertInProgress();
    }

    public boolean isApprovedSeller(){
        return (this.status == 1);
    }
    //#endregion

}
