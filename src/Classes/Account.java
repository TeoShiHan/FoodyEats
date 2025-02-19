package Classes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import Cache.*;
import ListenerInterfaces.TableDataProcessing;
import SQL.CreateTableQuery.SQL;

public class Account implements TableDataProcessing {

    // #region : AGGREGATED / COMPOSITION VARIABLES
    private static JDBC db = new JDBC();
    private static GUI gui = GUI.getInstance();
    private static SQL sql = SQL.getInstance();
    // #endregion

    // #region : PROGRAM VARIABLES
    protected String accountID, username, password, name, email, mobileNo, accType;
    private LocalDate regDate;
    private HashMap<String, Object> accMap;
    // #endregion

    // #region : CONSTRUCTORS
    public Account() {}

    public Account(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo,
            Object accType, Object regDate) {
        this.accountID = (String) accountID;
        this.username = (String) username;
        this.password = (String) password;
        this.name = (String) name;
        this.email = (String) email;
        this.mobileNo = (String) mobileNo;
        this.accType = (String) accType;
        this.regDate = LocalDate.parse((String) regDate);
    }
    // #endregion

    // #region : GETTER AND SETTERS
    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public static String getNextAccID() {
        String nextAccId = "";
        try {
            nextAccId = db.getNextId("Account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextAccId;
    }

    public void setAccMap(HashMap<String, Object> acc) {
        this.accMap = acc;
    }

    public HashMap<String, Object> getAccMap() {
        return accMap;
    }
    // #endregion

    // #region : METHODS
    public void register() throws IOException {
        SQL sql = SQL.getInstance();
        String nextAccountID = Account.getNextAccID();
        this.accountID = nextAccountID;
        db.executeCUD(sql.insertNewAccount(this), gui);
    }

    public void notifyAccCreated(GUI gui)throws IOException{

    }

    public void login(String username, String password) throws IOException {

        // #region ： PROGRAM VARIABLES
        DataHolder data = DataHolder.getInstance();
        DataHolder.loadFromDatabase();
        ArrayList<HashMap<String, Object>> accountTable = data.getAccountTable();
        ArrayList<HashMap<String, Object>> shopTable = data.getShopTable();
        ArrayList<HashMap<String, Object>> childAccTable = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> accMap = new HashMap<String, Object>();
        HashMap<String, Object> childAccMap = new HashMap<String, Object>();
        HashMap<String, Object> shopMap = new HashMap<String, Object>();
        String accID;
        // #endregion

        if (wrongLoginInfoProvided(accountTable, username, password)) {
            gui.informationPopup("Invalid Account", "Please try again");

        } else {

            accMap = getAccMap(username, accountTable);
            accID = (String) accMap.get("accountID");

            if (accMap.get("type").equals("Buyer")) {

                // #region : CONSTRUCT BUYER OBJECT AND SAVE INTO DATA HOLDER
                childAccTable = data.getBuyerTable();
                childAccMap = getChildAccMap(accID, childAccTable);
                Buyer buyer = new Buyer(accMap, childAccMap);
                String buyerCartID = buyer.getCartID();
                String buyerID = buyer.getBuyerID();

                if (cartIsEmpty(buyerCartID)) {
                    Cart tempCart = new Cart();
                    tempCart.setBuyerID(buyerID);
                    tempCart.setCartID(buyerCartID);
                    buyer.setCart(tempCart);
                } else {
                    String shopIdInCart = (String) sql.fetchShopIdOfBuyerCart(buyerCartID).get("shopID");
                    Cart tempCart = new Cart(buyerCartID, buyerID, shopIdInCart);
                    buyer.setCart(tempCart);
                }
                data.setAccount(buyer);
                data.setBuyer(buyer);
                // #endregion
                
                gui.toNextScene("View/BuyerHome.fxml");

            } else if (accMap.get("type").equals("Rider")) {

                // #region : CONSTRUCT RIDER OBJECT AND SAVE INTO DATA HOLDER
                childAccTable = data.getRiderTable();
                childAccMap = getChildAccMap(accID, childAccTable);
                Rider rider = new Rider(accMap, childAccMap);
                data.setAccount(rider);
                data.setRider(rider);
                // #endregion

                goToAccHome(rider.isApprovedRider(), gui, "Rider");

            } else if (accMap.get("type").equals("Admin")) {

                // #region : CONSTRUCT RIDER OBJECT AND SAVE INTO DATA HOLDER
                childAccTable = data.getAdminTable();
                childAccMap = getChildAccMap(accID, childAccTable);
                Admin admin = new Admin(accMap, childAccMap);
                data.setAccount(admin);
                data.setAdmin(admin);
                // #endregion

                gui.toNextScene("View/AdminHome.fxml");
            } else {

                // #region : CREATE SELLER INSTATNCE AND STORE INTO DATA HOLDER
                childAccTable = data.getSellerTable();
                childAccMap = getChildAccMap(accID, childAccTable);

                // #region : CREATE SHOP INSTANCE
                String shopID = (String) childAccMap.get("shopID");
                shopMap = getShopMap(shopID, shopTable);
                Shop shop = new Shop(shopMap);
                // #endregion

                Seller seller = new Seller(accMap, childAccMap, shop);
                data.setAccount(seller);
                data.setSeller(seller);
                // #endregion

                goToAccHome(seller.isApprovedSeller(), gui, "Seller");
            }
        }
    }

    public void edit(String username, String password, String name, String email, String mobileNo) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public void edit(Account account) {
        this.username = account.username;
        this.password = account.password;
        this.name = account.name;
        this.email = account.email;
        this.mobileNo = account.mobileNo;
    }

    public boolean wrongLoginInfoProvided(ArrayList<HashMap<String, Object>> accountTable, String username,
            String password) {
        for (int i = 0; i < accountTable.size(); i++) {
            String username1 = username;

            String username2 = (String) accountTable.get(i).get("username");

            if (isMatch(username1, username2)) {
                String correctPassword = (String) accountTable.get(i).get("password");
                if (isMatch(password, correctPassword)) {
                    return false;
                }
            }
        }
        return true;
    }

    public HashMap<String, Object> getAccMap(String username, ArrayList<HashMap<String, Object>> accountTable) {
        HashMap<String, Object> accMap = new HashMap<String, Object>();
        for (int i = 0; i < accountTable.size(); i++) {
            String username2 = (String) accountTable.get(i).get("username");
            if (isMatch(username, username2)) {
                accMap = accountTable.get(i);
                break;
            }
        }
        return accMap;
    }

    public HashMap<String, Object> getChildAccMap(String accountID, ArrayList<HashMap<String, Object>> childAccTable) {
        HashMap<String, Object> childAccMap = new HashMap<String, Object>();
        for (int i = 0; i < childAccTable.size(); i++) {
            String accountID2 = (String) childAccTable.get(i).get("accountID");
            if (isMatch(accountID, accountID2)) {
                childAccMap = childAccTable.get(i);
                break;
            }
        }
        return childAccMap;
    }

    public HashMap<String, Object> getShopMap(String shopID, ArrayList<HashMap<String, Object>> shopTable) {
        HashMap<String, Object> shopMap = new HashMap<String, Object>();
        for (int i = 0; i < shopTable.size(); i++) {
            String shopID2 = (String) shopTable.get(i).get("shopID");
            if (isMatch(shopID, shopID2)) {
                shopMap = shopTable.get(i);
                break;
            }
        }
        return shopMap;
    }

    public String toString() {
        return "Account [accType=" + accType + ", accountID=" + accountID + ", email=" + email + ", mobileNo="
                + mobileNo + ", name=" + name + ", password=" + password + ", regDate=" + regDate + ", username="
                + username + "]";
    }

    private static void goToAccHome(boolean isApproved, GUI gui, String accType) {
        if (isApproved) {
            gui.toNextScene(String.format("View/%sHome.fxml", accType));
        } else {
            try {
                gui.informationPopup("Account Pending", "Your account has been verifying");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // #endregion

    // #region : TABLE PROCESSING IMPLEMENTATION
    public ArrayList<String> getAllKeysInTable(ArrayList<HashMap<String, Object>> accountTable) {
        ArrayList<String> shopKeysArr = new ArrayList<String>();
        for (int i = 0; i < accountTable.size(); i++) {
            String tempShopID = (String) accountTable.get(i).get("shopID");
            shopKeysArr.add(tempShopID);
        }
        return shopKeysArr;
    }

    public boolean isMatch(String str1, String str2) {
        return str1.equals(str2);
    }

    public HashMap<String, ArrayList<String>> getArrMapToKey(ArrayList<HashMap<String, Object>> table, String keyName,
            String... fieldNames) {

        // #region : VARIABLES
        int tableSize = table.size();
        int colNum = fieldNames.length;
        ArrayList<String> tempDataArr = new ArrayList<String>();
        HashMap<String, ArrayList<String>> arrMapToKey = new HashMap<String, ArrayList<String>>();
        String tempFieldData = "";
        String tempKeyNew = "";
        String tempKeyOld = "";
        // #endregion

        for (int i = 0; i < tableSize; i++) {

            tempKeyNew = (String) table.get(i).get(keyName);

            if (i == 0) {
                tempKeyOld = tempKeyNew;
            } else {
                tempKeyOld = (String) table.get(i - 1).get(keyName);
            }

            if (!isMatch(tempKeyNew, tempKeyOld)) {
                arrMapToKey.put(tempKeyOld, tempDataArr);
                tempDataArr = new ArrayList<String>();
            }

            for (int r = 0; r < colNum; r++) {
                tempFieldData = (String) table.get(i).get(fieldNames[r]);
                tempDataArr.add(tempFieldData);
            }
        }
        return arrMapToKey;
    }

    public boolean cartIsEmpty(String buyerCartID) {
        String sqlStatement = sql.fetchShopIdOfBuyerCartStmt(buyerCartID);
        return db.readOne(sqlStatement) == null;
    }
    // #endregion
}