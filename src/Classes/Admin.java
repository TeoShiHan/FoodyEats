package Classes;
import java.util.HashMap;

import Cache.*;

public class Admin extends Account {
    private JDBC db = new JDBC();
    private GUI gui = GUI.getInstance();
    private String adminID;
    private String NRIC;
    private String companyBranch;

    public Admin(){
        this("","","");
    }

    public Admin(String adminID,String NRIC,String companyBranch){
        this.adminID = adminID;
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;

    }

    public Admin(Object adminID,Object NRIC,Object companyBranch){
        this.adminID = (String)adminID;
        this.NRIC = (String)NRIC;
        this.companyBranch = (String)companyBranch;
 
    }

    public Admin(String accountID, String username, String password, String name, String email, String mobileNo, String accType, String adminID,String NRIC,String companyBranch) {        
        super(accountID, username, password, name, email, mobileNo, accType);
        this.adminID = adminID;
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;           
    }

    public Admin(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo, Object accType, Object adminID,Object NRIC,Object companyBranch) {
        super(accountID, username, password, name, email, mobileNo, accType);
        this.adminID = (String)adminID;
        this.NRIC = (String)NRIC;
        this.companyBranch = (String)companyBranch;     
    }

    public Admin(HashMap<String,Object>account, HashMap<String,Object>admin){
        super(
            (String)account.get("accountID"),
            (String)account.get("username"),
            (String)account.get("password"),
            (String)account.get("name"), 
            (String)account.get("email"), 
            (String)account.get("mobileNo"), 
            (String)account.get("type")
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
 
    public void verifySeller(String seLLerID){
        db.executeCUD(String.format("UPDATE `Seller` SET status=1 WHERE sellerID='%s'",seLLerID),gui);
    }

    public void verifyRider(String rIDerID){
        db.executeCUD(String.format("UPDATE `Rider` SET status=1 WHERE riderID='%s'",rIDerID),gui);

    }

    public void edit(String username, String password, String name, String email, String mobileNo, String NRIC, String companyBranch) {
        super.edit(username, password, name, email, mobileNo);
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        db.executeCUD(String.format("UPDATE `Account` a, `Admin` ad SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', ad.NRIC='%s', ad.companyBranch='%s' WHERE a.accountID='%s' AND a.accountID=ad.accountID",username,password,name,email,mobileNo,NRIC,companyBranch,accountID),gui);
    }

    public void edit(Account account, String NRIC, String companyBranch) {
        super.edit(account.username, account.password, account.name, account.email, account.mobileNo);
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        db.executeCUD(String.format("UPDATE `Account` a, `Admin` ad SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', ad.NRIC='%s', ad.companyBranch='%s' WHERE a.accountID='%s' AND a.accountID=ad.accountID",username,password,name,email,mobileNo,NRIC,companyBranch,accountID),gui);
    }
}
