package Classes;

import java.time.LocalDate;

import Cache.DataHolder;
 
public class Account {    
    private static JDBC db;        
    private static DataHolder data = DataHolder.getInstance();

    private String id,username,password,email,mobileNo;    
    private LocalDate regDate;
    private int accType;

    public Account(){
        this("","","","","",-1);
    }        

    public Account(String id, String username, String password, String email, String i, int accType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobileNo = i;    
        this.accType = accType;
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

    public static void register(){

    }

    // public static boolean login(String uname, String pwd){
    //     db = new JDBC(String.format("SELECT * FROM Account WHERE userName='%s' AND password='%s'",uname,pwd));        
    //     if(db.getFirstString("accountID")!=null){
    //         String accId = db.getFirstString("accountID");
    //         String username = db.getFirstString("userName");
    //         String password = db.getFirstString("password");
    //         String email = db.getFirstString("email");
    //         String mobileNo = db.getFirstString("mobileNo");
    //         int accType = db.getFirstInt("accType");            
    //         data.setAccount(new Account(accId,username,password,email,mobileNo,accType));            
    //         if(accType==0){
    //             db = new JDBC(String.format("SELECT * FROM Buyer WHERE accountID='%s'",accId));
    //             String id = db.getFirstString("buyerId");
    //             String address = db.getFirstString("address");
    //             String cartId = db.getFirstString("cartID");
    //             data.setBuyer(new Buyer(id,address,accId,cartId));
    //         }else if(accType==1){
    //             db = new JDBC(String.format("SELECT * FROM Seller WHERE accountID='%s'",accId));
    //             String id = db.getFirstString("sellerId");
    //             String address = db.getFirstString("address");
    //             String state = db.getFirstString("state");
    //             String nric = db.getFirstString("NRIC");
    //             String licenseNo = db.getFirstString("licenseNumber");
    //             String bankAcc = db.getFirstString("bankAcc");                
    //             String shopId = db.getFirstString("shopID");                
    //             data.setSeller(new Seller(id,address,accId,state,nric,licenseNo,bankAcc,shopId));
    //         }else if(accType==2){
    //             db = new JDBC(String.format("SELECT * FROM Rider WHERE accountID='%s'",accId));
    //             String id = db.getFirstString("riderID");
    //             String name = db.getFirstString("riderName");
    //             String vechId = db.getFirstString("vechicleID");
    //             data.setRider(new Rider(id,name,accId,vechId));
    //         }

    //         JDBC.closeConnection();
    //         return true;            
    //     }else{            
    //         JDBC.closeConnection();
    //         return false;
    //     }        
    // }    
}