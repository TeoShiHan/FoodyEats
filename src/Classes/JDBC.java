package Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//Syntax to crate javadoc
//    javadoc -d <directory of HTML files> <directory to .java files> -author
import java.util.Optional;

/**
 * This class focus on simplify the usage of JDBC.
 * It can:<br>
 *     <br>1. Auto connect to remote database
 *     <br>2. Copy result set from database to array list.
 *     <br>3. Get first valur retrive from dabase
 *     <br>4. Close database connection
 * 
 * @author TeoShiHan
 * @since 1/8/2021
 */

public class JDBC {
    private ResultSet queryRslt;
    private static Connection dbLink;
    private static String url = "jdbc:mysql://37.59.55.185:3306/fCe5HJjPF6";
    private static String user = "fCe5HJjPF6";
    private static String pwrd = "iKXuA6Ozsj";
    private String userSQLstatement;    
    private Statement SQLstatement;

    /** 
     * @param userSQLstatement SELECT statement as argument
     * 
     */

    public JDBC(){
        
    }

    // public JDBC(String userSQLstatement) {
    //     this.userSQLstatement = userSQLstatement;
    //     returningExecution();
    // }

    public void openConnection() {
        try {
            JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
            this.SQLstatement = JDBC.dbLink.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                        
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }        
    }

    // public void returningExecution() {
    //     try {
    //         JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
    //         Statement SQLstatement = JDBC.dbLink.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    //         this.queryRslt = SQLstatement.executeQuery(this.userSQLstatement);

    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    // }

    
    /** 
     * @param  field Put field name. The field must VARCHAR.
     * @return String
     */
    // public String getFirstString(String field){
    //     String firstString = null;
    //     try {
    //         this.queryRslt.first();            
    //         firstString = queryRslt.getString(field);
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    //     return firstString;
    // }

    // public int getFirstInt(String field){
    //     int firstInt = 0;
    //     try {
    //         this.queryRslt.first();
    //         firstInt = queryRslt.getInt(field);
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    //     return firstInt;
    // }

    // public Double getFirstDouble(String field){
    //     Double firstDouble = null;
    //     try {
    //         this.queryRslt.first();
    //         firstDouble = queryRslt.getDouble(field);
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    //     return firstDouble;
    // }

    // public void copyStringcol(String field, ArrayList<String> dbStringCol) {
    //     try {
    //         this.queryRslt.first();
    //         while (queryRslt.next()) {
    //             dbStringCol.add(this.queryRslt.getString(field));
    //         }
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    // }

    // public void copyDoublecol(String field, ArrayList<Double> dbIntCol) {
    //     try {
    //         this.queryRslt.first();
    //         while (queryRslt.next()) {
    //             dbIntCol.add(this.queryRslt.getDouble(field));
    //         }
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    // }

