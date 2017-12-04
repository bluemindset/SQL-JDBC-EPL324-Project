package com.horses.dbmanage;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomis on 04/12/2017.
 */
public class TableExporter {
    private static final String EXPORT_DIR = "export-dir";
    private static final boolean INCLUDE_HEADERS = true;
    private static final char DEFAULT_SEPARATOR= '\t';

    private char separator;
    private ConnectionManager cm;

    public TableExporter(ConnectionManager cm) {
        this(cm , DEFAULT_SEPARATOR);
    }

    public TableExporter(ConnectionManager cm, char separator) {
        this.cm = cm;
        this.separator = separator;
    }
    public void exportTable(String tablename, String filename) {
        String sqlstmt = "SELECT * FROM " + tablename + ";";
        try (
                CSVWriter writer = new CSVWriter(new FileWriter(EXPORT_DIR + "/" + filename), separator , CSVWriter.NO_QUOTE_CHARACTER);
                Statement statement = cm.getDBConnection().createStatement();
                ResultSet myResultSet = statement.executeQuery(sqlstmt)
        ){

            writer.writeAll(myResultSet, INCLUDE_HEADERS);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void exportDB() {
        ConnectionManager cm = new ConnectionManager(Config.connection_url);
        TableExporter te = new TableExporter(cm);
        List<String> tableNames = new ArrayList<>();
        tableNames.add("BREEDER");
        tableNames.add("FAMILY");
        tableNames.add("FIELD_TYPE");
        tableNames.add("HORSE");
        tableNames.add("HORSE_COLOR");
        tableNames.add("HORSE_SEX");
        tableNames.add("JOCKEY");
        tableNames.add("LOG_HISTORY");
        tableNames.add( "MEETING");
        tableNames.add( "OWNER");
        tableNames.add( "PARTICIPATION");
        tableNames.add( "RACE");
        tableNames.add( "RACE_DISTANCE");
        tableNames.add("RACE_TYPE");
        tableNames.add("SYSTEM_USER");
        tableNames.add("TRAINER");
        
        for(String tn :tableNames) {
        	te.exportTable(tn, tn + ".txt");
        }
        
        cm.closeConnection();
    }

    public static void main (String args[]) {
        ConnectionManager cm = new ConnectionManager(Config.connection_url);
        TableExporter te = new TableExporter(cm);

        String tableName = "BREEDER";
        String filename ="BREEDER.txt";
        te.exportTable(tableName, filename);

        cm.closeConnection();
    }
}
