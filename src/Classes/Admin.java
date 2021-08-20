package Classes;

public class Admin {
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

    public void addRider(){

    }

    public void register(){
        
    }    
}
