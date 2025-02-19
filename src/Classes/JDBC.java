package Classes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
//Syntax to crate javadoc
//    javadoc -d <directory of HTML files> <directory to .java files> -author

import Cache.GUI;

/**
 * This class focus on simplify the usage of JDBC. It can:<br>
 * <br>
 * 1. Auto connect to remote database <br>
 * 2. Copy result set from database to array list. <br>
 * 3. Get first valur retrive from dabase <br>
 * 4. Close database connection
 * 
 * @author TeoShiHan
 * @since 1/8/2021
 */

public class JDBC {
    private ResultSet queryRslt;
    private static Connection dbLink;
    private static String url = "jdbc:mysql://127.0.0.1:3306/foodyeats?allowMultiQueries=true&rewriteBatchedStatements=true";
    private static String user = "foodyeats";
    private static String pwrd = "foodyeats";
    private Statement SQLstatement;

    /**
     * @param userSQLstatement SELECT statement as argument
     * 
     */

    public JDBC() {

    }

    public void openConnection() {
        try {
            JDBC.dbLink = DriverManager.getConnection(url, user, pwrd);
            this.SQLstatement = JDBC.dbLink.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException exc) {
            System.out.println("A database error occured: " + exc.getMessage());
        }
    }

    public int executeCUD(String statement, GUI gui) {
        openConnection();
        try {
            return this.SQLstatement.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                gui.informationPopup("Something wrong", "There is an error when inserting or updating the database");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return 0;
        } finally {
            closeConnection();
        }
    }

    public HashMap<String, Object> readOne(String statement) {
        openConnection();
        HashMap<String, Object> hMap = new HashMap<String, Object>();
        try {
            this.queryRslt = this.SQLstatement.executeQuery(statement);
            this.queryRslt.first();
            for (int i = 1; i <= queryRslt.getMetaData().getColumnCount(); i++) {
                Object obj = queryRslt.getObject(i); // get the value for whatever column the result has
                // hMap.put(rs.getMetaData().getColumnName(i), obj);
                hMap.put(queryRslt.getMetaData().getColumnLabel(i), obj);
            }
            return hMap;
        } catch (Exception e) {
            System.out.println("A database error occured: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
    }

    public ArrayList<HashMap<String, Object>> readAll(String statement) {
        openConnection();
        ArrayList<HashMap<String, Object>> hMapList = new ArrayList<HashMap<String, Object>>();
        try {
            this.queryRslt = this.SQLstatement.executeQuery(statement);
            while (queryRslt.next()) {
                HashMap<String, Object> hMap = new HashMap<String, Object>();
                for (int i = 1; i <= queryRslt.getMetaData().getColumnCount(); i++) {
                    Object obj = queryRslt.getObject(i); // get the value for whatever column the result has (so that no
                                                         // need identify whats the value type)
                    // hMap.put(rs.getMetaData().getColumnName(i), obj);
                    hMap.put(queryRslt.getMetaData().getColumnLabel(i), obj);
                }
                hMapList.add(hMap);
            }
            this.queryRslt.beforeFirst();
            return hMapList;
        } catch (Exception e) {
            System.out.println("A database error occured: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }
    }

    public String getNextId(String table) throws SQLException {
        openConnection();
        String column = table.toLowerCase() + "ID";
        this.queryRslt = this.SQLstatement
                .executeQuery(String.format("SELECT MAX(`%s`) as %s FROM `%s`", column, column, table));
        this.queryRslt.first();
        String currId = (String) this.queryRslt.getObject(column);
        int id = Integer.parseInt(currId.replaceAll("\\D+", ""));
        closeConnection();
        return String.format("%s%05d", table.substring(0, 1).toUpperCase(), id + 1);
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