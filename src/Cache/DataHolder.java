package Cache;
import Classes.Account;

import java.util.ArrayList;
import java.util.HashMap;


public final class DataHolder {  
    private Account account;
    private HashMap<String,String> stringHolder = new HashMap<String,String>();
    private HashMap<String,Double> doubleHolder = new HashMap<String,Double>();
    private HashMap<String,Boolean> booleanHolder = new HashMap<String,Boolean>();

    private final static DataHolder INSTANCE = new DataHolder();
    
    private DataHolder() {}
    
    public static DataHolder getInstance() {
      return INSTANCE;
    }
    
    public void setAccount(Account acc) {
      this.account = acc;
    }    
    public Account getAccount() {
      return this.account;
    }

    // public void setBuyer(Buyer buyer) {
    //   this.buyer = buyer;
    // }    
    // public Buyer getBuyer() {
    //   return this.buyer;
    // }

    // public void setSeller(Seller seller) {
    //   this.seller = seller;
    // }    
    // public Seller getSeller() {
    //   return this.seller;
    // }

    // public void setRider(Rider rider) {
    //   this.rider = rider;
    // }    
    // public Rider getRider() {
    //   return this.rider;
    // }  

    public void addStringHolder(String key,String value){
      this.stringHolder.put(key, value);
    }
    public String getStringHolder(String key){
      String stringValue= stringHolder.get(key);
      stringHolder.remove(key);
      return stringValue;
    }

    public void addDoubleHolder(String key,double value){
      this.doubleHolder.put(key, value);
    }
    public double getDoubleHolder(String key){
      double doubleValue= doubleHolder.get(key);
      doubleHolder.remove(key);
      return doubleValue;
    }

    public void addBooleanHolder(String key,boolean value){
      this.booleanHolder.put(key, value);
    }
    public boolean getBooleanHolder(String key){
      boolean booleanValue = booleanHolder.get(key);
      booleanHolder.remove(key);
      return booleanValue;
    }
    
    public void clear(){
      this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
      // this.account = new Account();
    }
  }
