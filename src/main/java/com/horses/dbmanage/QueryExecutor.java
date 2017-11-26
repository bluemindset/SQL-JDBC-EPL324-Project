package com.horses.dbmanage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * !APLA GIA ELEGHO TWN QUERIES!
 * Created by tomis on 26/11/2017.
 */
public class QueryExecutor {
    private ConnectionManager connectionManager;
    private Connection conn;
    QueryExecutor(ConnectionManager cm) {
        this.connectionManager = cm;
        conn = cm.getDBConnection();
    }

    void selectHorsesLike(String word) throws SQLException {
        String storProcString = "{call selectHorsesLike(?)}";
        ResultSet rs = null;
        try (CallableStatement cstmt = conn.prepareCall(storProcString)) {
            cstmt.setNString(1, "HORSE");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } finally {
            if(rs != null)
                rs.close();
        }
    }

    void selectRacesByMeetingDate(java.util.Date date) throws SQLException {
        String storProcString = "{call selectRacesByMeetingDate(?)}";
        long timestamp = date.getTime();
        ResultSet rs = null;
        try (CallableStatement cstmt = conn.prepareCall(storProcString)) {
            //prepei prwta naperasw java.sql.date antikeimeno
            cstmt.setDate(1, new java.sql.Date(timestamp));
            rs = cstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("race_id"));
            }
        } finally {
            if(rs != null)
                rs.close();
        }
    }

    public static void main(String args[]) throws SQLException, ParseException {
        String dbConnString = "jdbc:sqlserver://localhost:1433;databaseName=Horses;user=SA;password=Password123;";
        QueryExecutor qe = new QueryExecutor(new ConnectionManager(dbConnString));
        //Q1
        qe.selectHorsesLike("Horse");
        //Q2
        Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse("2010-01-08");
        System.out.println(d.getTime());
        qe.selectRacesByMeetingDate(d);


    }
}
