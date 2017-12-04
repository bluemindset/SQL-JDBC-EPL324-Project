package com.horses.dbmanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tomis on 25/11/2017.
 */
public class ConnectionManager {

    private static final boolean DEBUG=false;
    private static boolean dbDriverLoaded = false;
    private static Connection conn = null;
    private String dbConnString;

    public String getDbConnString() {
        return dbConnString;
    }

    public ConnectionManager(String dbConnString) {
        this.dbConnString = dbConnString;
    }
    /**
     * A method that returns a connection to MS SQL server DB
     *
     * @return The connection object to be used.
     */
    Connection getDBConnection() {
        if (!dbDriverLoaded)
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                dbDriverLoaded = true;
            } catch (ClassNotFoundException e) {
                System.out.println("Cannot load DB driver!");
                return null;
            }

        try {
            if (conn == null)
                conn = DriverManager.getConnection(dbConnString);
            else if (conn.isClosed())
                conn = DriverManager.getConnection(dbConnString);
        } catch (SQLException e) {
            System.out.print("Cannot connect to the DB!\nGot error: ");
            System.out.print(e.getErrorCode());
            System.out.print("\nSQL State: ");
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void closeConnection(){
        try {
            if (!conn.isClosed()) {
                System.out.print("Disconnecting from database...");
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot Close connection");

        }
    }
}
