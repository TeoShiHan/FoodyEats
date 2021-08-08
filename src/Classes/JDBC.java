package Classes;

import java.sql.*;
import java.util.ArrayList;
//Syntax to crate javadoc
//    javadoc -d <directory of HTML files> <directory to .java files> -author

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

    /** 
     * @param userSQLstatement SELECT statement as argument
     * 
     */
    public JDBC(String userSQLstatement) {
        this.userSQLstatement = userSQLstatement;
        returningExecution();
    }

    public void returningExecution() {
        try {
            JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
            Statement SQLstatement = JDBC.dbLink.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.queryRslt = SQLstatement.executeQuery(this.userSQLstatement);

        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
    }

    
    /** 
     * @param  field Put field name. The field must VARCHAR.
     * @return String
     */
    public String getFirstString(String field){
        String firstString = null;
        try {
            this.queryRslt.first();
            firstString = queryRslt.getString(field);
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
        return firstString;
    }

    public int getFirstInt(String field){
        int firstInt = 0;
        try {
            this.queryRslt.first();
            firstInt = queryRslt.getInt(field);
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
        return firstInt;
    }

    public Double getFirstDouble(String field){
        Double firstDouble = null;
        try {
            this.queryRslt.first();
            firstDouble = queryRslt.getDouble(field);
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
        return firstDouble;
    }

    public void copyStringcol(String field, ArrayList<String> dbStringCol) {
        try {
            this.queryRslt.first();
            while (queryRslt.next()) {
                dbStringCol.add(this.queryRslt.getString(field));
            }
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
    }

    public void copyDoublecol(String field, ArrayList<Double> dbIntCol) {
        try {
            this.queryRslt.first();
            while (queryRslt.next()) {
                dbIntCol.add(this.queryRslt.getDouble(field));
            }
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
    }

    public void copyIntcol(String field, ArrayList<String> dbIntCol) {
        try {
            this.queryRslt.first();
            while (queryRslt.next()) {
                dbIntCol.add(this.queryRslt.getString(field));
            }
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
    }

    public static void nonReturningExecution(String userSQLStatement) {
        try {
            JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
            Statement SQLstatement = JDBC.dbLink.createStatement();
            SQLstatement.executeQuery(userSQLStatement);

        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        } finally {
            JDBC.closeConnection();
        }
    }
    
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