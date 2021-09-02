package Classes;
import Cache.*;

public class Admin extends Account {
    private JDBC db = new JDBC();
    private GUI gui;
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
}
