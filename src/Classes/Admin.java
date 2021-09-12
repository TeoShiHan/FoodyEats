package Classes;
import java.util.HashMap;

import Cache.*;
import SQL.CreateTableQuery.SQL;

public class Admin extends Account {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private String adminID;
    private String NRIC;
    private String companyBranch;
    private SQL sql = SQL.getInstance();


    public Admin(){}    

    public Admin(HashMap<String,Object>account, HashMap<String,Object>admin){
        super(
            (String)account.get("accountID"),
            (String)account.get("username"),
            (String)account.get("password"),
            (String)account.get("name"), 
            (String)account.get("email"), 
            (String)account.get("mobileNo"), 
            (String)account.get("type"),
            (String)account.get("regDate")
        );
        this.adminID = (String)admin.get("adminID");        
        this.NRIC = (String)admin.get("NRIC");        
        this.companyBranch = (String)admin.get("companyBranch");
    }

    public String getadminID() {
        return adminID;
    }
    public void setadminID(String adminID) {
        this.adminID = adminID;
    }

    public String getNRIC() {
        return NRIC;
    }
    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }
    public String getCompanyBranch() {
        return companyBranch;
    }
    public void setCompanyBranch(String companyBranch) {
        this.companyBranch = companyBranch;
    }
 
    public void verifySeller(String sellerID){
        sql.updateStatusToArrpoved("Seller", "sellerID", sellerID);
    }

    public void verifyRider(String riderID){
        sql.updateStatusToArrpoved("Rider","riderID", riderID);
    }

    public void edit(String username, String password, String name, String email, String mobileNo, String NRIC, String companyBranch) {
        super.edit(username, password, name, email, mobileNo);
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        sql.updateAccountDetailsForAdmin(username, password, name, email, mobileNo, NRIC, companyBranch, accountID);
    }

    public void edit(Account account, String NRIC, String companyBranch) {
        super.edit(account.username, account.password, account.name, account.email, account.mobileNo);
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        sql.updateAccountDetailsForAdmin(username, password, name, email, mobileNo, NRIC, companyBranch, accountID);
    }
}
