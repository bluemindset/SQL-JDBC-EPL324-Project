package com.horses.dbmanage;

import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static void main (String args[]) {
        ConnectionManager cm = new ConnectionManager(Config.connection_url);
        TableExporter te = new TableExporter(cm);

        String tableName = "BREEDER";
        String filename ="BREEDER.txt";
        te.exportTable(tableName, filename);

        cm.closeConnection();
    }
}
