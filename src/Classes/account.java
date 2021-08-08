package Classes;

public class account {
    public String getAccountID() {
        return accountID;
    }
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    private String accountID;
    private String accountType;
    public account(String accountID, String accountType) {
        this.accountID = accountID;
        this.accountType = accountType;
    }
    
}
