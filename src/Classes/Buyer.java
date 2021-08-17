package Classes;

public class Buyer {
    private String buyerId, address, accountId, cartId;
    private Cart cart;
    private Order order;

    public Buyer() {  //No-arg Constructor
    	this("","","","",new Cart(), new Order());
    }

    public Buyer(String buyerId, String address, String accountId, String cartId, Cart cart, Order order) {
        this.buyerId = buyerId;
        this.address = address;
        this.accountId = accountId;
        this.cartId = cartId;
        this.cart = cart;
        this.order = order;
    } 

    public Buyer(Object buyerId, Object address, Object accountId, Object cartId, Cart cart, Order order) {
        this.buyerId =(String)buyerId;
        this.address =(String)address;
        this.accountId =(String)accountId;
        this.cartId = (String)cartId;
        this.cart = cart;
        this.order = order;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    } 

    public static String getCart(String BuyerId) {
        return BuyerId;
    }
}





