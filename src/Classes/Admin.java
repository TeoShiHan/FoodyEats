package Classes;

public class Admin {
    private String id;
    private String name;
    private String NRIC;
    private String companyBranch;
    private String accountId;

    public Admin(){}

    public Admin(String id,String name,String NRIC,String companyBranch,String accountId){
        this.id = id;
        this.name = name;
        this.NRIC = NRIC;
        this.companyBranch = companyBranch;
        this.accountId = accountId;
    }

    public Admin(Object id,Object name,Object NRIC,Object companyBranch,Object accountId){
        this.id = (String)id;
        this.name = (String)name;
        this.NRIC = (String)NRIC;
        this.companyBranch = (String)companyBranch;
        this.accountId = (String)accountId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void addRider(){

    }

    public void register(){
        
    }

    
}
