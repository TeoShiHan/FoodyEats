package Classes;

public class Payment {
    private String paymentID,method,orderID;
    
    public Payment(){
        
    }    
    // for scenario when user pay, to create new payment
    public Payment(String method){
        this.method = method;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }    

    // public String create(String paymentID, String orderID){
    //     return String.format("INSERT INTO `Payment` (paymentID,method,orderID) VALUES (%s,%s,%s);",paymentID,method,orderID);
    // }
}
