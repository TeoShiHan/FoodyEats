package Classes;

public class Cart {
    private String cartId, cartListId;
    private CartItem cartItem;

    public Cart() {  //No-arg Constructor
    	this("","",new CartItem());
    }

    public Cart(String cartId, String cartListId, CartItem cartItem) {
        this.cartId = cartId;
        this.cartListId = cartListId;
        this.cartItem = cartItem;
    }

    public Cart(Object cartId, Object cartListId, CartItem cartItem) {
        this.cartId = (String)cartId;
        this.cartListId = (String)cartListId;
        this.cartItem = cartItem;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCartListId() {
        return cartListId;
    }

    public void setCartListId(String cartListId) {
        this.cartListId = cartListId;
    }

    public CartItem getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    public static String addProductToCart(String ProductId) {
        return ProductId;
    }
    
}
