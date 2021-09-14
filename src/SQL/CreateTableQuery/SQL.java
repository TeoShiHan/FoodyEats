package SQL.CreateTableQuery;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Cache.GUI;
import Classes.Account;
import Classes.JDBC;
import Classes.Seller;
import Classes.Shop;

public final class SQL {

    private JDBC db = new JDBC();
    private final static SQL INSTANCE = new SQL();
    private final static String UNION = "\nUNION\n";
    private final static GUI gui = GUI.getInstance();

    SQL() {}
    
    public static SQL getInstance() {
        return INSTANCE;
    }

    public String insertNewAccount(Account account) {
        return String.format("INSERT INTO Account VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",
                Account.getNextAccID(), account.getUsername(), account.getPassword(), account.getName(),
                account.getEmail(), account.getMobileNo(), LocalDate.now().toString(), account.getAccType());
    }

    public String insertNewSeller(Seller seller) {
        return String.format("INSERT INTO Seller VALUES ('%s','%s','%s','%s','%s','%s','%s',%d)", seller.getSellerID(),
                seller.getAddress(), seller.getNRIC(), seller.getLicenseNumber(), seller.getBankAcc(),
                seller.getAccountID(), Shop.getNextID(), 0);
    }

    public String insertNewShop(Shop shop) {
        return String.format("INSERT INTO Shop VALUES ('%s','%s','%s','%s','%s','%s','%s',%.2f,%d,'%s')",
                Shop.getNextID(), shop.getName(), LocalDate.now(), shop.getStartHour().toString(),
                shop.getEndHour().toString(), shop.getTel(), shop.getAddress(), shop.getDeliveryFee(), shop.getStatus(),
                shop.getImgPath());
    }

    public ArrayList<HashMap<String, Object>> fetchAccountTable() {
        return db.readAll("SELECT * FROM Account");
    }

    public ArrayList<HashMap<String, Object>> fetchBuyerTable() {
        return db.readAll("SELECT * FROM Buyer");
    }

    public ArrayList<HashMap<String, Object>> fetchSellerTable() {
        return db.readAll("SELECT * FROM Seller");
    }

    public ArrayList<HashMap<String, Object>> fetchRiderTable() {
        return db.readAll("SELECT * FROM Rider");
    }

    public ArrayList<HashMap<String, Object>> fetchAdminTable() {
        return db.readAll("SELECT * FROM Admin");
    }

    public ArrayList<HashMap<String, Object>> fetchShopTable() {
        return db.readAll("SELECT * FROM Shop");
    }

    public HashMap<String, Object> fetchSpecificShopDetails(String shopID) {
        return db.readOne(String.format("SELECT * FROM `Shop` WHERE shopID='%s'", shopID));
    }

    public HashMap<String, Object> fetchSpecificRiderDetails(String riderID){
        return db.readOne(String.format("SELECT r.*,v.*,a.* FROM `Rider` r, `Vehicle` v, `Account` a WHERE r.riderID='%s' AND r.vehicleID=v.vehicleID AND r.accountID=a.accountID",riderID));
    }

    public ArrayList<HashMap<String, Object>> fetchSpecificOrderDataset(String idName, String idValue) {
        return db.readAll(String.format("SELECT * FROM `Order` WHERE %s='%s' ORDER BY orderID DESC", idName, idValue));
    }

    public HashMap<String, Object> fetchSpecificCartDetails(String buyerID) {
        return db.readOne(String.format("SELECT * FROM `Cart` WHERE buyerID='%s'", buyerID));
    }

    public ArrayList<HashMap<String,Object>> fetchCartItems(String cartID){
        return db.readAll(String.format("SELECT c.*,ci.*,f.* FROM `Cart` c, `CartItem` ci, `Food` f WHERE c.cartID='%s' AND c.cartID=ci.cartID AND ci.foodID=f.foodID",cartID));
    }

