package Classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

public class TestJDBC {
    public static void main(String[] args) {
        JDBC buyerTable = new JDBC("SELECT * FROM Buyer");
        List<HashMap<String,Object>> buyers = buyerTable.getAll();        
        for(HashMap<String,Object> buyer: buyers){
            System.out.println("Buyerrrrrr"+buyer);                
        }            
        System.out.println("Buyerssss: "+buyers);      
          
        // put the generic type is good practice
        // for(HashMap<String,Object> buyer: buyers){            
        //     System.out.println(buyer.get("accountID"));
        // }
        JDBC.closeConnection();

        // JDBC buyerTable = new JDBC("SELECT * FROM Buyer WHERE buyerID='B34615'");                
        // HashMap<String,Object> buyer = buyerTable.getOne();                
        // System.out.println(buyer);
        // HashMap<String,Object> buyee = buyerTable.getOne();
        // System.out.println(buyee);        
        // System.out.println(buyee.get("accountID"));
        // JDBC.closeConnection();

        // JDBC buyerTable = new JDBC("SELECT * FROM Buyer");
        // HashMap<String,Object> buyerOne = buyerTable.getOne();
        // System.out.println(buyerOne);
        // // System.out.println(buyerOne.get("deliveryFee").getClass());        
        // Buyer buyer = new Buyer(buyerOne.get("buyerID"),buyerOne.get("address"),buyerOne.get("accountID"),buyerOne.get("cartID"));
        // // System.out.println(buyer.getAccountId());
        // // System.out.println(buyer.getId());
        // JDBC.closeConnection();

        LocalDate date = LocalDate.now();
        System.out.println(date);        

        LocalTime time = LocalTime.now();        
        System.out.println(time);
    }
}
