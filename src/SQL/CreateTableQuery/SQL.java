package SQL.CreateTableQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Classes.Account;
import Classes.JDBC;
import Classes.Seller;
import Classes.Shop;

public final class SQL {
    
    private JDBC db = new JDBC();
    private final static SQL INSTANCE = new SQL();
    
    SQL(){}
    
    public static SQL getInstance(){
        return INSTANCE;
    }

    public String insertNewAccount(Account account) {
        return String.format(
            "INSERT INTO Account VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",
            Account.getNextAccID(),
            account.getUsername(),
            account.getPassword(),
            account.getName(),
            account.getEmail(),
            account.getMobileNo(),
            LocalDate.now().toString(),
            account.getAccType()
        );
    }

    public String insertNewSeller(Seller seller) {
        return String.format(
            "INSERT INTO Seller VALUES ('%s','%s','%s','%s','%s','%s','%s',%d)",
            seller.getSellerID(),
            seller.getAddress(),
            seller.getNRIC(),
            seller.getLicenseNumber(),
            seller.getBankAcc(),
            seller.getAccountID(),
            Shop.getNextID(),
            0
        );
    }

    public String insertNewShop(Shop shop){
        return String.format(
            "INSERT INTO Shop VALUES ('%s','%s','%s','%s','%s','%s','%s',%.2f,%d,'%s')",
            Shop.getNextID(),
            shop.getName(),
            LocalDate.now(),
            shop.getStartHour().toString(),
            shop.getEndHour().toString(),
            shop.getTel(),
            shop.getAddress(),
            shop.getDeliveryFee(),
            shop.getStatus(),
            shop.getImgPath()
        );
    }

    public ArrayList<HashMap<String,Object>> fetchAccountTable(){
        return db.readAll("SELECT * FROM Account");
    }

    public ArrayList<HashMap<String,Object>> fetchBuyerTable(){
        return db.readAll("SELECT * FROM Buyer");
    }

    public ArrayList<HashMap<String,Object>> fetchSellerTable(){
        return db.readAll("SELECT * FROM Seller");
    }

    public ArrayList<HashMap<String,Object>> fetchRiderTable(){
        return db.readAll("SELECT * FROM Rider");
    }

    public ArrayList<HashMap<String,Object>> fetchAdminTable(){
        return db.readAll("SELECT * FROM Admin");
    }

    public ArrayList<HashMap<String,Object>> fetchShopTable(){
        return db.readAll("SELECT * FROM Shop");
    }
}
