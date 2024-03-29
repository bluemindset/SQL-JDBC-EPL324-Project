package com.horses.dbmanage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class App {
//    private static final String USERNAME = "achrys16";
//    private static final String DEFAULT_PASS = "g7P5UchuqAda";
//    private static final String PASSWORD = DEFAULT_PASS;
    
//    private static void insertSystemAdmins(RecordInserter ri) {
//        List<SystemUser> systemUsers = new ArrayList<>();
//        systemUsers.add(new SystemUser("cchadj01","cchadj01", DEFAULT_PASS,"tomis", "what", SystemUserRole.SA));
////        systemUsers.add(new SystemUser("987423","achrys16", DEFAULT_PASS,"antria", "ever", SystemUserRole.SA));
////        systemUsers.add(new SystemUser("548512","ntzior01", DEFAULT_PASS,"ntzior", "plz", SystemUserRole.SA));
////        systemUsers.add(new SystemUser("258456","sioan01", DEFAULT_PASS,"O STEFOS", "O POLLYS", SystemUserRole.SA));
//
//        try {
//            ri.insertSystemUsers(systemUsers);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
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
    public static void main(String args[]) throws SQLException, ParseException, IOException {
        //PrintStream out = new PrintStream(new FileOutputStream("./console-output/output.txt"));
        //System.setOut(out);
        //String dbConnString = "jdbc:sqlserver://apollo.in.cs.ucy.ac.cy:1433;databaseName="+USERNAME+";user="+USERNAME+";password="+PASSWORD+";";
        //String dbConnString = "jdbc:sqlserver://\\DOCTOR\\SQLEXPRESS:1433;databaseName=master;integratedSecurity=true;user=DOCTOR\\stefanos";
        //String dbConnString = "jdbc:sqlserver://localhost:1433;databaseName=Horses;user=SA;password=Password123;";
        ConnectionManager connectionManager = new ConnectionManager(Config.connection_url);

        SchemaCreator schemaCreator = new SchemaCreator(connectionManager);
        schemaCreator.setDropDbFile("stored-procedures-create-schema/drop-db.sql");
        schemaCreator.dropSchema();
        schemaCreator.setCreateTablesFile("stored-procedures-create-schema/create-tables.sql");

        schemaCreator.setCreateForeignKeysFile("stored-procedures-create-schema/create-foreign-keys.sql");
        schemaCreator.setCreateDomainsFile("stored-procedures-create-schema/create-domains.sql");
        schemaCreator.setCreateTriggersFile("stored-procedures-create-schema/create-triggers.sql");
        schemaCreator.createSchema();

//        RecordInserter recordInserter = new RecordInserter(connectionManager);
//        //insertSystemAdmins(recordInserter);
//        recordInserter.insert();

        connectionManager.closeConnection();
    }
}
