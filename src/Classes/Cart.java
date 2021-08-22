package Classes;

public class Cart {
    private String cartID, cartListID;
    private CartItem cartItem;

    public Cart() {  //No-arg Constructor
    	this("","",new CartItem());
    }

    public Cart(String cartID, String cartListID, CartItem cartItem) {
        this.cartID = cartID;
        this.cartListID = cartListID;
        this.cartItem = cartItem;
    }

    public Cart(Object cartID, Object cartListID, CartItem cartItem) {
        this.cartID = (String)cartID;
        this.cartListID = (String)cartListID;
        this.cartItem = cartItem;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getCartListID() {
        return cartListID;
    }

    public void setCartListID(String cartListID) {
        this.cartListID = cartListID;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public static String addProductToCart(String ProductID) {
        return ProductID;
    }
    
}
