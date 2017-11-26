package com.horses.dbmanage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.horses.dbobjects.SystemUser;
import com.horses.dbobjects.SystemUserRole;

public class App {
    private static final String USERNAME = "cchadj01";
    private static final String PASSWORD = "whatever";


    private static void insertSystemAdmins(RecordInserter ri) {
        List<SystemUser> systemUsers = new ArrayList<>();
        systemUsers.add(new SystemUser("123456","cchadj01", "chadj", "tomis", SystemUserRole.SA));

        try {
            ri.insertSystemUsers(systemUsers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 1.Vale ta stored procedures manualy sto microsoft sql server.
     * (Vriskontai stored-procedures-other kai ston StroreProceduresInsert)
     * 2.Vale username kai password pou sou edwke o panchriss ( sto USERNAME kai PASSWORD tou app).
     * 3. Prosthese sto insertSystemAdmins() tou App epipleon systemUser.add(new SystemUser( id, username, firstName, lastName, SystemUserRole.SA));
     * 4. Trekse tin main() tou APP.
     *
     * Prp prwta na dimiourgithoun ta triggers gia ola ta tables.
     * To console output vgainei sto /console-output/output.txt
     */
    public static void main(String args[]) throws SQLException, ParseException, FileNotFoundException {
        PrintStream out = new PrintStream(new FileOutputStream("./console-output/output.txt"));
        System.setOut(out);
        //String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName="+USERNAME+";user="+USERNAME+";password="+PASSWORD+";";
        //String dbConnString = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";
        String dbConnString = "jdbc:sqlserver://localhost:1433;databaseName=Horses;user=SA;password=Password123;";
        ConnectionManager connectionManager = new ConnectionManager(dbConnString);

        SchemaCreator schemaCreator = new SchemaCreator(connectionManager);
        schemaCreator.setDropDbFile("Tables/drop-db.sql");
        schemaCreator.setCreateTablesFile("Tables/create-tables.sql");
        schemaCreator.setCreateForeignKeysFile("Tables/create-foreign-keys.sql");
        schemaCreator.setCreateDomainsFile("Tables/create-domains.sql");
        schemaCreator.setCreateTriggersFile("Tables/create-triggers.sql");
        schemaCreator.createSchema();

        RecordInserter recordInserter = new RecordInserter(connectionManager);
        recordInserter.insert();

        connectionManager.closeConnection();
    }
}
