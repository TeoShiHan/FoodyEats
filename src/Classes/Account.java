package Classes;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import Cache.*;
 
public class Account {    
    private static JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private static GUI gui = GUI.getInstance();

    protected String accountID,username,password,name,email,mobileNo,accType;
    private LocalDate regDate;    

    public Account(){
        this("","","","","","","");
    }
    public Account(String accountID, String username, String password, String name, String email, String mobileNo, String accType) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;    
        this.accType = accType;
    } 
    public Account(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo, Object accType) {
        this.accountID = (String)accountID;
        this.username = (String)username;
        this.password = (String)password;
        this.name = (String)name;
        this.email = (String)email;
        this.mobileNo = (String)mobileNo;    
        this.accType = (String)accType;
    }    

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

    public void register() throws IOException {        
        try {
            String nextAccountID = db.getNextId("Account");
            db.executeCUD(String.format("INSERT INTO Account VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",nextAccountID,username,password,name,email,mobileNo,LocalDate.now().toString(),accType));
            this.accountID = nextAccountID;            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void login(String username, String password) throws IOException{  
        DataHolder data = DataHolder.getInstance();        
        HashMap<String,Object> acc = db.readOne(String.format("SELECT * FROM `Account` WHERE username='%s' AND password='%s' LIMIT 1",username,password));
        if(acc==null){
            gui.informationPopup("Invalid Account", "Please try again");
        }else{                        
            HashMap<String,Object> childAcc = db.readOne(String.format("SELECT * FROM `%s` WHERE accountID='%s'",acc.get("type"),acc.get("accountID")));            
            if(acc.get("type").equals("Buyer")){                  
                Buyer buyer = new Buyer(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                    acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("buyerID"),
                    childAcc.get("address"),childAcc.get("cartID")
                );   
                data.setAccount(buyer);
                data.setBuyer(buyer);
                gui.toNextScene(String.format("View/%sHome.fxml",acc.get("type")));
            }else if(acc.get("type").equals("Admin")){
                // Admin admin = new Admin(adminID, name, NRIC, companyBranch, accountID);
                // data.setAccount(admin);
                // data.setBuyer(admin);
                gui.toNextScene(String.format("View/%sHome.fxml",acc.get("type")));
            }else{
                if((int)childAcc.get("status")==1){                    
                    if(acc.get("type").equals("Rider")){
                        Rider rider = new Rider(
                            acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                            acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("riderID"),
                            childAcc.get("vehicleID")
                        );                        
                        data.setAccount(rider);   
                        data.setRider(rider);
                    }else if(acc.get("type").equals("Seller")){  
                        Seller seller = new Seller(
                            acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("name"),
                            acc.get("email"),acc.get("mobileNo"),acc.get("type"),childAcc.get("sellerID"),
                            childAcc.get("address"),childAcc.get("NRIC"),childAcc.get("licenseNumber"),
                            childAcc.get("bankAcc"),childAcc.get("shopID")
                        );
                        data.setAccount(seller);
                        data.setSeller(seller);
                    }
                    gui.toNextScene(String.format("View/%sHome.fxml",acc.get("type")));
                }else{
                    gui.informationPopup("Account Pending", "Your account has been verifying");
                }                
            }            
        }         
    }    

    public void edit(String username, String password, String name, String email, String mobileNo){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public void loadUser(){

    }
}