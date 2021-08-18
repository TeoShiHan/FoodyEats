package Classes;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TestJDBC {
    private static JDBC db = new JDBC();
    public static void main(String[] args) throws SQLException {
        // JDBC buyerTable = new JDBC("SELECT * FROM Buyer");
        // List<HashMap<String,Object>> buyers = buyerTable.getAll();        
        // for(HashMap<String,Object> buyer: buyers){
        //     System.out.println("Buyerrrrrr"+buyer);                
        // }            
        // System.out.println("Buyerssss: "+buyers);      
          
        // put the generic type is good practice
        // for(HashMap<String,Object> buyer: buyers){            
        //     System.out.println(buyer.get("accountID"));
        // }
        // JDBC.closeConnection();

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

        // LocalDate date = LocalDate.now();
        // System.out.println(date);        

        // LocalTime time = LocalTime.now();        
        // System.out.println(time);

        // String uniqueID = UUID.randomUUID().toString();
        // System.out.println(uniqueID);

        // JDBC lastBuyer = new JDBC("SELECT * FROM Buyer ORDER BY buyerID DESC LIMIT 1");
        // System.out.println(lastBuyer.getOne());
        // JDBC.closeConnection();

        // JDBC lastBuyer = new JDBC();
        // HashMap<String,Object> row = lastBuyer.readLast("Buyer");
        // Object buyerId = row.get("buyerID");
        // // https://stackoverflow.com/questions/4030928/extract-digits-from-a-string-in-java
        // int id = Integer.parseInt(buyerId.toString().replaceAll("\\D+",""));
        // // https://www.java67.com/2014/10/how-to-pad-numbers-with-leading-zeroes-in-Java-example.html
        // String nextId = String.format("%05d", id+1);
        // System.out.println(nextId);        

        
        // select/read all row from table
        ArrayList<HashMap<String,Object>> allBuyer = db.readAll("Buyer");
        System.out.println(allBuyer);

        // insert/create 1 row into table
        db.createOne("Buyer","lol","lol","lol","lol","lol");

        // select/read 1 row from table
        HashMap<String,Object> aBuyer = db.readOne("Buyer","lol");
        System.out.println(aBuyer);
        
        // modified/update 1 row table
        aBuyer.put("address", "Taman Sentosaaaa");
        aBuyer.put("accountID", "A23455");
        db.updateOne("Buyer", aBuyer, (String)aBuyer.get("buyerID"));

        HashMap<String,Object> newBuyer = db.readOne("Buyer","lol");
        System.out.println(newBuyer);

        // remove/delete one row
        db.deleteOne("Buyer", "lol");

        allBuyer = db.readAll("Buyer");
        System.out.println(allBuyer);
        
        // to read the table last row id
        System.out.println(db.getNextId("Buyer", 'a','b'));
    }    
}
