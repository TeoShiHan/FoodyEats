package Classes;

import Cache.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Account {
    private JDBC db = new JDBC();

    private String adminID;
    private String name;
    private String NRIC;
    private String companyBranch;
    private String accountID;

    public Admin(){}

    public Admin(String adminID,String name,String NRIC,String companyBranch,String accountID){
        this.adminID = adminID;
        this.name = name;
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        this.accountID = accountID;
    }

    public Admin(Object adminID,Object name,Object NRIC,Object companyBranch,Object accountID){
        this.adminID = (String)adminID;
        this.name = (String)name;
        this.NRIC = (String)NRIC;
        this.companyBranch = (String)companyBranch;
        this.accountID = (String)accountID;
    }

    public String getadminID() {
        return adminID;
    }
    public void setadminID(String adminID) {
        this.adminID = adminID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getaccountID() {
        return accountID;
    }
    public void setaccountID(String accountID) {
        this.accountID = accountID;
    }

    public void verifySeller(String seLLerID){
        db.executeCUD(String.format("UPDATE Seller SET status=1 WHERE sellerID='%s'",seLLerID));
    }

    public void verifyRider(String rIDerID){
        db.executeCUD(String.format("UPDATE Rider SET status=1 WHERE riderID='%s'",rIDerID));

    }

 
}
