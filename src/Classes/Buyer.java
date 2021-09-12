package Classes;

import Cache.*;
import SQL.CreateTableQuery.SQL;
import javafx.concurrent.Task;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Buyer extends Account {

    // #region : COMPOSITION / AGGREGATION VARIABLES
    private JDBC db = new JDBC();
    private DataHolder data = DataHolder.getInstance();
    private GUI gui = GUI.getInstance();
    private Cart cart;
    private SQL sql = SQL.getInstance();
    // #endregion

    // #region : PROGRAM VARIABLES
    private String buyerID, address, cartID;
    private ArrayList<Order> orders;
    // #endregion

    // #region : CONSTRUCTORS
    public Buyer() { // No-arg Constructor

    }

    public Buyer(Object accountID, Object username, Object password, Object name, Object email, Object mobileNo,
            Object accType, Object regDate, Object buyerID, Object address, Object cartID) {
        super(accountID, username, password, name, email, mobileNo, accType, regDate);
        this.buyerID = (String) buyerID;
        this.address = (String) address;
        this.cartID = (String) cartID;
    }

    public Buyer(HashMap<String, Object> account, HashMap<String, Object> buyer) {
        super((String) account.get("accountID"), (String) account.get("username"), (String) account.get("password"),
                (String) account.get("name"), (String) account.get("email"), (String) account.get("mobileNo"),
                (String) account.get("type"), (String) account.get("regDate"));
        this.buyerID = (String) buyer.get("buyerID");
        this.address = (String) buyer.get("address");
        this.cartID = (String) buyer.get("cartID");
    }
    // #endregion

    public String getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void loadCart() {
        HashMap<String, Object> cartDetails = sql.fetchSpecificCartDetails(buyerID);
        this.cart = new Cart(cartDetails.get("cartID"), cartDetails.get("buyerID"), cartDetails.get("shopID"));
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void loadOrders() {
        this.orders = new ArrayList<>();
        ArrayList<HashMap<String, Object>> orderTable = sql.fetchSpecificOrderDataset("buyerID", buyerID);
        for (HashMap<String, Object> o : orderTable) {
            this.orders.add(new Order(o));
        }
        data.setOrders(this.orders);
    }

    @Override
    public void register() throws IOException {
        super.register();
        try {
            String nextBuyerID = db.getNextId("Buyer");
            String nextCartID = db.getNextId("Cart");
            sql.createNewCartForBuyer(nextCartID, nextBuyerID);
            this.cart = new Cart(nextCartID, nextBuyerID, null);
            sql.registerNewBuyer(nextBuyerID, address, accountID, nextCartID);
            this.buyerID = nextBuyerID;
            this.cartID = nextCartID;
            data.setAccount(this);
            data.setBuyer(this);
            gui.toNextScene("View/BuyerHome.fxml");
            gui.notAlertInProgress();
        } catch (SQLException e) {
            e.printStackTrace();
            gui.informationPopup("Something wrong", "There is an error when inserting to database");
        }
    }

    public void edit(String username, String password, String name, String email, String mobileNo, String address) {
        super.edit(username, password, name, email, mobileNo);
        this.address = address;
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException, SQLException {
                db.executeCUD(String.format(
                        "UPDATE `Account` a, `Buyer` b SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', b.address='%s' WHERE a.accountID='%s' AND a.accountID=b.accountID",
                        username, password, name, email, mobileNo, address, accountID), gui);
                return null;
            }
        };
        new Thread(task).start();
    }

    public void edit(Account account, String address) {
        super.edit(account.username, account.password, account.name, account.email, account.mobileNo);
        this.address = address;
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws IOException, SQLException {
                db.executeCUD(String.format(
                        "UPDATE `Account` a, `Buyer` b SET a.username='%s', a.password='%s', a.name='%s', a.email='%s', a.mobileNo='%s', b.address='%s' WHERE a.accountID='%s' AND a.accountID=b.accountID",
                        username, password, name, email, mobileNo, address, accountID), gui);
                return null;
            }
        };
        new Thread(task).start();
    }

    @Override
    public String toString() {

        return super.toString() + "Buyer [address=" + address + ", buyerID=" + buyerID + ", cart=" + cart + ", cartID="
                + cartID + "]";
    }
    // public static String getCart(String BuyerID) {
    // return BuyerID;
    // }
}