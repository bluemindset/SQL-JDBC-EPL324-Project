package com.horses.dbmanage;
import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

/**
 * !APLA GIA ELEGHO TWN QUERIES!
 * Created by tomis on 26/11/2017.
 */
public class QueryExecutor {
    public static final String USERNAME = "ntzior01";
    public static final String DEFAULT_PASS = "swuYezukA6RE";
    //private static final String USERNAME = "cchadj01";
   // private static final String DEFAULT_PASS = "s3agazuhespU";
    private static final String PASSWORD = DEFAULT_PASS;
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

    void executeQuery8b() throws SQLException {
    	ResultSet rs = null;
        try (CallableStatement cstmt = conn.prepareCall("{call query8_c}")) {
            cstmt.executeQuery();
             rs = cstmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1));
            }
        } finally {
            if(rs != null)
                rs.close();
        }
    }






    /**-------      Q9 HORSE --------------------**/


    public void executeQuery9Horse(boolean meeting,boolean oneHorse,boolean allHorses) throws SQLException {

        if (meeting) {
            System.out.println("Please insert Date and Time of Race");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            String d = reader.nextLine();
            String t = reader.nextLine();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat tf = new SimpleDateFormat("HH:mm:ss");
            Date dateRace;
            java.sql.Date dateInsert = null;
            java.sql.Time timeInsert = null;
            java.util.Date  runTime ;
            try {
                dateRace = df.parse(d);
                runTime = tf.parse(t);
                dateInsert = RecordInserter.convertUtilToSql(dateRace);
                timeInsert = RecordInserter.convertUtilToSqlTime(runTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            executeQuery9_aDate(dateInsert,timeInsert);
            return;
        }
        if (oneHorse) {
            System.out.println("Please enter HORSE ID ");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int n = reader.nextInt();
            reader.close();
            executeQuery9_aHorse(n);
            return;
        }
        if (allHorses) {
            System.out.println("Statement for all horses");
            executeQuery9_allHorse();
        }

    }

    public void     executeQuery9_allHorse()throws SQLException {

        CallableStatement cstmt = conn.prepareCall("{call Query9_a_allHorses}");
        cstmt.execute();
        ResultSet rs = cstmt.getResultSet();

        int horse = 0;
        while (rs.next()) {
            if (rs.getInt(1) == horse)
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" +rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9)+"\t\t\t" + rs.getString(10));
            else {
                System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\tRACETIME\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
                System.out.println(rs.getString(1) + "\t\t\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t\t" +rs.getString(4) + "\t\t"
                        + rs.getString(5) + "\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9)+"\t\t\t" + rs.getString(10));
                horse = rs.getInt(1);
            }
        }
        rs.close();
        cstmt.close();
    }


    public void executeQuery9_aDate(java.sql.Date racedate,java.sql.Time timedate)throws SQLException {

        CallableStatement cstmt = conn.prepareCall("{call Query9_a_Date(?,?)}");
        cstmt.setDate(1,racedate);
        cstmt.setTime(2,timedate);
        cstmt.execute();
        ResultSet rs = cstmt.getResultSet();


        int horse = 0;

        while (rs.next()) {
            if (rs.getInt(1) == horse)
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" + rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6) + "\t\t\t" + rs.getString(7) + "\t\t\t" + rs.getString(8) + "\t\t\t" + rs.getString(9)+"\t\t\t" );
            else {
                System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\tRACETIME\\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
                System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6) + "\t\t\t" + rs.getString(7) + "\t\t\t" + rs.getString(8) + "\t\t\t" + rs.getString(9)+"\t\t\t");
                horse = rs.getInt(1);
            }
        }
        rs.close();
        cstmt.close();
    }


    void executeQuery9_aHorse(int horseId) throws SQLException {


        CallableStatement cstmt = conn.prepareCall("{call Query9_a_oneHorse(?)}");
        cstmt.setInt(1,horseId);


        cstmt.execute();

        ResultSet rs = cstmt.getResultSet();


        System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\t\\RaceTime\\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
        while (rs.next()) {


                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" +rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9)+"\t\t\t" + rs.getString(10));


        }
        rs.close();
        cstmt.close();
    }



    /**-------      Q9 TRAINER --------------------**/


    public void executeQuery9Trainer(boolean meeting,boolean oneHorse,boolean allHorses) throws SQLException {

        if (meeting) {
            System.out.println("Please insert Date and Time of Race");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            String d = reader.nextLine();
            String t = reader.nextLine();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            DateFormat tf = new SimpleDateFormat("HH:mm:ss");
            Date dateRace;
            java.sql.Date dateInsert = null;
            java.sql.Time timeInsert = null;
            java.util.Date  runTime ;
            try {
                dateRace = df.parse(d);
                runTime = tf.parse(t);
                dateInsert = RecordInserter.convertUtilToSql(dateRace);
                timeInsert = RecordInserter.convertUtilToSqlTime(runTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            executeQuery9_bDate(dateInsert,timeInsert);
            return;
        }
        if (oneHorse) {
            System.out.println("Please enter Trainer ID ");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int n = reader.nextInt();
            reader.close();
            executeQuery9_bTrainer(n);
            return;
        }
        if (allHorses) {
            System.out.println("Statement for all trainers");
            executeQuery9_allTrainers();
        }

    }


    public void     executeQuery9_allTrainers()throws SQLException {
        CallableStatement cstmt = conn.prepareCall("{call Query9_a_allTrainers}");
        cstmt.execute();
        ResultSet rs = cstmt.getResultSet();

        int horse = 0;
        while (rs.next()) {
            if (rs.getInt(1) == horse)
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" +rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9));
            else {
                System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" +rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9));
                horse = rs.getInt(1);
            }
        }
        rs.close();
        cstmt.close();
    }


    public void executeQuery9_bDate(java.sql.Date racedate,java.sql.Time timedate)throws SQLException {

        CallableStatement cstmt = conn.prepareCall("{call Query9_a_Date(?,?)}");
        cstmt.setDate(1,racedate);
        cstmt.setTime(2,timedate);

        cstmt.execute();

        ResultSet rs = cstmt.getResultSet();


        int horse = 0;

        while (rs.next()) {
            if (rs.getInt(1) == horse)
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" + rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6) + "\t\t\t" + rs.getString(7) + "\t\t\t" + rs.getString(8) + "\t\t\t" + rs.getString(9));
            else {
                System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
                System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" + rs.getString(4) + "\t\t\t\t"
                        + rs.getString(5) + "\t\t\t" + rs.getString(6) + "\t\t\t" + rs.getString(7) + "\t\t\t" + rs.getString(8) + "\t\t\t" + rs.getString(9));
                horse = rs.getInt(1);
            }
        }
        rs.close();
        cstmt.close();
    }


    void executeQuery9_bTrainer(int trainerId) throws SQLException {


        CallableStatement cstmt = conn.prepareCall("{call Query9_a_oneTrainer(?)}");
        cstmt.setInt(1,trainerId);


        cstmt.execute();

        ResultSet rs = cstmt.getResultSet();


        System.out.println("\nSTART HORSE\t\tMEETING DATE|\t\tDISTANCE|\t\tEND POS \t\t|POS1\t\t|POS2\t\tPOS3|\t\tTIMES|\t\tPERCENT%" + "\n");
        while (rs.next()) {


            System.out.println(rs.getString(1) + "\t\t\t\t" + rs.getString(2) + "\t\t\t" + rs.getString(3) + "\t\t\t" +rs.getString(4) + "\t\t\t\t"
                    + rs.getString(5) + "\t\t\t" + rs.getString(6)+"\t\t\t" + rs.getString(7)+"\t\t\t" + rs.getString(8)+"\t\t\t" + rs.getString(9));


        }
        rs.close();
        cstmt.close();
    }








    public static void main(String args[]) throws SQLException, ParseException {
        String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName="+USERNAME+";user="+USERNAME+";password="+PASSWORD+";";
     //   String dbConnString = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";

        QueryExecutor qe = new QueryExecutor(new ConnectionManager(dbConnString));
//        //Q1
//        qe.selectHorsesLike("Horse");
//        //Q2
//        Date d = (new SimpleDateFormat("yyyy-MM-dd")).parse("2010-01-08");
//        System.out.println(d.getTime());
//        qe.selectRacesByMeetingDate(d);
        //qe.executeQuery8b();

        boolean Q9A_date = false;
        boolean Q9A_oneHorse = false;
        boolean Q9A_allHorses = false;

      qe.executeQuery9Horse(true,false,false);

    }
}
