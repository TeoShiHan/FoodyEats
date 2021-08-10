package Cache;

import Classes.Account;

public final class DataHolder {
      
    private Account account;    
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
  }