    public ArrayList<HashMap<String, Object>> fetchFoodCategoriesFromAllShops(ArrayList<String> shopKeyArr) {
        String completeSQLStmt = "";

        int statementQty = shopKeyArr.size();

        for (int i = 0; i < statementQty; i++) {
            int currentStatement = i + 1;

            String sqlStmt = String.format("SELECT DISTINCT S.shopID, category " + "FROM Food F, Shop S "
                    + "WHERE S.shopID = F.shopID AND S.shopID = '%s'", shopKeyArr.get(i));

            completeSQLStmt = completeSQLStmt.concat(sqlStmt);

            if (isNotLastStatement(statementQty, currentStatement)) {
                completeSQLStmt = completeSQLStmt.concat(UNION);
            } else {
                completeSQLStmt = completeSQLStmt.concat("\nORDER BY shopID;");
            }
        }
        System.out.println(completeSQLStmt);
        return db.readAll(completeSQLStmt);
    }

    public HashMap<String, Object> fetchShopIdOfBuyerCart(String buyerCartID) {
        String sqlStatement = String.format(
                "SELECT shopID " + "FROM Food F, CartItem C " + "WHERE F.foodID = C.foodID AND " + "cartID = '%s';",
                buyerCartID);
        return db.readOne(sqlStatement);
    }

    public String fetchShopIdOfBuyerCartStmt(String buyerCartID) {
        String sqlStatement = String.format(
                "SELECT shopID " + "FROM Food F, CartItem C " + "WHERE F.foodID = C.foodID AND " + "cartID = '%s';",
                buyerCartID);
        return sqlStatement;
    }

    public void updateAccountDetailsForAdmin(String username, String password, String name, String email,
            String mobileNo, String NRIC, String companyBranch, String accountID) {
        db.executeCUD(String.format(
                "UPDATE `Account` a, `Admin` ad SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', ad.NRIC='%s', ad.companyBranch='%s' WHERE a.accountID='%s' AND a.accountID=ad.accountID",
                username, password, name, email, mobileNo, NRIC, companyBranch, accountID), gui);
    }

    public void updateAccountDetailsForSeller(String username, String password, String name, String email,
            String mobileNo, String address, String bankAcc, String NRIC, String licenseNumber, String accountID) {
        db.executeCUD(String.format(
                "UPDATE `Account` a, `Seller` s SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', s.address='%s', s.bankAcc='%s', s.NRIC='%s', s.licenseNumber='%s' WHERE a.accountID='%s' AND a.accountID=s.accountID",
                username, password, name, email, mobileNo, address, bankAcc, NRIC, licenseNumber, accountID), gui);
    }

    public void updateStatusToApproved(String accountType, String idName, String id) {
        db.executeCUD(String.format("UPDATE `%s` SET status=1 WHERE %s ='%s'", accountType, idName, id), gui);
    }

    public void createNewCartForBuyer(String newCartID, String newBuyerID) {
        db.executeCUD(String.format("INSERT INTO Cart VALUES ('%s','%s','%s')", newCartID, newBuyerID, null), gui);
    }

    public void registerNewBuyer(String newBuyerID, String newAddress, String newAccountID, String newCartID) {
        db.executeCUD(String.format("INSERT INTO Buyer VALUES ('%s','%s','%s','%s')", newBuyerID, newAddress,
                newAccountID, newCartID), gui);
    }

    public void updateBuyerAccountDetails(String username, String password, String name, String email, String mobileNo,
            String address, String accountID) {
        db.executeCUD(String.format(
                "UPDATE `Account` a, `Buyer` b SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', b.address='%s' WHERE a.accountID='%s' AND a.accountID=b.accountID",
                username, password, name, email, mobileNo, address, accountID), gui);
    }

    public void updateOrderStatus(String orderID, String status){
        db.executeCUD(String.format("UPDATE `Order` SET status='%s' WHERE orderID='%s'",status, orderID),gui);
    }

    private static boolean isNotLastStatement(int statementQty, int currentStatement) {
        return currentStatement < statementQty;
    }
}
