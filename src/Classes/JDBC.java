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
    private static String url = "jdbc:mysql://37.59.55.185:3306/fCe5HJjPF6?allowMultiQueries=true&rewriteBatchedStatements=true";
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

    public void openConnection() {
        try {
            JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
            this.SQLstatement = JDBC.dbLink.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);                        
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }        
    }
    
    public void executeCUD(String statement){
        openConnection();
        try{            
            int row = this.SQLstatement.executeUpdate(statement);
            System.out.println("row affected: "+row);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            closeConnection();
        }
    }    

    public HashMap<String,Object> readOne(String statement) {  
            openConnection();   
            HashMap<String,Object> hMap = new HashMap<String,Object>();        
            try{  
                this.queryRslt = this.SQLstatement.executeQuery(statement);
                this.queryRslt.first();
                for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
                    Object obj=queryRslt.getObject(i); //get the value for whatever column the result has   
                    // hMap.put(rs.getMetaData().getColumnName(i), obj); 
                    hMap.put(queryRslt.getMetaData().getColumnLabel(i), obj); 
                }                            
                return hMap;
            }catch (Exception e) { 
                System.out.println("A database error occured: " + e.getMessage());
                return null;
            }finally{
                closeConnection();
            }         
        } 

    public ArrayList<HashMap<String,Object>> readAll(String statement) {         
        openConnection();
        ArrayList<HashMap<String, Object>> hMapList = new ArrayList<HashMap<String,Object>>();
        try{                        
            this.queryRslt = this.SQLstatement.executeQuery(statement);
            while(queryRslt.next()){           
                HashMap<String, Object> hMap = new HashMap<String,Object>();
                for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) {                     
                    Object obj=queryRslt.getObject(i); //get the value for whatever column the result has (so that no need identify whats the value type)  
                    // hMap.put(rs.getMetaData().getColumnName(i), obj); 
                    hMap.put(queryRslt.getMetaData().getColumnLabel(i), obj);                    
                }                             
                hMapList.add(hMap);
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
     
    public String getNextId(String table) throws SQLException{                
        openConnection();        
        String column = table.toLowerCase()+"ID";
        this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT MAX(`%s`) as %s FROM `%s`",column,column,table));
        this.queryRslt.first();
        String currId = (String)this.queryRslt.getObject(column);
        int id = Integer.parseInt(currId.replaceAll("\\D+",""));
        closeConnection();
        return String.format("%s%05d",table.substring(0,1).toUpperCase(),id+1);
    }

    //#region used to readLast row of table, an old method used for getting nextRowId
    // // other method
    // public HashMap<String,Object> readLast(String table){     
    //     openConnection();
    //     HashMap<String,Object> hMap = new HashMap<String,Object>();        
    //     try{  
    //         this.queryRslt = this.SQLstatement.executeQuery(String.format("SELECT * FROM %s ORDER BY %sID DESC LIMIT 1",table,table.toLowerCase()));        
    //         this.queryRslt.first();                                   
    //         for (int i=1;i<=queryRslt.getMetaData().getColumnCount();i++) { 
    //             Object obj=queryRslt.getObject(i); //get the value for whatever column the result has                   
    //             hMap.put(queryRslt.getMetaData().getColumnName(i), obj);
    //         }                            
    //         return hMap;
    //     }catch (Exception e) { 
    //         System.out.println("A database error occured: " + e.getMessage());
    //         return null;
    //     }finally{
    //         closeConnection();                        
    //     }        
    // }           
    // public String getNextId(String table, char... chars){        
    //     HashMap<String,Object> lastBuyer = readLast(table);            
    //     Object buyerId = lastBuyer.get("buyerID");
        
    //     // https://stackoverflow.com/questions/4030928/extract-digits-from-a-string-in-java
    //     int id = Integer.parseInt(buyerId.toString().replaceAll("\\D+",""));
    //     // https://www.java67.com/2014/10/how-to-pad-numbers-with-leading-zeroes-in-Java-example.html
    //     String nextId = "";

    //     if(chars.length!=0){
    //         for(char ch:chars){
    //             nextId += Character.toString(ch);
    //         }
    //     }else{
    //         nextId+=table.substring(0,1);
    //     }    
    //     nextId += String.format("%05d", id+1);
        
    //     return nextId.toUpperCase();
    // }    
    // #endregion
    
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