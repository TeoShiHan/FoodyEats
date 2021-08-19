package Classes;

import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import Cache.DataHolder;
 
public class Account {    
    private static JDBC db = new JDBC();
    private static DataHolder data = DataHolder.getInstance();

    protected String id,username,password,email,mobileNo,accType;
    private LocalDate regDate;    

    public Account(){
        this("","","","","","");
    }
    public Account(String id, String username, String password, String email, String mobileNo, String accType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = mobileNo;    
        this.accType = accType;
    } 
    public Account(Object id, Object username, Object password, Object email, Object mobileNo, Object accType) {
        this.id = (String)id;
        this.username = (String)username;
        this.password = (String)password;
        this.email = (String)email;
        this.mobileNo = (String)mobileNo;    
        this.accType = (String)accType;
    }    

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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

    public static void register(){

    }

    public static boolean login(String username, String password){        
        HashMap<String,Object> acc = db.readOne(String.format("SELECT * FROM Account WHERE username='%s' AND password='%s' LIMIT 1",username,password));
        if(acc==null){
            return false;
        }else{                        
            data.addObjectHolder("accType", acc.get("accType"));
            HashMap<String,Object> childAcc = db.readOne(String.format("SELECT * FROM %s WHERE accountID='%s'",acc.get("accType"),acc.get("accountID")));            
            if(acc.get("accType").equals("Buyer")){                
                Buyer buyer = new Buyer(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("email"),
                    acc.get("mobileNo"),acc.get("accType"),childAcc.get("buyerID"),childAcc.get("address"),
                    childAcc.get("accountID"),childAcc.get("cartID")
                );
                data.setBuyer(buyer);                                
            }else if(acc.get("accType").equals("Rider")){
                Rider rider = new Rider(
                    acc.get("accountID"),acc.get("username"),acc.get("password"),acc.get("email"),
                    acc.get("mobileNo"),acc.get("accType"),childAcc.get("buyerID"),childAcc.get("address"),
                    childAcc.get("accountID"),childAcc.get("cartID")
                );                      
                data.setRider(rider);
            }else if(acc.get("accType").equals("Seller")){                
                // data.setBuyer(new Buyer(...));                
            }
            return true;
        }
        // if(db.getFirstString("accountID")!=null){
        //     String accId = db.getFirstString("accountID");
        //     String username = db.getFirstString("userName");
        //     String password = db.getFirstString("password");
        //     String email = db.getFirstString("email");
        //     String mobileNo = db.getFirstString("mobileNo");
        //     int accType = db.getFirstInt("accType");            
        //     data.setAccount(new Account(accId,username,password,email,mobileNo,accType));            
        //     if(accType==0){
        //         db = new JDBC(String.format("SELECT * FROM Buyer WHERE accountID='%s'",accId));
        //         String id = db.getFirstString("buyerId");
        //         String address = db.getFirstString("address");
        //         String cartId = db.getFirstString("cartID");
        //         data.setBuyer(new Buyer(id,address,accId,cartId));
        //     }else if(accType==1){
        //         db = new JDBC(String.format("SELECT * FROM Seller WHERE accountID='%s'",accId));
        //         String id = db.getFirstString("sellerId");
        //         String address = db.getFirstString("address");
        //         String state = db.getFirstString("state");
        //         String nric = db.getFirstString("NRIC");
        //         String licenseNo = db.getFirstString("licenseNumber");
        //         String bankAcc = db.getFirstString("bankAcc");                
        //         String shopId = db.getFirstString("shopID");                
        //         data.setSeller(new Seller(id,address,accId,state,nric,licenseNo,bankAcc,shopId));
        //     }else if(accType==2){
        //         db = new JDBC(String.format("SELECT * FROM Rider WHERE accountID='%s'",accId));
        //         String id = db.getFirstString("riderID");
        //         String name = db.getFirstString("riderName");
        //         String vechId = db.getFirstString("vechicleID");
        //         data.setRider(new Rider(id,name,accId,vechId));
        //     }

        //     JDBC.closeConnection();
        //     return true;            
        // }else{            
        //     JDBC.closeConnection();
        //     return false;
        // }        
    }    

    public void loadUser(){

    }
}