    // public void copyIntcol(String field, ArrayList<String> dbIntCol) {
    //     try {
    //         this.queryRslt.first();
    //         while (queryRslt.next()) {
    //             dbIntCol.add(this.queryRslt.getString(field));
    //         }
    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     }
    // }    

    public void createOne(String table, String... column){
        openConnection();
        try{  
            String statement = "INSERT INTO %s VALUES(";            
            this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s LIMIT 1",table));
            this.queryRslt.first();            
            int colCount = queryRslt.getMetaData().getColumnCount();
            for (int i=1;i<=colCount;i++) { 
                // Object obj=queryRslt.getObject(i); //get the value for whatever column the result has                   
                // hMap.put(queryRslt.getMetaData().getColumnName(i), obj);                                
                if(i==colCount){                    
                    statement+=String.format("'%s')",column[i-1]);
                }else{
                    statement+=String.format("'%s',",column[i-1]);                    
                }                
            }                        
            int row = this.SQLstatement.executeUpdate(String.format(statement,table));                   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }

    // public void create(String table, String... columns){        
        // int row = this.SQLstatement.executeUpdate(String.format("INSERT INTO %s VALUES(?)",table,table.toLowerCase()));
        // this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s LIMIT 1",table,table.toLowerCase()));     
        // try{  
        //     this.queryRslt.first();                                   
        //     for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
        //         Object obj=queryRslt.getObject(i); //get the value for whatever column the result has   
        //         String type = queryRslt.getMetaData().getColumnTypeName(i);                
        //         hMap.put(queryRslt.getMetaData().getColumnName(i), obj);
        //     }                                        
        // }catch (Exception e) { 
        //     System.out.println("A database error occured: " + e.getMessage());
        // } 
    // }
    
    public HashMap<String,Object> readLast(String table){     
        openConnection();
        HashMap<String,Object> hMap = new HashMap<String,Object>();        
        try{  
            this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s ORDER BY %sID DESC LIMIT 1",table,table.toLowerCase()));        
            this.queryRslt.first();                                   
            for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
                Object obj=queryRslt.getObject(i); //get the value for whatever column the result has                   
                hMap.put(queryRslt.getMetaData().getColumnName(i), obj);
            }                            
            return hMap;
        }catch (Exception e) { 
            System.out.println("A database error occured: " + e.getMessage());
            return null;
        }finally{
            closeConnection();                        
        }        
    }
    
    public HashMap<String,Object> readOne(String table, String id) {  
        openConnection();   
        HashMap<String,Object> hMap = new HashMap<String,Object>();        
        try{  
            this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s WHERE %sID = '%s'",table,table.toLowerCase(),id));        
            this.queryRslt.first();                                   
            for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
                Object obj=queryRslt.getObject(i); //get the value for whatever column the result has   
                // hMap.put(rs.getMetaData().getColumnName(i), obj); 
                hMap.put(queryRslt.getMetaData().getColumnName(i), obj);
            }                            
            return hMap;
        }catch (Exception e) { 
            System.out.println("A database error occured: " + e.getMessage());
            return null;
        }finally{
            closeConnection();
        }         
    } 

    public ArrayList<HashMap<String,Object>> readAll(String table) {         
        openConnection();
        ArrayList<HashMap<String, Object>> hMapList = new ArrayList<HashMap<String,Object>>();
        try{              
            this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s",table));
            while(queryRslt.next()){           
                HashMap<String, Object> hmap = new HashMap<String,Object>();
                for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
                    Object obj=queryRslt.getObject(i); //get the value for whatever column the result has (so that no need identify whats the value type)  
                    // hMap.put(rs.getMetaData().getColumnName(i), obj); 
                    hmap.put(queryRslt.getMetaData().getColumnName(i), obj);                    
                }                
                hMapList.add(hmap);
            }                
            this.queryRslt.beforeFirst();
            return hMapList;        
        }catch (Exception e) { 
            System.out.println("A database error occured: " + e.getMessage());
            return null;
        }finally{
            closeConnection();
        }
    } 

    public void updateOne(String table, HashMap<String,Object> columns, String id){
        openConnection();
        try{  
            String statement = "UPDATE %s SET ";
            Iterator<String> iteratorColumnsKey = columns.keySet().iterator();
            while(iteratorColumnsKey.hasNext()){
                String key = iteratorColumnsKey.next();
                statement += String.format("%s='%s'",key,columns.get(key));
                if(iteratorColumnsKey.hasNext()){
                    statement += ", ";
                }else{
                    statement += String.format(" WHERE %sID='%s'",table,id);
                }
            }            
            int row = this.SQLstatement.executeUpdate(String.format(statement,table));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }
    
    public void deleteOne(String table, String id){
        openConnection();
        try{                          
            int row = this.SQLstatement.executeUpdate(String.format("DELETE FROM %s WHERE %sID = '%s'",table,table.toLowerCase(),id));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }

    public void deleteOneWithForeignKey(String table, String id1, String foreignKeyColName, String id2){
        openConnection();
        try{                          
            int row = this.SQLstatement.executeUpdate(String.format("DELETE FROM %s WHERE %sID='%s' AND %s='%s'",table,table.toLowerCase(),id1,foreignKeyColName,id2));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();            
        }finally{
            closeConnection();
        }
    }

    // other method
    public String getNextId(String table, char... chars){        
        HashMap<String,Object> lastBuyer = readLast(table);            
        Object buyerId = lastBuyer.get("buyerID");
        
        // https://stackoverflow.com/questions/4030928/extract-digits-from-a-string-in-java
        int id = Integer.parseInt(buyerId.toString().replaceAll("\\D+",""));
        // https://www.java67.com/2014/10/how-to-pad-numbers-with-leading-zeroes-in-Java-example.html
        String nextId = "";

        if(chars.length!=0){
            for(char ch:chars){
                nextId += Character.toString(ch);
            }
        }else{
            nextId+=table.substring(0,1);
        }    
        nextId += String.format("%05d", id+1);
        
        return nextId.toUpperCase();
    }

    // public static void nonReturningExecution(String userSQLStatement) {
    //     try {
    //         JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
    //         Statement SQLstatement = JDBC.dbLink.createStatement();
    //         SQLstatement.executeQuery(userSQLStatement);

    //     } catch (SQLException exc) {
    //         System.out.println("A database error occured: " + exc.getMessage());
    //     } finally {
    //         JDBC.closeConnection();
    //     }
    // }
    
    public static void closeConnection() {
        if (JDBC.dbLink != null) {
            try {
                JDBC.dbLink.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}